//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/12896

class Node{
	int node;
	Node next;
	Node(int n, Node nxt){node=n; next=nxt;}
}

class Main{
	
	static Node[] adNode;
	static int N, maxLen, maxNode;
	static boolean visit[];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int node, int len) {
		if(maxLen < len)
		{
			maxNode = node;
			maxLen = len;
		}
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
			{
				visit[next.node] = true;
				DFS(next.node, len + 1);
			}
	}
	public static void main(String[] args)throws Exception{
		N		= read();
		adNode	= new Node[N+1];
		
		for(int i=1; i<N; i++)
		{
			int a = read();
			int b = read();
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		
		visit		= new boolean[N+1];
		visit[1]	= true;
		DFS(1, 0);
		
		visit			= new boolean[N+1];
		visit[maxNode]	= true;
		maxLen			= 0;
		DFS(maxNode, 0);
		
		System.out.print((1 + maxLen) >> 1);
	}
}