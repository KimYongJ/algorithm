//https://www.acmicpc.net/problem/9098
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static int cnt[];
	static int parent[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		cnt = new int[30_001];
		parent = new int[30_001];
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			if(N == 0 && M == 0)
				break;
			
			for(int i=0; i<=N; i++)
			{
				parent[i] = i;
				cnt[i] = 1;
			}
			
			while(M-->0)
			{
				st = new StringTokenizer(br.readLine());
				int k = Integer.parseInt(st.nextToken());
				if(k > 1)
				{
					int now = Integer.parseInt(st.nextToken());
					k -= 1;
					while(k-->0)
					{
						int next = Integer.parseInt(st.nextToken());
						
						int p1 = find(now);
						int p2 = find(next);
						
						if(p1 == p2)
							continue;
						
						if(parent[p1] < parent[p2])
						{
							parent[p2] = p1;
							cnt[p1] += cnt[p2];
						}
						else
						{
							parent[p1] = p2;
							cnt[p2] += cnt[p1];
						}
					}
				}
			}
			
			sb.append(cnt[0]).append('\n');
		}
		System.out.print(sb);
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}