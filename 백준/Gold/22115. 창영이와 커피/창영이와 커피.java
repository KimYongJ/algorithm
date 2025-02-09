//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/22115
//2초 / 512MB

import java.util.Arrays;

class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();// 커피개수 1<=백
		int K		= read();// 카페인양 0<=십만
		int dp[]	= new int[K + 1];
		
		Arrays.fill(dp, 200);
		dp[0] = 0;
		
		for(int i=1; i<=N; i++)
		{
			int cf = read();
			for(int j=K; j>=cf; j--)
				dp[j] = Math.min(dp[j], dp[j - cf] + 1);
		}
		
		System.out.print(dp[K] == 200 ? -1 : dp[K]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
