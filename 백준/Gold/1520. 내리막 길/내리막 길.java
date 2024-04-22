// https://github.com/kimyongj/algorithm
class Main{
	
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, eY, eX, dp[][], map[][];
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static int DFS(int y, int x) {
		if(eY == y && eX== x)
			return 1;
		if(dp[y][x] != -1)
			return dp[y][x];
		
		dp[y][x]=0;
		
		for(int xy[] : dxy) 
		{
			int nextY = y + xy[0];
			int nextX = x + xy[1];
			if(nextY >= 0 && nextX >= 0 && nextY <Y && nextX<X && map[nextY][nextX] < map[y][x])
				dp[y][x] += DFS(nextY, nextX);
		}
		return dp[y][x];
	}
	public static void main(String[] args)throws Exception{
		Y  	= read();
		X  	= read();
		eY 	= Y-1;
		eX 	= X-1;
		dp 	= new int[Y][X];
		map	= new int[Y][X];
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++) 
			{
				map[y][x] = read();
				dp[y][x] = -1;
			}
		DFS(0,0);
		
		System.out.println(dp[0][0]);
	}
}