//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1058
class Main{

	public static void main(String[] args)throws Exception{
		int N			= read();
		boolean[][]map	= new boolean[N][N];
		
		for(int y=0; y<N; y++)
		{
			for(int x=0; x<N; x++)
				map[y][x] = map[x][y] = System.in.read() == 'Y';
			System.in.read();
		}
		
		int res = 0;
		for(int y=0; y<N; y++)
		{
			int cnt = 0;
			for(int x=0; x<N; x++)
				if(y==x)
					continue;
				else if(map[y][x])
					++cnt;
				else
					for(int i=0; i<N; i++)
						if(map[i][x] && map[y][i])
						{
							++cnt;
							break;
						}

			res = Math.max(res, cnt);
		}
		System.out.print(res);
	}

	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}