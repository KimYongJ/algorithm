//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25418
//1초 / 512mb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A	= Integer.parseInt(st.nextToken());
		int B	= Integer.parseInt(st.nextToken());
		int dp[]= new int[B+1];
		
		for(int i=A+1; i<=B; i++)
		{
			dp[i] = dp[i-1] + 1;
			if(A <= i/2 && i % 2 == 0 && dp[i/2] < dp[i-1])
				dp[i] = dp[i/2] + 1;
		}
		
		System.out.print(dp[B]);
	}
}