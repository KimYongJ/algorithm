//https://www.acmicpc.net/problem/10812
//1초 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static int[] arr, brr;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N + 1];
		brr = new int[N + 1];
		
		for(int i=1; i<=N; i++)
			arr[i] = i;
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			for(int i=s; i<m; i++)
				brr[i] = arr[i];
			
			for(int i=m, j = s; i<=e; i++)
				arr[j++] = arr[i];
			
			for(int i=s + e - m + 1, j = s; i<=e; i++)
				arr[i] = brr[s++];
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++)
			sb.append(arr[i]).append(' ');
		
		System.out.print(sb);
	}
}