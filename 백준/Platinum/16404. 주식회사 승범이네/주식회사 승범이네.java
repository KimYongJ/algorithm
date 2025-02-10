//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/16404
//1초 / 256MB

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
	static int[] tree, lazy;
	static int[][] range;
	static Node adNode[];
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		N		= read();//1<=십만
		M		= read();//1<=십만
		adNode	= new Node[N+1];
		range	= new int[N+1][2];
		tree	= new int[N<<2];
		lazy	= new int[N<<2];
		
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
			if(f == 1)	// 이익/손해
			{
				int i = read(); // 1<=N
				int w = read(); // |만|
				update(1, 1, N, range[i][0], range[i][1], w);
			}
			else		// 잔액 출력
			{
				sb.append(query(1, 1, N, range[read()][0]))
					.append('\n');
			}
		}
		System.out.print(sb);
	}
	public static void update(int treeNode, int s, int e, int left, int right, int value) {
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return;
		
		if(left<=s && e<=right)
		{
			lazy[treeNode] += value;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right, value);
		update(treeNode << 1 | 1, mid + 1, e, left, right, value);
		tree[treeNode] = tree[treeNode << 1] + tree[treeNode << 1 | 1];
	}
	public static int query(int treeNode, int s, int e, int idx) {
		propagate(treeNode, s, e);
		
		if(e < idx || idx < s) return 0;
		if(s == e)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, idx) 
				+ query(treeNode << 1 | 1, mid + 1, e, idx);
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] += (e - s + 1) * lazy[treeNode];
			if(s!=e)
			{
				lazy[treeNode << 1] += lazy[treeNode];
				lazy[treeNode << 1 | 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
	public static void DFS(int node)
	{
		range[node][0] = ++cnt;
		for(Node next = adNode[node]; next!=null; next=next.next)
			DFS(next.node);
		range[node][1] = cnt;
	}
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
}