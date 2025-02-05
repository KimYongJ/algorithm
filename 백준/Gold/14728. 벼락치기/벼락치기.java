//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14728
//2초 / 256mb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{ 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 시험 단원 개수 1<=100
		int T		= Integer.parseInt(st.nextToken());	// 
		int dp[][]	= new int[N+1][T+1];
		int W[]		= new int[N+1];
		int V[]		= new int[N+1];
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		// 과목 반복
		for(int n=1; n<=N; n++)
		{
			// 시간 반복
			for(int t=1; t<=T; t++)
			{
				dp[n][t] = dp[n-1][t];
				if(0<=t-W[n])
					dp[n][t] = Math.max(dp[n][t], dp[n-1][t-W[n]] + V[n]);
			}
		}
		System.out.print(dp[N][T]);
	}
}