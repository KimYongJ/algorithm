//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9507
//2초 / 128mb

class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		long dp[] = new long[68];
		
		dp[0] = dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		for(int i=4; i<68; i++)
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3] + dp[i-4];
		
		int T = read();
		
		while(T-->0)
			sb.append(dp[read()]).append('\n');
		
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}