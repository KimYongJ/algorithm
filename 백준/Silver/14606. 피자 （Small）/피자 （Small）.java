//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14606
//1초 / 512mb

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N	= Integer.parseInt(br.readLine());
		if(N < 3) {
			System.out.print(N == 1 ? 0 : 1);
			return;
		}
		int dp[]= new int[N+1];
		dp[2]	= 1;
		dp[3]	= 3;
		
		for(int i=4; i<=N; i++)
		{
			int left 	= i / 2;
			int right	= (i+1) / 2;
			dp[i]		= left * right + dp[left] + dp[right];
		}
		System.out.print(dp[N]);
	}
}