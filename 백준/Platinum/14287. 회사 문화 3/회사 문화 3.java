//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/14287
//2초 / 512MB

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
	static int[] tree;
	static Node[] adNode;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N		= Integer.parseInt(st.nextToken());
		M		= Integer.parseInt(st.nextToken());
		tree	= new int[N<<2];
		range	= new int[N + 1][2];
		adNode	= new Node[N + 1];
		
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		for(int i=2; i<=N; i++)
		{
			int parent = Integer.parseInt(st.nextToken());
			adNode[parent] = new Node(i, adNode[parent]);
		}
		
		DFS(1);
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int node = Integer.parseInt(st.nextToken());
			if(f == 1)
			{
				int w = Integer.parseInt(st.nextToken());
				update(1, 1, N, range[node][0], w);
			}
			else
			{
				sb.append(query(1, 1, N, range[node][0], range[node][1]))
					.append('\n');
			}
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
}