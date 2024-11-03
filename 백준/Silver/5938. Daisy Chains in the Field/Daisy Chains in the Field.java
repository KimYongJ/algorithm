//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5938
class Node{
	int node; Node next;Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int N, M;
	static Node adNode[];
	static boolean visit[];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int node) {
		visit[node] = true;
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
				DFS(next.node);
	}
	
	public static void main(String[] args)throws Exception{
		N		= read();
		M		= read();
		adNode	= new Node[N+1];
		visit	= new boolean[N+1];
		
		while(M-->0)
		{
			int a		= read();
			int b		= read();
			adNode[a]	= new Node(b, adNode[a]);
			adNode[b]	= new Node(a, adNode[b]);
		}
		
		DFS(1);
		
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		
		for(int i=1; i<=N; i++)
			if(!visit[i])
			{
				sb.append(i).append('\n');
				flag = true;
			}
		
		if(flag)
			System.out.print(sb.toString());
		else 
			System.out.print(0);
	}
}