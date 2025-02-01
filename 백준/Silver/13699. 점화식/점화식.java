//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13699
//5초 / 512mb

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long dp[] = new long[36];
		dp[0] = dp[1] = 1;
		
		for(int i=2; i<=35; i++)
		{
			int s		= 0;
			int e		= i - 1;
			long sum	= 0;
			while(s < e)
				sum += dp[s++] * dp[e--];
			
			dp[i] = sum * 2;
			
			if(i % 2 == 1)
				dp[i] += dp[i/2] * dp[i/2];
		}
		System.out.print(dp[Integer.parseInt(br.readLine())]);
	}
}