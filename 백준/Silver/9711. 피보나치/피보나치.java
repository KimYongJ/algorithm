//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9711
//1초 / 128mb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long dp[] = new long[10_001];

		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i<=T; i++)
		{
			Arrays.fill(dp, 0);
			StringTokenizer st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			
			dp[1] = dp[2] = 1;
			
			for(int j=3; j<=P; j++)
				dp[j] = (dp[j-1] + dp[j-2]) % Q;
			
			sb.append("Case #").append(i).append(": ")
				.append(dp[P] % Q).append('\n');
		}
		System.out.print(sb);
	}
}