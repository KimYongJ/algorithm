//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5829
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int N, M, K, tree[][], endPoint[], direct[], dist[];
	static int cycleDist, cycleCnt, cycleNode;
	static Node adNode[];
	static boolean visit[];
	
	public static void main(String[] args)throws Exception{
		N		= read();	// 항구(1<=천)
		M		= read();	// 방향 개수
		K		= read();	// 반복 횟수(1<=십억)
		tree	= new int[N+1][2];					// 최초입력되는 인접 노드
		endPoint= new int[N+1];						// 각 노드가 도착하는 노드의번호를 담을 배열
		direct	= new int[M];						// 방향을 저장할 배열
		adNode	= new Node[N+1];					// 방향개수만큼 한 사이클을 돈 후 오게되는 노드와 시작노드를 연결하여 구간을 줄임
		visit	= new boolean[N+1];					// 최종 결과 탐색시 사이클을 찾을 때 사용
		dist	= new int[N+1];						// 사이클을 찾을 때 까지 걸리는 거리를 담을 배열
		
		for(int i=1; i<=N; i++)
		{
			tree[i][0] = read();	// 왼쪽 노드
			tree[i][1] = read();	// 오른쪽노드
		}
		
		for(int i=0; i<M; i++)
		{
			direct[i] = System.in.read() == 'L' ? 0 : 1;// 왼쪽은 true, 오른쪽은 false
			System.in.read();
		}
		// 각 노드에서 최종적으로 도착하는 노드를 endPoint배열에 저장한다.
		for(int i=1; i<=N; i++)
			make_adNode_DFS(i, i, 0);	
		
		// adNode를 K번 DFS를 돌았을 때 결과가 답이다.
		find_cycle_DFS(1, 0);
		
		K -= cycleDist;
		K %= cycleCnt;
		
		DFS(cycleNode, 0);
	}
	// 시작부터 사이클시작 노드까지, 사이클 시작노드는 자기자신으로 돌아오기까지 를 기록한다.
	public static void find_cycle_DFS(int nowNode, int depth){
		if(depth == K)
		{
			System.out.print(nowNode);
			System.exit(0);
		}
		if(dist[nowNode] != 0)
		{
			cycleNode	= nowNode;
			cycleDist	= dist[nowNode];
			cycleCnt	= depth - dist[nowNode];
			return;
		}
		dist[nowNode] = depth;	
		find_cycle_DFS(adNode[nowNode].node, depth + 1);
	}
	// 최종 0부터 K까지 구한다. 이 때 K는 사이클이 제거되어 작은 숫자로 이미 수정되어있다.
	public static void DFS(int nowNode, int depth){
		if(depth == K)
		{
			System.out.print(nowNode);
			return;
		}
		DFS(adNode[nowNode].node, depth + 1);
	}
	// 각노드마다 M번 돌았을 때 방문하는 노드를 기록한다. 기록할 때 연결 노드를 미리 만든다.
	public static void make_adNode_DFS(int startNode, int nowNode, int depth)
	{
		if(depth == M)
		{
			// startNode에서 nowNode로 바로 갈 수 있게 탐색 구간을 줄임
			adNode[startNode] = new Node(nowNode, adNode[startNode]);
			return;
		}
		int nextNode = tree[nowNode][direct[depth]];
		make_adNode_DFS(startNode, nextNode, depth + 1);
	}
	// 빠른 입력을 위한 함수
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}