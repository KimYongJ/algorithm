//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16398
//1초 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node implements Comparable<Node>{
	int a, b;
	long cost;
	Node(int a, int b, long c){
		this.a=a;
		this.b=b;
		this.cost=c;
	}
	@Override
	public int compareTo(Node o) {
		return Long.compare(cost,o.cost);
	}
}
class Main{
	
	static int parent[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		parent = new int[N];
		for(int y=0; y<N; y++)
		{
			parent[y] = y;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++)
			{
				long c = Long.parseLong(st.nextToken());
				if(y < x)
				{
					pq.add(new Node(y, x, c));
				}
			}
		}
		
		long res = 0;
		while(!pq.isEmpty())
		{
			Node now = pq.poll();
			int p1 = getParent(now.a);
			int p2 = getParent(now.b);
			if(p1 != p2) {
				res += now.cost;
				if(p1 < p2)
					parent[p2] = p1;
				else
					parent[p1] = p2;
			}
		}
		System.out.print(res);
		
	}
	public static int getParent(int node) {
		if(parent[node] == node) return node;
		return parent[node] = getParent(parent[node]);
	}
}