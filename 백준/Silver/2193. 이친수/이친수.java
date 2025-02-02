//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2193
//2초 / 128mb

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());//1<=90
		long dp[]	= new long[N+3];
		
		dp[1] = dp[2] = 1;
		for(int i=3; i<=N; i++)
			dp[i] = dp[i-1] + dp[i-2];
		
		System.out.print(dp[N]);
	}
}