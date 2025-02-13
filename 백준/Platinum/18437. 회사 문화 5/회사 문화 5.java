//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/18437
//2초 512MB
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
	
	static int N, idx;
	static int[] lazy, tree;
	static int[][] range;
	static Node[] adNode;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		lazy = new int[N << 2];
		tree = new int[N << 2];
		range = new int[N + 2][2];
		adNode = new Node[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		st.nextToken();
		for(int i=2; i<=N; i++)
		{
			int parent = Integer.parseInt(st.nextToken());
			adNode[parent] = new Node(i, adNode[parent]);
		}
		
		DFS(1);
		
		update(1, 1, N, 1, N, 1);
		
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());
			if(f==1)
			{
				update(1, 1, N, range[i][0] + 1, range[i][1], 1);
			}
			else if(f==2)
			{
				update(1, 1, N, range[i][0] + 1, range[i][1], 2);
			}
			else
			{
				sb.append(query(1, 1, N, range[i][0] + 1, range[i][1]))
					.append('\n');
			}
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
			if(lazy[treeNode] == 1)
			{
				tree[treeNode] = (e - s + 1);
				if(s != e)
				{
					lazy[treeNode << 1] = 1;
					lazy[treeNode << 1 | 1] = 1;
				}
			}
			else
			{
				tree[treeNode] = 0;
				if(s != e)
				{
					lazy[treeNode << 1] = 2;
					lazy[treeNode << 1 | 1] = 2;
				}
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
}