//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/14287
//2초 / 512MB

class Node{
	int node;
	Node next;
	Node(int node, Node next){
		this.node = node;
		this.next = next;
	}
}
class Main{
	
	static int N, M, cnt;
	static int[][] range;
	static int[] tree;
	static Node[] adNode;
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		N		= read();
		M		= read();
		tree	= new int[N<<2];
		range	= new int[N + 1][2];
		adNode	= new Node[N + 1];
		
		read();
		for(int i=2; i<=N; i++)
		{
			int parent = read();
			adNode[parent] = new Node(i, adNode[parent]);
		}
		
		DFS(1);
		
		while(M-->0)
		{

			int f	= read();
			int node= read();
			
			if(f == 1)
				update(1, 1, N, range[node][0], read());
			else
				sb.append(query(1, 1, N, range[node][0], range[node][1]))
					.append('\n');
		}
		System.out.print(sb);
	}
	public static void update(int treeNode, int s, int e, int idx, int value) {
		if(idx < s || e < idx)
			return;
		if(s == e) {
			tree[treeNode] += value;
			return;
		}
		int mid = (s + e) >> 1;
		update(treeNode << 1, s, mid, idx, value);
		update(treeNode << 1 | 1, mid + 1, e, idx, value);
		tree[treeNode] = tree[treeNode << 1] + tree[treeNode << 1 | 1];
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		if(left <= s && e <= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		return query(treeNode << 1, s, mid, left, right)
				+ query(treeNode << 1 | 1, mid + 1, e, left, right);
	}
	public static void DFS(int node) {
		range[node][0] = ++cnt;
		
		for(Node next=adNode[node]; next != null; next=next.next)
			DFS(next.node);
		
		range[node][1] = cnt;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}