//https://www.acmicpc.net/problem/1551
//2초 128MB
//5 1 // N(1<=20), K(0<=N-1)
//5,6,3,9,-1
//답 : 1,-3,6,-10

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int arr[] = new int[N];
		
		st = new StringTokenizer(br.readLine(),",");
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int len = N;
		
		while(K-->0)
		{
			for(int i=1; i<len; i++)
				arr[i - 1] = arr[i] - arr[i-1];
			
			--len;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<len; i++)
			sb.append(arr[i]).append(i+1 == len ? "" : ',');
		
		System.out.print(sb);
	}
}