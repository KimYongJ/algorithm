// https://github.com/kimyongj/algorithm

class Main{
	static int map[][];
	static StringBuilder sb;
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int now) {
		for(int i=0; i<map.length; i++) 
		{
			while(map[now][i] > 0) 
			{
				map[now][i]--;
				map[i][now]--;
				DFS(i);
			}
		}
		sb.append(now + 1).append(' ');		
	}
	public static void main(String[] args)throws Exception{
		int N = read();
		map   = new int[N][N];
		
		for(int y=0; y<N; y++) 
		{
			int v = 0;
			for(int x=0; x<N; x++) 
			{
				map[y][x] = read();
				v += map[y][x];
			}
			if( v % 2 != 0)
			{
				System.out.print(-1);
				return;
			}
		}
		
		sb = new StringBuilder();
		DFS(0);
		System.out.print(sb.toString());
	}
}