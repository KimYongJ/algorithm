//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16493
//2초 / 512mb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 1<=200
		int M = Integer.parseInt(st.nextToken());	// 1<=20
		int dp[][] = new int[M+1][N+1];
		int day[] = new int[M+1];
		int page[] = new int[M+1];
		
		for(int m=1; m<=M; m++)
		{
			st = new StringTokenizer(br.readLine());
			day[m]	= Integer.parseInt(st.nextToken());
			page[m]	= Integer.parseInt(st.nextToken());
		}
		
		for(int m=1; m<=M; m++)
		{
			for(int n=1; n<=N; n++)
			{
				dp[m][n] = dp[m-1][n];
				
				if(0<=n-day[m])
					dp[m][n] = Math.max(dp[m][n], dp[m-1][n-day[m]] + page[m]);
			}
		}
		System.out.print(dp[M][N]);
	}
}