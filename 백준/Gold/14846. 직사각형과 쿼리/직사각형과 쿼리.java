//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14846
class Main{
	public static void main(String[] args)throws Exception{
		int N			= read();
		int map[][][]	= new int[11][N+2][N+2];
		
		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
			{
				map[read()][y][x] = 1;
				
				for(int i=1; i<=10; i++)
					map[i][y][x] += map[i][y-1][x] + map[i][y][x-1] - map[i][y-1][x-1];
			}
		
		StringBuilder sb = new StringBuilder();
		int Q = read();
		while(Q-->0)
		{
			int y1 = read();
			int x1 = read();
			int y2 = read();
			int x2 = read();
			int cnt = 0;
			
			for(int i=1; i<=10; i++)
				if(0<map[i][y2][x2] - map[i][y2][x1-1] - map[i][y1-1][x2] + map[i][y1-1][x1-1])
					++cnt;
			
			sb.append(cnt).append('\n');
		}
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}