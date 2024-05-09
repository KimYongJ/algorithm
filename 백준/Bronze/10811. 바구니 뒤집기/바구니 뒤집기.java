// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N 		= Integer.parseInt(st.nextToken());
		int M 		= Integer.parseInt(st.nextToken());
		int arr[] 	= new int[N+1];
		int s,e, t;
		for(int i=1; i<=N; i++)
			arr[i] = i;
		
		for(int m=0; m<M; m++) 
		{
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			for(int i=s, j=e; i<j; i++,j--)
			{
				t = arr[i];
				arr[i] = arr[j];
				arr[j] = t; 
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(arr[i]).append(' ');
		System.out.println(sb);
	}
	
}