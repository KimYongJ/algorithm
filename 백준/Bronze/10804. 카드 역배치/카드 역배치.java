//https://www.acmicpc.net/problem/10804
//1초 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int arr[] = new int[21];
		
		for(int i=1; i<=20; i++)
			arr[i] = i;
		
		for(int i=1; i<=10; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			while(s < e)
			{
				int tmp = arr[s];
				arr[s] = arr[e];
				arr[e] = tmp;
				s++;
				e--;
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=20; i++)
			sb.append(arr[i]).append(' ');
		
		System.out.print(sb);
	}
}