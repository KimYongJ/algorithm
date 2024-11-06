//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/24232
class Node{
	int node, idx, isReverse; // 정방향 : 0 / 역방향 : 1
	Node next;
	Node(int node, int idx, int isReverse, Node next){
		this.node=node; this.idx=idx; this.isReverse=isReverse;
		this.next=next;
	}
}
class Main{
	
	static int N;
	static int reverseCnt[];		// 각 노드당 역방향의 개수를 카운팅할 배열
	static int isReverse[];			// 간선을 최종적으로 뒤집을지 말지 결정하는 배열
	static Node adNode[];			// 인접노드를 담을 배열
	static boolean visit[];			// DFS마다 방문 체크
	
	public static void main(String[] args)throws Exception{
		N			= read();
		adNode		= new Node[N+1];
		isReverse	= new int[N-1];
		reverseCnt	= new int[N+1];
		visit		= new boolean[N+1];
		
		for(int i=0; i<N-1; i++)
		{
			int a		= read();
			int b		= read();
			adNode[a]	= new Node(b, i, 0, adNode[a]);
			adNode[b]	= new Node(a, i, 1, adNode[b]);
		}
		// 1번 노드의 역방향 개수를 담는다.
		visit[1] = true;
		reverseCnt[1] = reverse_edge_DFS(1);
		
		// 1번노드의 역방향 개수를 활용해 모든 노드의 역방향 개수를 구한다(내가갈때 정방향이면 상대 노드는 역방향간선이 + 1, 내가 갈 때 역방향이면 상대 노드는 역방향 간선이 -1이된다)
		visit[1] = false;	// 이미 다 true기 때문에 false로함
		all_reverse_edge_DFS(1, reverseCnt[1]);
		
		// 역방향 개수가 가장 작은 노드를 찾는다.
		int minNode = 0;
		int minReverseEdge = 1<<30;
		for(int i=1; i<=N; i++)
			if(reverseCnt[i] < minReverseEdge)
			{
				minReverseEdge = reverseCnt[i];
				minNode = i;
			}
		
		// 역방향 개수가 가장 작은 노드를 DFS로 탐색하며 결과를 담는다.
		visit[minNode] = true;
		find_result_DFS(minNode);
		
		StringBuilder sb = new StringBuilder();
		for(int i : isReverse)
			sb.append(i);
		System.out.print(sb.toString());
	}
	public static int reverse_edge_DFS(int node) {
		int cnt = 0;
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
			{
				visit[next.node] = true; 
				cnt += reverse_edge_DFS(next.node) + next.isReverse;
			}
		return cnt;
	}
	public static void all_reverse_edge_DFS(int node, int cnt) {
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(visit[next.node])
			{
				visit[next.node] = false;// 첫번째 DFS에서 이미 다 true기 때문에 false로함
				reverseCnt[next.node] = reverseCnt[node] + (next.isReverse == 0 ? 1 : -1);
				all_reverse_edge_DFS(next.node, reverseCnt[next.node]);
			}
	}
	public static void find_result_DFS(int node) {
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
			{
				visit[next.node] = true;
				isReverse[next.idx] = next.isReverse;	// 역방향이면 next.isReverse가 1이된다. 해당 간선이 역방향이라면 자연스럽게 1이담기게됨
				find_result_DFS(next.node);
			}
	}
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}