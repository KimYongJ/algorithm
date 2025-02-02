//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/8394
//1초 / 256mb

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N	= Integer.parseInt(br.readLine());	// 1<=천만
		int dp[]= new int[N+4];
		
		dp[2] = 2;
		dp[3] = 3;
		
		for(int i=4; i<=N; i++)
		{
			dp[i] = dp[i-1] + dp[i-2];
			if(dp[i] >= 10)
				dp[i] -= 10;
		}
		
		System.out.print(dp[N]);
	}
}