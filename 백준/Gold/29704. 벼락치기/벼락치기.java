//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/29704
//1초 / 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());// 문제 개수1<=천
		int T		= Integer.parseInt(st.nextToken());// 남은 제출 기한 1<=천
		int day[]	= new int[N+1];
		int cost[]	= new int[N+1];
		int costSum = 0;
		int dp[][]	= new int[N + 1][T + 1];
		
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			day[i] = Integer.parseInt(st.nextToken());//1<=천
			costSum += cost[i] = Integer.parseInt(st.nextToken());//1<=오천
		}
		
		for(int i=1; i<=N; i++)
		{
			for(int j=0; j<=T; j++)
			{
				dp[i][j] = dp[i-1][j];
				if(0<=j-day[i] )
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-day[i]] + cost[i]);
			}
		}
		
		System.out.print(costSum - dp[N][T]);
	}
}