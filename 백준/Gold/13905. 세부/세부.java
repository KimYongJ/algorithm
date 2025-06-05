//https://www.acmicpc.net/problem/13905
//1초 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
		UnionFind uf = new UnionFind(N);
		
		for(int i=0; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			pq.add(new Node(n1, n2, dist));
		}
		
		while(!pq.isEmpty())
		{
			Node now = pq.poll();
			if(uf.union(now.n1, now.n2) && uf.find(s) == uf.find(e))
			{
				System.out.print(now.dist);
				return;
			}
		}
		
		System.out.print(0);
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
}