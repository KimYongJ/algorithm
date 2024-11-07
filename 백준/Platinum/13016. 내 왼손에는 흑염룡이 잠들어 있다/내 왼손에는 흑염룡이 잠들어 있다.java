//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13016

class Node{
	int node, dist; Node next;
	Node(int n, int d, Node t){node=n; dist=d; next=t;}
}

class Main{
	
	
	static int 		N, resDist[];
	static int 		maxNode, maxDist;
	static Node 	adNode[];
	static boolean 	visit[];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int node, int dist) {
		resDist[node] = Math.max(resDist[node], dist);
		if(maxDist < dist)
		{
			maxDist = dist;
			maxNode = node;
		}
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
			{
				visit[next.node] = true;
				DFS(next.node, dist + next.dist);
			}
	}
	
	public static void main(String[] args)throws Exception{
		N		= read();	// 노드 수(2<=오만)
		adNode	= new Node[N+1];
		resDist	= new int[N+1];
		
		for(int i=1; i<N; i++)
		{
			int a = read();
			int b = read();
			int d = read();	// 거리(1<=사만)
			adNode[a] = new Node(b, d, adNode[a]);
			adNode[b] = new Node(a, d, adNode[b]);
		}
		
		for(int i=0, startNode = 1; i<3; i++)
		{
			visit			= new boolean[N+1];
			visit[startNode]= true;
			maxDist			= 0;
			
			DFS(startNode, 0);
			
			startNode = maxNode;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(resDist[i]).append('\n');
		System.out.print(sb.toString());
	}
}