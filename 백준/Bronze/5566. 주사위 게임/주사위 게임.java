//https://www.acmicpc.net/problem/5566
//1초 128MB

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
		
		int cnt = 0;
		int idx = 1;
		while(M-->0)
		{
			++cnt;
			int jump = Integer.parseInt(br.readLine());
			
			idx += jump;
			
			if(idx < N)
				idx += arr[idx];
				
			if(idx >= N)
				break;
		}
		
		System.out.print(cnt);
	}
}