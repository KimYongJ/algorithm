//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6207
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int K, N, M;
	static int[] cowCnt, nowNode;
	static Node adNode[];
	static boolean visit[];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int node)
	{
		visit[node] = true;
		++cowCnt[node];
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
				DFS(next.node);
	}
	
	public static void main(String[] args)throws Exception{
		K		= read();	// 소의 수(1<=100)
		N		= read();	// 노드 수(1<=1,000)
		M		= read();	// 간선 수(1<=10,000)
		nowNode	= new int[K+1];						// 최초에 소들이 있는 노드번호
		cowCnt	= new int[N+1];						// 각 노드에 도착한 소의 수
		adNode	= new Node[N+1];
		
		for(int i=0; i<K; i++)
			nowNode[i] = read();
		
		while(M-->0)
		{
			int a		= read();
			int b		= read();
			adNode[a]	= new Node(b, adNode[a]);
		}
		
		for(int node : nowNode)
		{
			visit = new boolean[N+1];
			DFS(node);
		}
		
		
		int res = 0;
		for(int cnt : cowCnt)
			if(cnt == K)
				++res;
		
		System.out.print(res);
	}
}