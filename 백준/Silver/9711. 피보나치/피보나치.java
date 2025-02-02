//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9711
//1초 / 128mb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BigInteger dp[] = new BigInteger[10_001];
		dp[1] = BigInteger.ONE;
		dp[2] = BigInteger.ONE;
		
		for(int i=3; i<10_001; i++)
			dp[i] = dp[i-1].add(dp[i-2]);
		
		
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i<=T; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			BigInteger Q = new BigInteger(st.nextToken());
			
			sb.append("Case #").append(i).append(": ")
				.append(dp[P].mod(Q)).append('\n');
		}
		System.out.print(sb);
	}
}