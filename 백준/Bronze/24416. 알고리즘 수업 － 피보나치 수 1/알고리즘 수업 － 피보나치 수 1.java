//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/24416
//1초 / 512mb

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N	= Integer.parseInt(br.readLine());
		int dp[]= new int[N+1];
		
		dp[1] = dp[2] = 1;
		
		for(int i=3; i<=N; i++)
			dp[i] = dp[i-1] + dp[i-2];

		sb.append(dp[N]).append(' ').append(N-2);
		System.out.print(sb);		
	}
}
