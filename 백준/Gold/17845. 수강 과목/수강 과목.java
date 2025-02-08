//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/17845
//1초 / 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 1<=만 최대공부시간
		int K = Integer.parseInt(st.nextToken());// 1<=천 과목 수
		int dp[][] = new int[K+1][N+1];// 과목 : 시간
		int value[] = new int[K+1];
		int time[] = new int[K+1];
		
		for(int i=1; i<=K; i++)
		{
			st = new StringTokenizer(br.readLine());
			value[i]= Integer.parseInt(st.nextToken());
			time[i]	= Integer.parseInt(st.nextToken());
		}
		
		//과목 순회
		for(int i=1; i<=K;i++)
		{
			// 시간 순회
			for(int j=1; j<=N; j++)
			{
				dp[i][j] = dp[i-1][j];
				if(0<=j-time[i])
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j - time[i]] + value[i]);
			}
		}
		
		System.out.print(dp[K][N]);
	}
}