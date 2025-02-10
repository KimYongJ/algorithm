//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/16404
//1초 / 256MB

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
	static int[] tree, lazy;
	static int[][] range;
	static Node adNode[];
	static boolean visit[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());//1<=십만
		M		= Integer.parseInt(st.nextToken());//1<=십만
		adNode	= new Node[N+1];
		visit	= new boolean[N+1];
		range	= new int[N+1][2];
		tree	= new int[N<<2];
		lazy	= new int[N<<2];
		
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		for(int i=2; i<=N; i++)
		{
			int parent = Integer.parseInt(st.nextToken());
			adNode[parent] = new Node(i, adNode[parent]);
		}
		
		for(int i=1; i<=N; i++)
		{
			if(!visit[i])
			{
				visit[i] = true;
				DFS(i);
			}
		}
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			if(f == 1)	// 이익/손해
			{
				int i = Integer.parseInt(st.nextToken()); // 1<=N
				int w = Integer.parseInt(st.nextToken()); // |만|
				update(1, 1, N, range[i][0], range[i][1], w);
			}
			else		// 잔액 출력
			{
				sb.append(query(1, 1, N, range[Integer.parseInt(st.nextToken())][0]))
					.append('\n');
			}
		}
		System.out.print(sb);
	}
	public static void update(int treeNode, int s, int e, int left, int right, int value) {
		propagate(treeNode, s, e);
		if(e < left || right < s)
			return;
		if(left<=s && e<=right) {
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
		{
			if(!visit[next.node])
			{
				visit[next.node] = true;
				DFS(next.node);
			}
		}
		range[node][1] = cnt;
	}
}