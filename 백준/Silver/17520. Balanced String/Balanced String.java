//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17520
//0.5초 / 512mb

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int dp[]	= new int[N+2];
		
		dp[1] = dp[2] = 2;
		
		for(int i=3; i<=N; i+=2)
			dp[i] = dp[i + 1] = (dp[i-1] << 1) % 16_769_023;

		System.out.print(dp[N]);
	}
}