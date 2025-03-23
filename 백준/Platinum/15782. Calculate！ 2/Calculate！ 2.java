//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15782
//1초 512MB
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
	
	static int N, M, idx;
	static int[] arr, tree, lazy;
	static int[][] range;	// idx를 트리의 번호로 변경
	static Node[] adNode;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());	// 3<=십만
		M		= Integer.parseInt(st.nextToken());	// 3<=오십만
		adNode	= new Node[N + 1];
		arr		= new int[N + 1];
		tree	= new int[N * 4];
		lazy	= new int[N * 4];
		range	= new int[N + 1][2];

		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		
		dfs(1, 0);// 오일러투어로 주어진 노드 번호를 트리에서의 범위를 만든다.
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[range[i][0]] = Integer.parseInt(st.nextToken());// 0<=만
		
		init(1, 1, N);
		
		StringBuilder sb = new StringBuilder();
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			if(flag == 1)
				sb.append( query(1, 1, N, range[x][0], range[x][1]) ).append('\n');
			else
			{
				int y = Integer.parseInt(st.nextToken());
				update(1, 1, N, range[x][0], range[x][1], y);
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
			lazy[treeNode] = value;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		int nxt = treeNode << 1;
		
		update(nxt, s, mid, left, right, value);
		update(nxt | 1, mid + 1, e, left, right, value);
		
		tree[treeNode] = tree[nxt] ^ tree[nxt | 1];
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return 0;
		
		if(left <= s && e <= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		int nxt = treeNode << 1;
		
		return query(nxt, s, mid, left, right) ^
				query(nxt | 1, mid + 1, e, left, right);
	}
	public static int init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = arr[s];
		
		int mid = (s + e) >> 1;
		int nxt = treeNode << 1;
		
		return tree[treeNode] = 
					init(nxt, s, mid) ^ init(nxt | 1, mid + 1, e);
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			
			if((e - s + 1) % 2 != 0)
				tree[treeNode] ^= lazy[treeNode];
			
			if(s != e)
			{
				lazy[treeNode << 1]		^= lazy[treeNode];
				lazy[treeNode << 1 | 1] ^= lazy[treeNode];
			}
			
			lazy[treeNode] = 0;
		}
	}
	public static void dfs(int node, int prv) {
		range[node][0] = ++idx;
		
		for(Node next = adNode[node]; next!=null; next=next.next)
			if(next.node != prv)
				dfs(next.node, node);
		
		range[node][1] = idx;
	}
}