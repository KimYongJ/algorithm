//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15991
//1초 / 512MB
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N		= 100_001;
		long dp[]	= new long[N];
		
		dp[1] = 1;
		dp[2] = dp[3] = 2;
		dp[4] = dp[5] = 3;
		dp[6] = 6;
		
		for(int i=7; i<N; i++)
			dp[i] = (dp[i-2] + dp[i-4] + dp[i-6])%1_000_000_009;
		
		int T = read();
		
		while(T-->0)
			//0<=십만
			sb.append(dp[read()]).append('\n');
		
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
