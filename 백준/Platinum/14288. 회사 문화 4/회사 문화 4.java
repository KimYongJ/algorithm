//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14288
//2초 512MB
class Node{
	int node;
	Node next;
	Node(int node, Node next){
		this.node = node;
		this.next = next;
	}
}
class Main{
	
	static int N, M, idx;
	static int[] lazy, treeDown, treeUp;
	static int[][] range;
	static Node[] adNode;
	static boolean down;
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		N		= read();// 직원수 2<=십만
		M		= read();// 쿼리수 2<=십만
		lazy	= new int[N<<2];
		treeDown= new int[N<<2];	// 특정 부분까지 느리게 갱신되는 세그트리
		treeUp	= new int[N<<2];	// 특정범위까지 누적합
		range	= new int[N + 1][2];
		adNode	= new Node[N + 1];
		down	= true;// 초기는 위에서 아래로 칭찬
		
		read();
		for(int i=2; i<=N; i++)
		{
			int parent = read();
			adNode[parent] = new Node(i, adNode[parent]);
		}
		
		DFS(1);
		
		while(M-->0)
		{
			int f = read();
			if(f == 1)
			{
				int nd	= read();
				int v	= read();
				if(down)// 아래 방향일 때는 자기 포함 부하들 다 플러스 , 문제에 제대로 명시하지 않네..;;
					update_down(1, 1, N, range[nd][0], range[nd][1], v);
				else	// 위방향 일 때는 해당 노드 까지 누적합 갱신
					update_up(1, 1, N, range[nd][0], v);
			}
			else if(f == 2)
			{
				int nd	= read();
				int down= query_down(1, 1, N, range[nd][0]);
				int up	= query_up(1, 1, N, range[nd][0], range[nd][1]);
				
				sb.append(down + up)
					.append('\n');
			}
			else 
				down = !down;
		}
		System.out.print(sb);
	}
	public static int query_up(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return treeUp[treeNode];
		
		int mid = (s + e) >> 1;
		
		return query_up(treeNode << 1, s, mid, left, right) + 
				query_up(treeNode << 1 | 1, mid + 1, e , left, right);
	}
	public static void update_up(int treeNode, int s, int e, int idx, int v) {
		if(idx < s || e < idx)
			return;
		if(s == e)
		{
			treeUp[treeNode] += v;
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update_up(treeNode << 1, s, mid, idx, v);
		update_up(treeNode << 1 | 1, mid + 1, e, idx, v);
		treeUp[treeNode] = treeUp[treeNode << 1] + treeUp[treeNode << 1 | 1];
	}
	public static int query_down(int treeNode, int s, int e, int idx) {
		propagate(treeNode, s , e);
		if(idx < s || e < idx)
			return 0;
		if(s == e)
			return treeDown[treeNode];
		
		int mid = (s + e) >> 1;
		
		return query_down(treeNode << 1, s, mid, idx) + 
				query_down(treeNode << 1 | 1, mid + 1, e, idx);
	}
	public static void update_down(int treeNode, int s, int e, int left, int right, int v) {
		propagate(treeNode, s, e);
		if(e < left || right < s)
			return;
		if(left<=s && e<=right)
		{
			lazy[treeNode] += v;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update_down(treeNode << 1, s, mid, left, right, v);
		update_down(treeNode << 1 | 1, mid + 1, e, left, right, v);
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			treeDown[treeNode] += lazy[treeNode];
			if(s != e)
			{
				lazy[treeNode << 1] += lazy[treeNode];
				lazy[treeNode << 1 | 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
	public static void DFS(int node) {
		range[node][0] = ++idx;
		for(Node next=adNode[node]; next!=null; next=next.next)
			DFS(next.node);
		range[node][1] = idx;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}