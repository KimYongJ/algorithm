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
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int dp[] = new int[B+1];
		
		for(int i=0; i<A; i++)
			dp[i] = 1000000;
		
		while(++A<=B)
		{
			dp[A] = dp[A-1] + 1;
			if(A % 2 == 0 && 0<=A/2)
				dp[A] = Math.min(dp[A], dp[A/2]+1);
		}
		
		System.out.print(dp[B]);
	}
}