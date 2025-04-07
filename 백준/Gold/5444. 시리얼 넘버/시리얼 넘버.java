//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/5444
//3초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());	// 테스트케이스
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N	= Integer.parseInt(st.nextToken());	// 기타개수(1<=500)
			int M	= Integer.parseInt(st.nextToken());	// 기준 값(1<=100,000)
			int S[] = new int[N];						// 기타의 시리얼 넘버(0<=100,000)
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++)
				S[i] = Integer.parseInt(st.nextToken());
			
			int dp[] = new int[M];
			int nextDp[] = new int[M];
			Arrays.fill(dp, -1);

			dp[0] = 0;
			for(int s : S)
			{
				for(int i=0; i<M; i++)
					nextDp[i] = dp[i];
				
				for(int j=0; j<M; j++)
				{
					if(dp[j] < 0)
						continue;
					
					int next = (j + s) % M;
					
					nextDp[next] = Math.max(nextDp[next], dp[j] + 1);
				}
				
				for(int i=0; i<M; i++)
					dp[i] = nextDp[i];
			}
			
			sb.append(dp[0]).append('\n');
		}
		System.out.print(sb);
	}
}