//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14728
//2초 / 256mb

class Main{
	public static void main(String[] args)throws Exception{ 
		int N		= read();	// 시험 단원 개수 1<=100
		int T		= read();	// 
		int dp[][]	= new int[N+1][T+1];
		int W[]		= new int[N+1];
		int V[]		= new int[N+1];
		for(int i=1; i<=N; i++)
		{
			W[i] = read();
			V[i] = read();
		}
		// 과목 반복
		for(int n=1; n<=N; n++)
		{
			// 시간 반복
			for(int t=1; t<=T; t++)
			{
				dp[n][t] = dp[n-1][t];
				if(0<=t-W[n])
					dp[n][t] = Math.max(dp[n][t], dp[n-1][t-W[n]] + V[n]);
			}
		}
		
		System.out.print(dp[N][T]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}