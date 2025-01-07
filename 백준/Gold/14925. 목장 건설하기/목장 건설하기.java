//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14925
class Main{
	public static void main(String[] args)throws Exception{
		int Y		= read();	//1<=천
		int X		= read();	//1<=천
		int map[][] = new int[Y+1][X+1];
		int dp[][]	= new int[Y+1][X+1];
		
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++)
				map[y][x] = read();
		
		int max = 0;
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(map[y-1][x-1] == 0)
				{
					dp[y][x] = Math.min(dp[y-1][x], Math.min(dp[y][x-1], dp[y-1][x-1])) + 1;
					max = Math.max(dp[y][x], max);
				}
		
		System.out.print(max);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}