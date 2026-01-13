//https://www.acmicpc.net/problem/15464
//2초 512MB
//5 // 소(1<=100)
//1 3 4 5 2 // 소의 이동위치
//1234567 2222222 3333333 4444444 5555555 // 셔플 후 소의 위치
//원래 위치 출력
//1234567
//5555555
//2222222
//3333333
//4444444

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N+1];
		String str[] = new String[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			str[i] = st.nextToken();
		
		for(int i=1; i<=N; i++)
		{
			int idx = i;
			
			for(int j=1; j<=3; j++)
				idx = arr[idx];
			
			sb.append(str[idx]).append('\n');
		}
		
		System.out.print(sb);
	}
}