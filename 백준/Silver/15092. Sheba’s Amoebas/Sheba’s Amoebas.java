//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15092
class Main{
	
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1},{-1,1},{-1,-1},{1,-1},{1,1}};
	static int Y, X, map[][];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int y, int x) {
		map[y][x] = 0;
		for(int xy[] : dxy)
		{
			int nextY = y + xy[0];
			int nextX = x + xy[1];
			if(map[nextY][nextX] == 1)
				DFS(nextY, nextX);
		}
	}
	
	public static void main(String[] args)throws Exception{
		Y	= read();
		X	= read();
		map = new int[Y+2][X+2];
		
		for(int y=1; y<=Y; y++)
		{
			for(int x=1; x<=X; x++)
				if(System.in.read() == '#')
					map[y][x] = 1;
			System.in.read();
		}
		
		int cnt = 0;
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(map[y][x] == 1)
				{
					++cnt;
					DFS(y, x);
				}

		System.out.print(cnt);
	}
}