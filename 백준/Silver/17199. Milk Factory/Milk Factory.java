//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17199
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int cnt;
	static Node adNode[];
	static boolean visit[];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		int N	= read();
		adNode	= new Node[N + 1];
		
		for(int i=1; i<N; i++)
		{
			int a = read();
			int b = read();
			adNode[b] = new Node(a, adNode[b]); // 간선을 반대로 받음 
		}
		
		for(int i=1; i<=N; i++)
		{
			visit	= new boolean[N+1];
			cnt		= 0;
			
			DFS(i);
			
			if(cnt == N-1)
			{
				System.out.print(i);
				return;
			}
		}

		System.out.print(-1);
	}
	public static void DFS(int now){
		for(Node next=adNode[now]; next!=null; next=next.next)
			if(!visit[next.node])
			{
				visit[next.node]= true;
				cnt++;
				DFS(next.node);
			}
	}
}