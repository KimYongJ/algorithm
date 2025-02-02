//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25418
//1초 / 512mb

class Main{
	public static void main(String[] args)throws Exception{
		int A	= read();
		int B	= read();
		int dp[]= new int[B+1];
		
		for(int i=A+1; i<=B; i++)
		{
			dp[i] = dp[i-1] + 1;
			if(A <= i/2 && i % 2 == 0 && dp[i/2] < dp[i-1])
				dp[i] = dp[i/2] + 1;
		}
		
		System.out.print(dp[B]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}