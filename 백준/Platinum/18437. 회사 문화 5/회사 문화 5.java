//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/18437
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
	
	static int N, idx;
	static int[] lazy, tree;
	static int[][] range;
	static Node[] adNode;
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		N		= read();
		lazy	= new int[N << 2];
		tree	= new int[N << 2];
		range	= new int[N + 2][2];
		adNode	= new Node[N + 1];
		
		read();
		for(int i=2; i<=N; i++)
		{
			int parent = read();
			adNode[parent] = new Node(i, adNode[parent]);
		}
		
		DFS(1);
		
		update(1, 1, N, 1, N, 1);
		
		int T = read();
		while(T-->0)
		{
			int f = read();
			int i = read();
			
			if(f==3)
				sb.append(query(1, 1, N, range[i][0] + 1, range[i][1]))
				.append('\n');
			else	
				update(1, 1, N, range[i][0] + 1, range[i][1], f);
		}
		System.out.print(sb);
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return 0;
		
		if(left<=s && e<= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, left, right) + 
				query(treeNode << 1 | 1, mid + 1, e, left, right);
	}
	public static void update(int treeNode, int s, int e, int left, int right, int value) {
		
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return;
		
		if(left <= s && e <= right)
		{
			lazy[treeNode] = value;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right, value);
		update(treeNode << 1 | 1, mid + 1, e, left, right, value);
		
		tree[treeNode] = tree[treeNode << 1] + tree[treeNode << 1 | 1];
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] = lazy[treeNode] == 1 ? (e - s + 1) : 0;

			if(s != e)
			{
				lazy[treeNode << 1]		= lazy[treeNode];
				lazy[treeNode << 1 | 1] = lazy[treeNode];
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