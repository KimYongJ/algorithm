//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14615
class Node{
	int node; Node next;
	Node(int n, Node t){node=n; next=t;}
}
class Main{
	
	static boolean[] visit;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int node, Node[] adNode, boolean[] isReach)
	{
		visit[node] = isReach[node] = true;
		
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
			{
				visit[next.node] = true;
				DFS(next.node, adNode, isReach);
			}
	}
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb 		= new StringBuilder();
		int N					= read();				// 노드 수 3<=십만
		int M					= read();				// 간선수 1<=백만
		Node[] forward			= new Node[N+1];		// 정방향 1번 노드에서 모든 노드로 갈 수 있는지
		Node[] reverse			= new Node[N+1];		// 역방향 N번 노드에서 모든 노드로 갈 수 있는지
		boolean[] isReachableF	= new boolean[N+1];		// 1번에서 특정 노드로갈 수 있다면 true
		boolean[] isReachableR	= new boolean[N+1];		// N번에서 특정 노드로갈 수 있다면 true
		
		while(M-->0)
		{
			int a = read();
			int b = read();
			forward[a] = new Node(b, forward[a]);
			reverse[b] = new Node(a, reverse[b]);
		}
		
		visit = new boolean[N+1];
		DFS(1, forward, isReachableF);
		visit = new boolean[N+1];
		DFS(N, reverse, isReachableR);
		
		int T = read();
		while(T-->0)
		{
			int node = read();
			if(isReachableF[node] && isReachableR[node])
				sb.append("Defend the CTP");
			else
				sb.append("Destroyed the CTP");
			sb.append('\n');
		}
		System.out.print(sb.toString());		
	}
}