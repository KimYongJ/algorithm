// https://github.com/KimYongJ/algorithm

class Node{
	int node;	Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int 		a, b, T, N, M, cnt;
	static String 	str;
	static Node 	adNode[];
	static boolean 	visit[];
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static boolean DFS(int now, int before) {
		if(visit[now]) 
			return true;
		visit[now] = true;
		
		cnt++;
		
		for(Node n=adNode[now]; n!=null; n=n.next)
			if(  (visit[n.node] && n.node != before)  ||
				 (!visit[n.node] && !DFS(n.node, now))
			   ) 
				return false;

		return true;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		T = read();
		while(T-->0) 
		{
			cnt		= 0;
			N 		= read();
			M 		= read();
			str		= "graph";
			adNode 	= new Node[N+1];
			visit	= new boolean[N+1];
			for(int i=0; i<M; i++) 
			{
				a 			= read();
				b 			= read();
				adNode[a] 	= new Node(b, adNode[a]);
				adNode[b] 	= new Node(a, adNode[b]);
			}
			
			if( DFS(1,1) && cnt == N )
				str = "tree";
			
			sb.append(str)
				.append('\n');
		}
		
		System.out.print(sb);
	}
}