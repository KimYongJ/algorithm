//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17212
//1초 / 256mb
class Main{
	public static void main(String[] args)throws Exception{
		int N	= read();	// 0<=십만
		int dp[]= new int[N+8];
		
		dp[1] = dp[2] = dp[5] = dp[7] = 1;
		dp[3] = dp[4] = dp[6] = 2;
		
		for(int i=8; i<=N; i++)
		{
			int min1 = Math.min(dp[i-1], dp[i-2]);
			int min2 = Math.min(dp[i-5], dp[i-7]);
			dp[i] = Math.min(min1, min2) + 1;
		}
		System.out.print(dp[N]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}