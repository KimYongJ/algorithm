//https://www.acmicpc.net/problem/21918
//1초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int arr[] = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			if(i == 1)		arr[l] = r;
			else if(i == 2)	while(l<=r)arr[l++] ^= 1;
			else if(i == 3)	while(l<=r)arr[l++] = 0;
			else if(i == 4)	while(l<=r)arr[l++] = 1;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(arr[i]).append(' ');
		System.out.print(sb);
	}
}