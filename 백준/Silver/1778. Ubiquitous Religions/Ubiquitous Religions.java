//https://www.acmicpc.net/problem/1778
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static int parent[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		parent = new int[50_001];
		
		int idx = 0;
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			if(N == 0 && M == 0)
				break;
			
			for(int i=1; i<=N; i++)
				parent[i] = i;
			
			while(M-->0)
			{
				st = new StringTokenizer(br.readLine());
				int p1 = find(Integer.parseInt(st.nextToken()));
				int p2 = find(Integer.parseInt(st.nextToken()));
				
				if(p1 == p2)
					continue;
				
				if(parent[p1] < parent[p2])
					parent[p2] = p1;
				else
					parent[p1] = p2;
			}
			
			int cnt = 0;
			
			for(int i=1; i<=N; i++)
				if(parent[i] == i)
					++cnt;
			
			sb.append("Case ").append(++idx).append(": ").append(cnt).append('\n');
		}
		System.out.print(sb);
		
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}