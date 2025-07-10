//https://www.acmicpc.net/problem/32477
//4초 1024

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int res = 1;
	static int N, M;
	static int parent[];
	static int cnt[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1];
		cnt = new int[N + 1];
		
		for(int i=0; i<=N; i++)
		{
			parent[i] = i;
			cnt[i] = 1;
		}
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int p1 = find(Integer.parseInt(st.nextToken()));
			int p2 = find(Integer.parseInt(st.nextToken()));
			
			if(p1 == p2)
				continue;
			if(parent[p1] > parent[p2])
			{
				int tmp = p1;
				p1 = p2;
				p2 = tmp;
			}
			
			parent[p2] = p1;
			cnt[p1] += cnt[p2];
			
			res = Math.max(res, cnt[p1]);
		}
		
		System.out.print(res);
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}