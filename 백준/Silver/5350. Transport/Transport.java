//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/5350
// 1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());	// 문제 세트 수
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	// 물품수(1<=20)
			int W = Integer.parseInt(st.nextToken());	// 수송기 용량
			int dp[] = new int[W + 1];
			
			for(int i=0; i<N; i++)
			{
				st = new StringTokenizer(br.readLine());
				int w = Integer.parseInt(st.nextToken());	// 물품 무게
				int v = Integer.parseInt(st.nextToken());	// 물품 가치
				
				for(int j=W; j>=w; j--)
					dp[j] = Math.max(dp[j], dp[j - w] + v);
			}
			
			sb.append(dp[W])
				.append('\n');
		}
		System.out.print(sb);
	}
}