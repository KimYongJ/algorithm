//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/30893
class Node{
	int node; Node next;
	Node(int n, Node t){node=n; next=t;}
}
class Main{
	
	static int		N, S, E;
	static Node		adNode[];
	static boolean	visit[];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static boolean DFS(int node, int flag)
	{
		if(node == E)
			return true;

		int childCnt = 0;
		boolean bool = false;
		
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
			{
				++childCnt;
				visit[next.node] = true;
				bool |= DFS(next.node, 1-flag);
			}  
		// 두번째 플레이어(flag가 1인)가 선택권이 많을수록(자식노드를 2개이상 방문)할 수 있다면 두번째 플레이어가 무조건 이긴다.
		// 그 이유는 첫번째 플레이어가 반드시 가야만 하는 곳의 반대(다른곳)으로 가게 되면 첫번째 플레이어가 무조건 지기 때문이다. 
		// 그러므로 두번 째 플레이어가 2개 이상의 자식을 갈 수 있다면 무조건 false를 반환해 두번째 플레이어가 이기게 만들어야 한다.
		if(flag == 1 && childCnt > 1)
			return false;

		return bool;// 그외에는 DFS결과 리턴
	}
	public static void main(String[] args)throws Exception{
		N		= read();	// 정점개수(2<=십만)
		S		= read();
		E		= read();
		adNode	= new Node[N+1];
		visit	= new boolean[N+1];
		
		for(int i=1; i<N; i++)
		{
			int a = read();
			int b = read();
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		
		visit[S] = true;

		if(DFS(S,0))
			System.out.print("First");
		else
			System.out.print("Second");
	}
}