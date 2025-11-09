//https://www.acmicpc.net/problem/22858
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int[] arr, dummy, base;
	static int N, K;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N + 1];
		base = new int[N + 1];
		dummy = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			base[i] = Integer.parseInt(st.nextToken());
		
		while(K-->0)
		{
			for(int i=1; i<=N; i++)
				dummy[base[i]] = arr[i];
			
			for(int i=1; i<=N; i++)
				arr[i] = dummy[i];
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++)
			sb.append(arr[i]).append(' ');
		
		System.out.print(sb);
	}
}