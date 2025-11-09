//https://www.acmicpc.net/problem/22858
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int[] arr[], base;
	static int N, K;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[2][N + 1];
		base = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[0][i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			base[i] = Integer.parseInt(st.nextToken());
		
		int idx = 0;
		while(K-->0)
		{
			idx ^= 1;
			for(int i=1; i<=N; i++)
				arr[idx][base[i]] = arr[idx ^ 1][i];
		}

		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++)
			sb.append(arr[idx][i]).append(' ');
		
		System.out.print(sb);
	}
}