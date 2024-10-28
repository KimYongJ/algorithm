//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7535
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static Node adNode[];
	static int color[];
	static boolean flag;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static boolean DFS(int node, int f) {
		
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(color[next.node] == 0 && !DFS(next.node, color[next.node] = f==1 ? 2 : 1))
				return false;
			else if(color[next.node] == f)
				return false;
		
		return true;
	}
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();
		for(int i=1; i<=T; i++)
		{
			int N	= read();			// 노드 수(1<=이천)
			int L	= read();			// 상호작용 수(1<=백만)
			adNode	= new Node[N+1];	// 연결노드
			color	= new int[N+1];		// 해당 값
			flag	= true;
			
			while(L-->0)
			{
				int a		= read();
				int b		= read();
				adNode[a]	= new Node(b, adNode[a]);
				adNode[b]	= new Node(a, adNode[b]);
			}		
			
			for(int n=1; n<=N && flag; n++)
				if(color[n] == 0)
				{
					color[n] = 1;
					flag = DFS(n, 1);
				}
			
			sb.append("Scenario #").append(i).append(':').append('\n');
			if( flag )
				sb.append("No suspicious bugs found!");
			else
				sb.append("Suspicious bugs found!");

			sb.append('\n').append('\n');
		}
		System.out.print(sb.toString());
	}
}