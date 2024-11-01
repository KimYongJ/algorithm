//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20530

class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}

class Main{
	
	static Node adNode[];
	static boolean findCycle;
	static int[] parent, groupKey;
	static boolean[] visit, isCycle;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void find_cycle_DFS(int nowNode, int beforeNode) {
		visit[nowNode] = true;
		for(Node next=adNode[nowNode]; next!=null && !findCycle; next=next.next)
			if(!visit[next.node])
			{
				parent[next.node] = nowNode; 
				find_cycle_DFS(next.node, nowNode); 
			}
			else if(next.node != beforeNode){
				findCycle = true;
				// 사이클을 찾았으니 체크한다. 시작점(nowNode) / 종료점(next.node)
				isCycle[next.node] = true; 
				for(int v = nowNode; v != next.node; v = parent[v])
					isCycle[v] = true;
			}
	}
	
	public static void grouping_DFS(int nowNode, int key) {
		visit[nowNode]		= true;
		groupKey[nowNode]	= key;
		for(Node next=adNode[nowNode]; next!=null; next=next.next)
			if(!visit[next.node] && !isCycle[next.node])
				grouping_DFS(next.node, key);
	}
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N		= read();	// 노드수(1<=이십만)
		int Q		= read();	// 쿼리수(1<=이십만)
		adNode		= new Node[N+1];
		visit		= new boolean[N+1];
		isCycle		= new boolean[N+1];
		findCycle	= false;
		groupKey	= new int[N+1];
		parent		= new int[N+1];
		
		for(int i=1; i<=N; i++)
		{
			int a = read();
			int b = read();
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		
		find_cycle_DFS(1,0);
		
		visit = new boolean[N+1];
		
		for(int i=1; i<=N; i++)
			if(isCycle[i])
				grouping_DFS(i, i);
		
		while(Q-->0)
			sb.append(groupKey[read()] == groupKey[read()] ? 1 : 2).append('\n');
		
		System.out.print(sb.toString());
	}
}