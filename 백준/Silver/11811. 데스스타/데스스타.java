//https://www.acmicpc.net/problem/11811
//1초 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int bit = 0;
			for(int j=0; j<N; j++)
				bit |= Integer.parseInt(st.nextToken());
			arr[i] = bit;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int a : arr) sb.append(a).append(' ');
		System.out.print(sb);
	}
}