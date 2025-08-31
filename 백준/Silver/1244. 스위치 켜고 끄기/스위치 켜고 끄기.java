//https://www.acmicpc.net/problem/1244
//2초 128MB
//8 // 스위치 개수(1<=100)
//0 1 0 1 0 0 0 1// 스위치 상태
//2 // 학생수(1<=100)
//1 3// 학생의 성별과 학생이 받은 수 (1은 남 2는 여)
//2 3
//답 : 1 0 0 0 1 1 0 1

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static boolean arr[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new boolean[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken()) == 1;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0)
		{
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			if(m == 1)
			{
				int now = n;

				while(now <= N)
				{
					arr[now] = !arr[now];
					now += n;
				}

				continue;
			}
			
			int s = n;
			int e = n;
			
			while(s - 1>=1 && e+1<=N && arr[s-1] == arr[e+1])
			{
				--s;
				++e;
			}
			
			for(;s<=e; s++)
				arr[s] = !arr[s];
		}
		
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++) {
			sb.append(arr[i] ? 1 : 0).append(' ');
			if(i%20==0)
				sb.append('\n');
		}
		
		System.out.print(sb);
	}
}