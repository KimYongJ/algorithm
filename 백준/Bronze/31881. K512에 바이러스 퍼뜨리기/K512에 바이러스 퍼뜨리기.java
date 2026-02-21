//https://www.acmicpc.net/problem/31881
//2초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 컴퓨터 수 1<=200,000
		int Q = Integer.parseInt(st.nextToken());// 질의 수 1<=200,000
		int cnt = N;
		boolean visit[] = new boolean[N+1];
		StringBuilder sb = new StringBuilder();
		
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			if(cmd == 3)
			{
				sb.append(cnt).append('\n');
				continue;
			}
			int idx = Integer.parseInt(st.nextToken());
			if(cmd == 1)
			{
				if(!visit[idx])
				{
					--cnt;
					visit[idx] = true;
				}
				continue;
			}
			
			if(visit[idx])
			{
				visit[idx] = false;
				++cnt;
			}
		}
		
		System.out.print(sb);
	}
}