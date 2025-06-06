//https://www.acmicpc.net/problem/11085
//2초 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{

	static int P, W;
	static int start, end;
	static int parent[];
	static PriorityQueue<Node> pq;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		P = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>();
		parent = new int[P + 1];
		
		for(int i=1; i<=P; i++)
			parent[i] = i;
		
		while(W-->0)
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
			int parent1 = find(now.n1);
			int parent2 = find(now.n2);
			if(parent1 == parent2)
				continue;
			
			if(parent[parent1] < parent[parent2])
				parent[parent2] = parent1;
			else
				parent[parent1] = parent2;
			
			if(find(start) == find(end))
			{
				System.out.print(now.dist);
				return;
			}
			
		}
		System.out.print(0);
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	static class Node implements Comparable<Node>{
		int n1, n2, dist;
		Node(int n1, int n2, int dist){
			this.n1=n1;
			this.n2=n2;
			this.dist=dist;
		}
		@Override
		public int compareTo(Node o) {
			return o.dist - dist;
		}
	}
}