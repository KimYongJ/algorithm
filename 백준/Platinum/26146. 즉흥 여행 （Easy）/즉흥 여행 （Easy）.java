//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/26146
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int N, M;
	static Node[] forward, reverse;
	static boolean visit[];
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static int DFS(int node, Node[] adNode) {
		visit[node] = true;
		int cnt = 1;
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
				cnt += DFS(next.node, adNode);
		return cnt;
	}
	public static void main(String[] args)throws Exception{
		N		= read();	// 노드 개수(1<=이십만)
		M		= read();	// 항공편 개수(0<=오십만)
		forward	= new Node[N+1];
		reverse	= new Node[N+1];
		
		while(M-->0)
		{
			int a = read();
			int b = read();
			forward[a] = new Node(b, forward[a]);
			reverse[b] = new Node(a, reverse[b]);
		}
		
		visit		= new boolean[N+1];
		int cnt1	= DFS(1, forward);
		visit		= new boolean[N+1];
		int cnt2 	= DFS(1, reverse);
		
		if(N == cnt1 && cnt1 == cnt2)
			System.out.print("Yes");
		else
			System.out.print("No");
	}
}