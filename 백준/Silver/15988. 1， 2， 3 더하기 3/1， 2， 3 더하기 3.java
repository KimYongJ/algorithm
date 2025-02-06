//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15988
//1초 / 512MB
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		final long MOD = 1_000_000_009;
		int T		= read();
		int num[]	= new int[T];
		int max		= 0;
		
		for(int i=0; i<T; i++)
			max = Math.max(max, num[i] = read());// 1<=백만
		
		long dp[] = new long[max+4];
		
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		for(int i=4; i<=max; i++)
			dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % MOD;
		
		for(int N : num)
			sb.append(dp[N]).append('\n');
		
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
