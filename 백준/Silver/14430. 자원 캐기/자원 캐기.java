//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14430
//2초 / 25MB
class Main{
	public static void main(String[] args)throws Exception{
		int Y		= read();	// 세로 1<=300
		int X		= read();	// 가로 1<=300
		int dp[][]	= new int[Y+1][X+1];
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				dp[y][x] = read() + Math.max(dp[y-1][x], dp[y][x-1]);

		System.out.print(dp[Y][X]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
