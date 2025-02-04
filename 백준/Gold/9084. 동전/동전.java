//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9084
//1초 / 128mb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{ 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());//1<=10
		while(T-->0)
		{
			int N		= Integer.parseInt(br.readLine());// 동전 가지 수 1<=20
			int arr[]	= new int[N+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			
			int M = Integer.parseInt(br.readLine());	// 목표 값 1<=만
			
			int dp[][] = new int[N+1][M+1];
			
			for(int i=0; i<=N; i++)
				dp[i][0] = 1;
			
			for(int i=1; i<=N; i++)
			{
				for(int j=1; j<=M; j++)
				{
					dp[i][j] = dp[i-1][j];
					if(0<=j-arr[i])
						dp[i][j] += dp[i][j-arr[i]];
				}
			}
			
			sb.append(dp[N][M]).append('\n');
		}
		System.out.print(sb);
	}
}