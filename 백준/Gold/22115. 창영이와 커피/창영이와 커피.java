//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/22115
//2초 / 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 커피개수 1<=백
		int K = Integer.parseInt(st.nextToken());// 카페인양 0<=십만
		int value[] = new int[N + 1];
		int dp[][] = new int[N + 1][K + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			value[i] = Integer.parseInt(st.nextToken());
			for(int j=1; j<=K; j++) {
				dp[i][j] = 200;
			}
		}
		for(int j=1; j<=K; j++)
			dp[0][j] = 200;
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=K; j++) {
				dp[i][j] = dp[i-1][j];
				if(0<=j-value[i])
					dp[i][j] = Math.min(dp[i][j], dp[i-1][j-value[i]] + 1);
			}
		}
		
		System.out.print(dp[N][K] == 200 ? -1 : dp[N][K]);
	}
}