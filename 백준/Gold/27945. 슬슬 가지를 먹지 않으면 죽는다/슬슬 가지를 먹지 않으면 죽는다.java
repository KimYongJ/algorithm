//https://www.acmicpc.net/problem/27945
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static int parent[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Node> pq = new PriorityQueue<>();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			pq.add(new Node(n1, n2, t));
		}
		
		int edgeCnt = 1;
		int day = 0;
		while(!pq.isEmpty() && edgeCnt != N)
		{
			Node now = pq.poll();
			int p1 = find(now.n1);
			int p2 = find(now.n2);
			int t = now.t;
			
			if(p1 == p2)
				continue;
			
			edgeCnt++;
			
			if(parent[p1] < parent[p2])
				parent[p2] = p1;
			else
				parent[p1] = p2;
			
			if(day + 1 == t)
				day++;
			else
				break;
		}
		
		System.out.print(day + 1);
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	static class Node implements Comparable<Node>{
		int n1, n2, t;
		Node(int n1, int n2, int t){
			this.n1=n1;
			this.n2=n2;
			this.t=t;
		}
		@Override
		public int compareTo(Node o) {
			return t - o.t;
		}
	}
}