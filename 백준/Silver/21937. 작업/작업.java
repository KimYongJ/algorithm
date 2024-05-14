// https://github.com/KimYongJ/algorithm
class Node{
	int node;
	Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}

class Main{

	static int 		a, b, N, M, result;
	static Node 	adNode[];
	static boolean	visit[];
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int node) {
		if(!visit[node]) 
		{
			visit[node] = true;
			result++;
			for(Node now=adNode[node]; now!=null; now=now.next)
				DFS(now.node);
		}
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		M 		= read();
		adNode 	= new Node[N+1];
		visit	= new boolean[N+1];

		for(int i=0; i<M; i++) 
		{
			a 			= read();
			b 			= read();
			adNode[b] 	= new Node(a, adNode[b]);
		}
		
		DFS( read() );
		
		System.out.print(  result-1  );
	}
}