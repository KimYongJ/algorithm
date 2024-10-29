//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15511
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static Node adNode[];
	static int color[];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static boolean DFS(int node, int nowColor) {
		color[node] = nowColor;
		
		int nextColor = nowColor == 1 ? 2 : 1;
		
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(color[next.node] == 0 && !DFS(next.node, nextColor))
				return false;
			else if(color[next.node] == nowColor)
				return false;

		return true;
	}
	
	public static void main(String[] args)throws Exception{
		int N	= read();	// 직원 수(1<=백만)
		int M	= read();	// 기존팀정보(1<=백만)
		adNode	= new Node[N+1];
		color	= new int[N+1];
		while(M-->0)
		{
			int a = read();
			int b = read();
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		for(int i=1; i<=N; i++)
			if(color[i] == 0 && !DFS(i,1))
			{
				System.out.print("IMPOSSIBLE");
				return;
			}
		System.out.print("POSSIBLE");
	}
}