//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1647
//2초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	
	static int parent[];
	
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq = new PriorityQueue<>();
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N	= Integer.parseInt(st.nextToken());	// 2<=십만
		int M	= Integer.parseInt(st.nextToken());	// 1<=백만
		parent	= new int[N + 1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());// 1<=천
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