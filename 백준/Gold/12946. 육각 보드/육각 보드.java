//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/12946

class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1},{1,-1},{-1,1}};
	static int N, max;
	static int[][] color, map;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		N		= read();
		map		= new int[N+2][N+2];
		color	= new int[N+2][N+2];

		for(int y=1; y<=N; y++)
		{
			for(int x=1; x<=N; x++)
				if(System.in.read() == 'X')
					map[y][x] = 1;
			System.in.read();
		}
		
		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
				if(max == 3)
					break;
				else if(map[y][x] == 1 && color[y][x] == 0)
					DFS(y, x, 1);

		System.out.print(max);
	}
	public static void DFS(int y, int x, int col) {
		if(max == 3)
			return;
		
		max = Math.max(color[y][x] = col, max);
		
		for(int xy[] : dxy)
		{
			int nextY = xy[0] + y;
			int nextX = xy[1] + x;
			if(map[nextY][nextX] == 1)
			{
				if(color[nextY][nextX] == 0)
					DFS(nextY, nextX, col == 1 ? 2 : 1);
				else if(color[y][x] == color[nextY][nextX])
				{
					max = 3;
					return;
				}
			}
		}
	}
}