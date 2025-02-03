//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12865
//2초 / 512mb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 물품 수 1<=100
		int K		= Integer.parseInt(st.nextToken());	// 최대 무게 1<=십만
		int W[]		= new int[N+1];	// 각 물건의 무게 1<=십만
		int V[]		= new int[N+1];	// 물건의 가치 0<=천
		int dp[][]	= new int[N+1][K+1];
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		// 각 배낭을 선택할 때 마다 
		for(int i=1; i<=N; i++)
			// 무게 에 대해서 체크한다.
			for(int k=0; k<=K; k++)
			{
				dp[i][k] = dp[i-1][k];
				if(0 <= k-W[i])
					dp[i][k] = Math.max(dp[i-1][k], dp[i-1][k-W[i]] + V[i]);
			}
		
		System.out.print(dp[N][K]);
	}
}