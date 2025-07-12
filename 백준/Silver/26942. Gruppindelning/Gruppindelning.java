//https://www.acmicpc.net/problem/26942
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main{
	
	static int idx = 1;
	static int N, M;
	static int parent[];
	static HashMap<String, Integer> map;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		parent = new int[N + 1];
		map = new HashMap<>();
		
		for(int i=1; i<=N; i++)
		{
			map.put(br.readLine(), idx++);
			parent[i] = i;
		}
		
		M = Integer.parseInt(br.readLine());
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int p1 = find(map.get(st.nextToken()));
			int p2 = find(map.get(st.nextToken()));
			
			if(p1 == p2)
				continue;
			
			if(parent[p1] < parent[p2])
				parent[p2] = p1;
			else
				parent[p1] = p2;
		}
		
		int group = 0;
		
		for(int i=1; i<=N; i++)
			if(parent[i] == i)
				group++;
		
		System.out.print(group);
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}