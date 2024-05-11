// https://github.com/KimYongJ/algorithm

class Node{
	int node, dist;Node next;
	Node(int node, int dist, Node next){this.node=node;this.dist=dist;this.next=next;}
}
class Main{
	
	static final int	MAX = 1_000_000_000;
	static int 			a, b, c, N, Q, K, cnt;
	static Node[] 		adNode;
	static boolean 		visit[];
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void DFS(int node, int beforeDist) {
		for(Node now=adNode[node]; now!=null; now=now.next)
			if(!visit[now.node]) 
			{
				visit[now.node] = true;
				if(K <= Math.min(beforeDist, now.dist)) 
				{
					cnt ++;
					DFS(now.node, now.dist);
				}
			}
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		N 				= read();
		Q 				= read();
		adNode 			= new Node[N+1];
		for(int i=1; i<N; i++) 
		{
			a 			= read();
			b 			= read();
			c 			= read();
			adNode[a] 	= new Node(b,c, adNode[a]);
			adNode[b] 	= new Node(a,c, adNode[b]);
		}

		while(Q-->0) 
		{
			K 			= read();
			a 			= read();
			visit 		= new boolean[N+1];
			visit[a] 	= true;
			cnt 		= 0;
			
			DFS(a, MAX);
			
			sb.append(cnt).append('\n');
		}
		System.out.print(sb);
	}
}