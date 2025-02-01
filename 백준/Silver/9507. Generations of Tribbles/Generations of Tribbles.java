//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9507
//2초 / 128mb

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		long dp[] = new long[68];
		
		dp[0] = dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		for(int i=4; i<68; i++)
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3] + dp[i-4];
		
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
			sb.append(dp[Integer.parseInt(br.readLine())]).append('\n');
		
		System.out.print(sb);
	}
}