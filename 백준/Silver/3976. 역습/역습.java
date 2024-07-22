// https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0) 
		{
			int N			= read();
			int dp[][]		= new int[3][N+1];
			int pass1[]		= new int[N];
			int pass2[]		= new int[N];
			int dribble1[]	= new int[N];
			int dribble2[]	= new int[N];
			dp[1][0]		= read();
			dp[2][0]		= read();
			pass1[N-1]		= 1_000_000;
			pass2[N-1]		= 1_000_000;
			dribble1[N-1]	= read();
			dribble2[N-1]	= read();
			for(int i=0; i<N - 1; i++)	pass1[i]	= read();
			for(int i=0; i<N - 1; i++)	dribble1[i] = read();
			for(int i=0; i<N - 1; i++)	pass2[i]	= read();
			for(int i=0; i<N - 1 ; i++)	dribble2[i] = read();
			for(int i=1; i<=N; i++) 
			{
				dp[1][i] = Math.min(dp[2][i-1] + pass2[i-1] , dp[1][i-1] + dribble1[i-1]);
				dp[2][i] = Math.min(dp[1][i-1] + pass1[i-1] , dp[2][i-1] + dribble2[i-1]);
			}
			sb.append(Math.min(dp[1][N], dp[2][N])).append('\n');
		}
		System.out.print(sb.toString());
	}
}