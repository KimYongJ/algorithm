//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/22115
//2초 / 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 커피개수 1<=백
		int K = Integer.parseInt(st.nextToken());// 카페인양 0<=십만
		int dp[] = new int[K + 1];
		
		Arrays.fill(dp, 200);
		dp[0] = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			int cf = Integer.parseInt(st.nextToken());
			for(int j=K; j>=cf; j--)
				dp[j] = Math.min(dp[j], dp[j - cf] + 1);
		}
		
		System.out.print(dp[K] == 200 ? -1 : dp[K]);
	}
}
