//https://www.acmicpc.net/problem/11949
//2초 512MB

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
		
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=M; i++)
		{
			int idx = 1;
			
			while(idx < N)
			{
				if(arr[idx] % i > arr[idx + 1] % i)
				{
					int tmp = arr[idx];
					arr[idx] = arr[idx + 1];
					arr[idx + 1] = tmp;
				}
				++idx;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++)
			sb.append(arr[i]).append('\n');
		
		System.out.print(sb);
	}
}