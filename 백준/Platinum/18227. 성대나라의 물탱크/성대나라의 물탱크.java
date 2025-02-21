//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/18227
//1초 256MB
class Node{
	int node;
	Node next;
	Node(int node, Node next){
		this.node = node;
		this.next = next;
	}
}
class Main{
	
	static int N, S, cnt;
	static int[][] range;
	static int[] depth;
	static Node[] adNode;
	static long[] tree;
	static boolean[] visit;
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		N		= read();// 도시 수(1<=이십만)
		S		= read();// 수도의 번호(1<=N)
		adNode	= new Node[N + 1];
		range	= new int[N + 1][2];
		depth	= new int[N + 1];
		tree	= new long[N * 4];
		visit	= new boolean[N + 1];
		
		for(int i=1; i<N; i++)
		{
			int a = read();
			int b = read();
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		visit[S] = true;
		DFS(S, 1);
		
		int T = read();
		
		while(T-->0)
		{
			int f = read();
			int i = read();
			if(f == 1) {
				update(1, 1, N, range[i][0]);
			}
			else {
				long cnt = query(1, 1, N, range[i][0], range[i][1]);
				sb.append(depth[i] * cnt).append('\n');
			}
		}
		System.out.print(sb);
	}
	public static long query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		if(left <= s && e <= right)
			return tree[treeNode];
		int mid = (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, left, right) + query(treeNode << 1 | 1, mid + 1, e, left, right);
	}
	public static void update(int treeNode, int s, int e, int idx) {
		if(e < idx || idx < s)
			return;
		if(s == e)
		{
			tree[treeNode]++;
			return;
		}
		int mid = (s + e) >> 1;
		update(treeNode << 1, s, mid, idx);
		update(treeNode << 1 | 1, mid + 1, e, idx);
		tree[treeNode] = tree[treeNode << 1] + tree[treeNode << 1 | 1];
	}
	public static void DFS(int node, int dep) {
		depth[node] = dep;
		range[node][0] = ++cnt;
		for(Node next=adNode[node]; next!=null; next=next.next)
		{
			if(!visit[next.node])
			{
				visit[next.node] = true;
				DFS(next.node, dep + 1);
			}
		}
		range[node][1] = cnt;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}

