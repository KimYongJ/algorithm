// https://github.com/KimYongJ/algorithm

class Main{
	
	static int 		dxy[][][] = {
									{{-1, -1}, {0, -1}, {1, -1}, {1, 0}, {0, 1}, {-1 ,0}},
									{{-1, 0}, {0, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}}
								};	
	static int 		Y, X, sum, nextY, nextX, map[][];
	static boolean 	visit[][];

	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int y, int x) {
		visit[y][x] = true;
		
		for(int xy[] : dxy[y%2]) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(nextY>=0 && nextX>=0 && nextY<Y+2 && nextX<X+2) 
			{
				if(map[nextY][nextX] == 1) 
					sum ++;
				else if(map[nextY][nextX] == 0 && !visit[nextY][nextX])
					DFS(nextY, nextX);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		X 		= read();
		Y 		= read();
		map 	= new int[Y+2][X+2];
		visit 	= new boolean[Y+2][X+2];

		for(int y=1; y<=Y; y++) 
			for(int x=1; x<=X; x++)
				map[y][x] = read();
		
		DFS(0,0); // 패딩읗 넣고 0만 탐색하는데, 1을 만나면 넓이에서 + 1 
	
		System.out.println(sum);
	}
}