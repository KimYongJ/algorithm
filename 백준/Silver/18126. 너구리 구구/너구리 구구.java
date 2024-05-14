// https://github.com/KimYongJ/algorithm

class Main{
	
	static Node 	adNode[];
	static boolean 	visit[];
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	// DFS는 자식 노드들의 거리가 가장 먼 것을 반환한다.
	public static long DFS(int node, long dist) {
		if(visit[node])return 0;
		
		long sum 	= 0;
		
		visit[node] = true;
		
		for(Node now=adNode[node]; now!=null; now=now.next)
			sum = Math.max(sum, DFS(now.node,now.dist));

		return dist + sum;
	}
	public static void main(String[] args)throws Exception{
		int N 	= read();
		adNode 	= new Node[N+1];
		visit	= new boolean[N+1];
		
		int a,b,d;
		for(int i=1; i<N; i++) 
		{
			a 			= read();
			b 			= read();
			d 			= read();
			adNode[a] 	= new Node(b,d, adNode[a]);
			adNode[b] 	= new Node(a,d, adNode[b]);
		}
		System.out.print( DFS(1,0) );
	}
}
class Node{
	int node;	long dist;	Node next;
	Node(int node, long dist, Node next){
		this.node=node; 
		this.dist=dist;
		this.next=next;
	}
}