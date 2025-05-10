//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/13418
//1초 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken())+1;// 1 <= 1,000
		M = Integer.parseInt(st.nextToken());//	1 <= N * (N-1) / 2
		
		PriorityQueue<Node> max = new PriorityQueue<>();
		PriorityQueue<Node> min = new PriorityQueue<>();
		
		for(int i=0; i<=M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken())+1;
			int n2 = Integer.parseInt(st.nextToken())+1;
			int c = Integer.parseInt(st.nextToken());

			max.add(new Node(n1, n2, c));
			min.add(new Node(n1, n2, c ^ 1));
		}
		
		int MAX = N - cal(max) - 1;
		int MIN = cal(min);
		System.out.print(MAX * MAX - MIN * MIN);
	}
	static int cal(PriorityQueue<Node> pq) {
		int res = 0;
		int parent[] = new int[N + 1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		int edgeCnt = 1;
		while(edgeCnt < N)
		{
			Node now = pq.poll();
			
			int p1 = getParent(now.node1, parent);
			int p2 = getParent(now.node2, parent);
			if(p1 != p2)
			{
				edgeCnt += 1;
				res += now.cost;
				if(parent[p1] < parent[p2])
					parent[p2] = p1;
				else
					parent[p1] = p2;
			}
		}
		
		
		return res;
	}
	static int getParent(int node, int[] parent) {
		if(parent[node] == node)
			return node;
		
		return parent[node] = getParent(parent[node], parent);
	}
	static class Node implements Comparable<Node>{
		int node1, node2, cost;
		Node(int n1, int n2, int f){
			node1 = n1;
			node2 = n2;
			cost = f;
		}
		@Override
		public int compareTo(Node o) {
			return cost - o.cost;
		}
	}
}
