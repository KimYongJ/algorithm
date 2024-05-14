// https://github.com/KimYongJ/algorithm

class Main{
	static final int 	dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int 			T, nextY, nextX, map[][];
	static int			r, g, b, cnt, Y, X;
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int y, int x) {
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(map[nextY][nextX] >= T) 
			{
				map[nextY][nextX] = 0;
				DFS(nextY, nextX);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		Y 	= read();
		X 	= read();
		map = new int[Y+2][X+2];
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++) 
			{
				r = read();
				g = read();
				b = read();
				map[y][x] = (r+g+b) / 3;
			}

		T = read();
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(map[y][x] >= T) 
				{
					cnt ++;
					map[y][x] = 0;
					DFS(y,x);
				}

		System.out.print(cnt);
	}
}