//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/10678
class Node{
	int node, dist; Node next;
	Node(int n, int d, Node t){node=n; dist=d; next=t;}
}
class Main{
	
	static final int MAX = 16_001;
	static int N, M;
	static boolean time[][];
	static Node[] adNode1, adNode2;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int node, int dist, Node adNode[], int flag) {
		if(node == N)
		{
			time[dist][flag] = true;
			return;
		}
		
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(node < next.node)
				DFS(next.node, dist + next.dist, adNode, flag);
	}
	
	public static void main(String[] args)throws Exception{
		N		= read();
		M		= read();
		adNode1	= new Node[N+1];
		adNode2	= new Node[N+1];
		time	= new boolean[MAX][2];
		
		while(M-->0)
		{
			int a		= read();
			int b		= read();
			int c		= read();
			int d		= read();
			adNode1[a]	= new Node(b, c, adNode1[a]);
			adNode2[a]	= new Node(b, d, adNode2[a]);
		}
		
		DFS(1, 0, adNode1, 0);
		DFS(1, 0, adNode2, 1);
		
		for(int i=1; i<MAX; i++)
			if(time[i][0] && time[i][1])
			{
				System.out.print(i);
				return;
			}
		
		System.out.print("IMPOSSIBLE");
	}
}