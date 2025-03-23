//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1647
//2초 128MB

import java.util.PriorityQueue;

class Main{
	
	static int parent[];
	
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int N	= read();	// 2<=십만
		int M	= read();	// 1<=백만
		parent	= new int[N + 1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		while(M-->0)
		{
			int a = read();
			int b = read();
			int c = read();// 1<=천
			pq.add(new Node(a,b,c));
		}
		
		int total	= 0;
		int max		= 0;
		int repeat	= N - 1;
		
		while(0 < repeat)
		{
			Node now = pq.poll();
			int p1 = getParent(now.a);
			int p2 = getParent(now.b);
			if(p1 != p2)
			{
				if(p1 < p2)
					parent[p1] = p2;
				else
					parent[p2] = p1;
				
				total += now.c;
				repeat-= 1;
				
				if(max < now.c)
					max = now.c;
			}
		}
		System.out.print(total - max);
	}
	public static int getParent(int node) {
		if(parent[node] == node)
			return node;
		return parent[node] = getParent(parent[node]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}

class Node implements Comparable<Node>{
	int a,b,c;
	Node(int a, int b, int c){
		this.a=a;
		this.b=b;
		this.c=c;
	}
	@Override
	public int compareTo(Node o) {
		return this.c - o.c;
	}
}