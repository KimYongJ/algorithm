//https://www.acmicpc.net/problem/24164
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static int parent[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		parent = new int[N  + 1];
		
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
		
		System.out.print(cnt - 1);
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}