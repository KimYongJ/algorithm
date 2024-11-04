//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17834
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int[] colorCnt, color;
	static Node adNode[];

	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static boolean DFS(int node, int col) {
		++colorCnt[col];
		color[node] = col;
		for(Node next=adNode[node]; next!=null; next=next.next)
		{
			if(color[next.node] == 0 && DFS(next.node, col == 1 ? 2 : 1))
				return true;
			else if(color[next.node] == col)
				return true;
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		int N	= read();	// 노드 수 (2 ≤ N ≤ 50,000)
		int M	= read();	// 간선 수 (1 ≤ M ≤ 500,000)
		adNode	= new Node[N+1];
		color	= new int[N+1];
		colorCnt= new int[3];
		
		while(M-->0)
		{
			int a = read();
			int b = read();
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);			
		}
		
		if(DFS(1, 1))
			System.out.print(0);
		else
			System.out.print((colorCnt[1] * colorCnt[2]) << 1);
	}
}