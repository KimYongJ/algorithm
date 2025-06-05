//https://www.acmicpc.net/problem/13905

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{

	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());//집의 수(2≤N≤100,000)
		int M = Integer.parseInt(st.nextToken());//다리의 수(1≤M≤300,000)
		
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		List<Object> adList[] = new ArrayList[N + 1];
		UnionFind uf = new UnionFind(N);
		
		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			pq.add(new Node(n1, n2, dist));
		}
		
		// 큐에서 dist가 큰 값 기준으로 꺼내며 트리를 형성
		int edgeCnt = 1;
		while(!pq.isEmpty() && edgeCnt != N)
		{
			Node now = pq.poll();
			if(uf.union(now.n1, now.n2))
			{
				++edgeCnt;
				adList[now.n1].add(new Object(now.n2, now.dist));
				adList[now.n2].add(new Object(now.n1, now.dist));
			}
		}
		
		System.out.print( bfs(adList, N, s, e) );
	}
	static int bfs(List<Object> adList[], int N, int s, int e) {
		ArrayDeque<Object> q = new ArrayDeque<>();
		boolean visit[] = new boolean[N + 1];
		visit[s] = true;
		
		q.add(new Object(s, 1<<30));
		
		while(!q.isEmpty())
		{
			Object now = q.poll();
			
			if(now.node == e)
				return now.min;
			
			for(Object next : adList[now.node])
			{
				if(visit[next.node])
					continue;
				
				visit[next.node] = true; 
				
				q.add(new Object(next.node, Math.min(now.min, next.min)));
			}
		}
		
		return 0;
	}
	static class UnionFind{
		int N;
		int parent[];
		UnionFind(int N){
			this.N=N;
			this.parent = new int[N + 1];
			for(int i=1; i<=N; i++)
				parent[i] = i;
		}
		boolean union(int n1, int n2) {
			int parent1 = find(n1);
			int parent2 = find(n2);
			if(parent1 == parent2)
				return false;
			
			if(parent[parent1] < parent[parent2])
				parent[parent2] = parent1;
			else
				parent[parent1] = parent2;
			
			return true;
		}
		int find(int node) {
			if(parent[node] == node) return node;
			return parent[node] = find(parent[node]);
		}
	}

	static class Node implements Comparable<Node>{
		int n1, n2, dist;
		Node(int n1, int n2, int dist){
			this.n1=n1;
			this.n2=n2;
			this.dist = dist;
		}
		@Override
		public int compareTo(Node o) {
			return o.dist - dist;
		}
	}
	static class Object{
		int node, min;
		Object(int n, int m){
			this.node = n;
			this.min = m;
		}
	}
}