//https://www.acmicpc.net/problem/5033
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static int money[];
	static int parent[];
	
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		money = new int[N + 1];
		parent = new int[N + 1];
		
		for(int i=0; i<N; i++)
		{
			parent[i] = i;
			money[i] = Integer.parseInt(br.readLine());
		}
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int p1 = find(Integer.parseInt(st.nextToken()));
			int p2 = find(Integer.parseInt(st.nextToken()));
			
			if(p1 == p2)
				continue;
			
			if(p1 < p2)
			{
				parent[p2] = p1;
				money[p1] += money[p2];
			}
			else
			{
				parent[p1] = p2;
				money[p2] += money[p1];
			}
		}
		
		
		for(int i=0; i<N; i++)
		{
			if(parent[i] == i && money[i] != 0)
			{
				System.out.print("IMPOSSIBLE");
				return;
			}
		}
		
		System.out.print("POSSIBLE");
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}