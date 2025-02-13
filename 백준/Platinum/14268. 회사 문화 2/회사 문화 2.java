//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14268
//5초 / 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
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
	static int[] tree, lazy;
	static Node[] adNode;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		N		= Integer.parseInt(st.nextToken());//직원수n(2≤100,000)
		M		= Integer.parseInt(st.nextToken());//직원수n(2≤100,000)
		tree	= new int[N<<2];
		lazy	= new int[N<<2];
		range	= new int[N + 1][2];
		adNode	= new Node[N + 1];
		st = new StringTokenizer(br.readLine().trim());
		st.nextToken();
		for(int i=2; i<=N; i++)
		{
			int parent = Integer.parseInt(st.nextToken());
			adNode[parent] = new Node(i, adNode[parent]);
		}
		
		DFS(1);
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine().trim());
			int f = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			if(f == 1)
			{
				int w = Integer.parseInt(st.nextToken());//1≤1,000
				update(1, 1, N, range[n][0], range[n][1], w);
			}
			else
			{
				sb.append(query(1, 1, N, range[n][0]))
					.append('\n');
			}
		}
		System.out.print(sb);
	}
	public static void update(int treeNode, int s, int e, int left, int right, int value) {
		propagate(treeNode, s, e);
		if(e < left || right < s)
			return;
		if(left <= s && e <= right)
		{
			lazy[treeNode] += value;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right, value);
		update(treeNode << 1 | 1, mid + 1, e, left, right, value);
	}
	public static int query(int treeNode, int s, int e, int idx) {
		propagate(treeNode, s, e);
		
		if(idx < s || e < idx)
			return 0;
		if(s == e)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return query(treeNode << 1, s , mid, idx) +
				query(treeNode << 1 | 1, mid + 1, e, idx);
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] += lazy[treeNode];
			if(s!=e)
			{
				lazy[treeNode << 1] += lazy[treeNode];
				lazy[treeNode << 1 | 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
	public static void DFS(int node) {
		range[node][0] = ++cnt;
		for(Node next=adNode[node]; next!=null; next=next.next)
			DFS(next.node);
		range[node][1] = cnt;
	}
}
