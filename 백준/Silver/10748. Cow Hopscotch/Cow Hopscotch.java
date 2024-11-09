//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/10748
class Main{
	
	static final int MOD = 1_000_000_007;
	static int Y, X;
	static int[][] map, dp;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static int solved(int y, int x) {
		if(y==Y-1 && x==X-1)
			return 1;
		
		if(dp[y][x] != -1)
			return dp[y][x];
		
		dp[y][x] = 0;
		
		for(int y1=y+1; y1<Y; y1++)
			for(int x1=x+1; x1<X; x1++)
				if(map[y1][x1] != map[y][x])
				{
					dp[y][x] += solved(y1, x1);
					dp[y][x] %= MOD;
				}
		
		return dp[y][x];
	}
	public static void main(String[] args)throws Exception{
		Y	= read();
		X	= read();
			  read(); // 값 하나 버림
		map = new int[Y][X];
		dp	= new int[Y][X];
		
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++)
			{
				map[y][x]	= read();
				dp[y][x]	= -1;
			}
		
		System.out.print( solved(0,0) );
	}
}