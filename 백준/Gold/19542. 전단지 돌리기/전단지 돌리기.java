// https://github.com/KimYongJ/algorithm

class Node{
	int node;
	Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int 		a,b, N, S, D, dist;
	static Node 	adNode[];
	static boolean 	visit[];
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static int DFS(int now) {
		if(visit[now]) 
			return 0;
        
		visit[now] = true;
		
		int max = 0;
        
		for(Node n=adNode[now]; n!=null; n=n.next)
			if(!visit[n.node])
				max = Math.max(DFS(n.node),max);
		
		if(now != S && max >= D)
			dist += 2;

		return max + 1;
	}

	public static void main(String[] args)throws Exception{
		N 		= read();
		S 		= read();
		D 		= read();
		adNode 	= new Node[N+1];
		visit   = new boolean[N+1];
		
		for(int i=1; i<N; i++) 
		{
			a = read();
			b = read();
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}

		DFS(S);
        
		System.out.println(dist);
	}
}