//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25214
//1초 / 1024mb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		sb.append(0).append(' ');
		
		int N		= Integer.parseInt(br.readLine());
		int arr[]	= new int[N+2];
		int dp[]	= new int[N+2];
		int min[]	= new int[N+2];
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		min[1] = arr[1] = Integer.parseInt(st.nextToken());
		for(int i=2; i<=N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
			if(arr[i-1] >= arr[i])
				dp[i] = dp[i-1];
			else
				dp[i] = Math.max(dp[i-1], arr[i] - min[i-1]);
			
			min[i] = Math.min(arr[i], min[i-1]);
			
			sb.append(dp[i]).append(' ');
		}
		
		System.out.print(sb);
	}
}