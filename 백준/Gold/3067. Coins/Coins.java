//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3067
//1초 / 128mb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{ 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int C[] = new int[N+1];
			for(int i=1; i<=N; i++)
				C[i] = Integer.parseInt(st.nextToken());
			
			int M = Integer.parseInt(br.readLine());
			int dp[] = new int[M+1];
			dp[0] = 1;
			
			for(int n=1; n<=N; n++)
				for(int i=1; i<=M; i++)
					if(0<=i-C[n])
						dp[i] += dp[i-C[n]];
			
			sb.append(dp[M]).append('\n');
		}
		System.out.print(sb);
	}
}