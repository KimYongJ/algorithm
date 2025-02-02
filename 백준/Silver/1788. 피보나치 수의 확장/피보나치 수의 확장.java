//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1788
//2초 / 128mb

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int len = Math.abs(N);
		int dp[] = new int[len+2];	// 양수
		dp[1] = 1;
		
		for(int i=2; i<=len; i++)
		{
			dp[i] = dp[i-1] + dp[i-2];
			
			if(dp[i] >= 1_000_000_000)
				dp[i] -= 1_000_000_000;
		}
		
		StringBuilder sb = new StringBuilder();
		if(N == 0)
			sb.append(0);
		else
			sb.append(len % 2 == 0 && N < 0 ? -1 : 1);
		
		sb.append('\n').append(dp[len]);
		
		System.out.print(sb);
	}
}