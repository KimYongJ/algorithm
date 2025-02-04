//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17271
//1초 / 256MB

class Main{
	public static void main(String[] args)throws Exception{
		final int MOD = 1_000_000_007;
		int N		= read();	// 최대시간 1<=만
		int M		= read();	// B스킬 시간 2<=100
		int dp[]	= new int[10_001];
		
		dp[0] = 1;
		
		for(int i=1; i<=N; i++)
		{
			dp[i] = dp[i-1];
			
			if(0<= i-M)
				dp[i] += dp[i-M];
			
			if(dp[i] >= MOD)
				dp[i] -=MOD;
		}
		System.out.print(dp[N]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
