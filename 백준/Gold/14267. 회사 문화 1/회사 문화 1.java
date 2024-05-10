// https://github.com/kimyongj/algorithm
class Node{
	int node;
	Node next;
	Node(int node, Node next){this.node=node;this.next=next;}
}

class Main{
	
	static int N, M, x, y, dp[];
	static Node adNode[];
	public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
	public static void DFS(int node) {
		for(Node now=adNode[node]; now!=null; now=now.next) 
		{
			dp[now.node] += dp[node]; 
			DFS(now.node);
		}
		
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		M 		= read();
		dp 		= new int[N+1];
		adNode 	= new Node[N+1];
		read();
		for(int i=2; i<=N; i++) 
		{
			x 			= read();
			adNode[x] 	= new Node(i, adNode[x]);
		}
		
		for(int i=0; i<M; i++) 
		{
			x 		= read();
			y 		= read();
			dp[x] 	+= y;
		}
		
		DFS(1);
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(dp[i]).append(' ');
		System.out.println(sb);
	}
}