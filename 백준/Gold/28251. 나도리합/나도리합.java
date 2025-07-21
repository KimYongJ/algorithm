//https://www.acmicpc.net/problem/28251
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, Q;
	static int parent[];
	static long sum[];
	static long mul[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		sum = new long[N + 1];
		mul = new long[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			parent[i] = i;
			sum[i] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int p1 = find(Integer.parseInt(st.nextToken()));
			int p2 = find(Integer.parseInt(st.nextToken()));
			
			if(p1 > p2)
			{
				int tmp = p1;
				p1 = p2;
				p2 = tmp;
			}
			
			if(p1 != p2)
			{
				parent[p2] = p1;
				mul[p1] += mul[p2] + (sum[p1] * sum[p2]);
				sum[p1] += sum[p2];
			}
			
			sb.append(mul[p1]).append('\n');
		}
		
		System.out.print(sb);
	}

	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}