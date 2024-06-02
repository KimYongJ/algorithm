// https://github.com/kimyongj/algorithm

class Node{
	int node;	Node next;
	Node(int node, Node next){this.node = node;this.next = next;}
}
class Main{
	
	static int 		N, M;
	static boolean 	visit[];
	static Node 	adNode[];
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static boolean DFS(int node) {
		boolean flag = true;
		
		for(Node n=adNode[node]; n!=null; n=n.next) 
		{
			if(!visit[n.node] && DFS(n.node)) return true;
			else flag = false;
		}
		
		return flag;
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		M 		= read();
		adNode 	= new Node[N+1];
		visit	= new boolean[N+1];
		
		int a, b, n;
		for(int i=0; i<M; i++) 
		{
			a 			= read();
			b 			= read();
			adNode[a] 	= new Node(b, adNode[a]);
		}
		
		n	= read();
		for(int i=0; i<n; i++)
			visit[read()] = true;

		System.out.print(!visit[1] && DFS(1) ? "yes" : "Yes");
	}
}