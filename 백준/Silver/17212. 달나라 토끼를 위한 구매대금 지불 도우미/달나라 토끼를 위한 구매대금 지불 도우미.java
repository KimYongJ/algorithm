//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17212
//1초 / 256mb

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N	= Integer.parseInt(br.readLine());	// 0<=십만
		long dp[]= new long[N+8];
		
		dp[1] = dp[2] = dp[5] = dp[7] = 1;
		dp[3] = dp[4] = dp[6] = 2;
		
		for(int i=8; i<=N; i++)
		{
			long min1 = Math.min(dp[i-1], dp[i-2]);
			long min2 = Math.min(dp[i-5], dp[i-7]);
			dp[i] = Math.min(min1, min2) + 1;
		}
		System.out.print(dp[N]);
	}
}