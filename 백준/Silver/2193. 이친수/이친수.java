//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2193
//2초 / 128mb

class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();//1<=90
		long dp[]	= new long[N+3];
		
		dp[1] = 1;
		
		for(int i=2; i<=N; i++)
			dp[i] = dp[i-1] + dp[i-2];
		
		System.out.print(dp[N]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}