//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16493
//2초 / 512mb

class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 1<=200
		int M		= read();	// 1<=20
		int dp[][]	= new int[M+1][N+1];
		int day[]	= new int[M+1];
		int page[]	= new int[M+1];
		
		for(int m=1; m<=M; m++)
		{
			day[m]	= read();
			page[m]	= read();
		}
		
		for(int m=1; m<=M; m++)
		{
			for(int n=1; n<=N; n++)
			{
				dp[m][n] = dp[m-1][n];
				
				if(0<=n-day[m])
					dp[m][n] = Math.max(dp[m][n], dp[m-1][n-day[m]] + page[m]);
			}
		}
		System.out.print(dp[M][N]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}