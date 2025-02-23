//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/19581
class Node{
	int node, dist; Node next;
	Node(int n, int d, Node next){node=n; dist=d; this.next=next;}
}
class Main{
	
	static Node adNode[];
	static int maxNode, maxDist;
	static boolean visit[];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int node, int dist) {
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
		int N	= read(); // 노드수(3<=십만)
		adNode	= new Node[N + 1];
		
		for(int i=1; i<N; i++)
		{
			int a		= read();
			int b		= read();
			int d		= read();// 가중치(1<=이만)
			adNode[a]	= new Node(b, d, adNode[a]);
			adNode[b]	= new Node(a, d, adNode[b]);
		}
		int node[] = new int[2];	// 가장 먼 노드 2개를 담을 배열
		int dist[] = new int[2];	// 두번째 먼 노드의 거리를 담을 배열
		
		visit	 = new boolean[N+1];
		visit[1] = true;
		
		DFS(1, 0);
		
		maxDist			= 0;
		node[0]			= maxNode;
		visit			= new boolean[N+1];
		visit[maxNode]	= true;
		
		DFS(maxNode, 0);
		
		node[1]			= maxNode;
		
		// start에서 end를 제외하고 구하고
		// end에서 start를 제외하고 구한다.
		for(int i=0; i<2; i++)
		{
			visit			= new boolean[N+1];
			maxDist			= 0;
			visit[node[0]]	= visit[node[1]] = true;
			DFS(node[i], 0);
			dist[i] = maxDist;
		}

		System.out.print(Math.max(dist[0], dist[1]));
	}
}