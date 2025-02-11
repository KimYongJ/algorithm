//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2820
//1초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int now;
	Node next;
	Node(int now, Node next){
		this.now = now;
		this.next = next;
	}
}
class Main{
	
	static int cnt;
	static int N, M;
	static int[] cost;
	static int[][] range;
	static int[] tree, lazy;
	static Node[] adNode;
	static boolean[] visit;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N		= Integer.parseInt(st.nextToken());
		M		= Integer.parseInt(st.nextToken());
		cost	= new int[N + 1];
		adNode	= new Node[N+1];
		range	= new int[N + 1][2];
		visit	= new boolean[N + 1];
		tree	= new int[N<<2];
		lazy	= new int[N<<2];
		
		
		cost[1] = Integer.parseInt(br.readLine());
		for(int i=2; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			cost[i] = Integer.parseInt(st.nextToken());
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
			char flag = st.nextToken().charAt(0);
			if(flag == 'p')
			{
				int l = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				update(1, 1, N, range[l][0]+1, range[l][1], x);
			}
			else {
				int idx = Integer.parseInt(st.nextToken());
				sb.append(cost[idx] + query(1, 1, N, range[idx][0])).append('\n');
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
			lazy[treeNode] = value;
			propagate(treeNode, s, e);
			return;
		}
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right, value);
		update(treeNode << 1 | 1, mid + 1, e, left, right, value);
	}
	public static int query(int treeNode, int s, int e, int idx) {
		
		propagate(treeNode, s, e);
		
		if(e < idx || idx < s)
			return 0;
		if(s == e)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, idx) 
				+ query(treeNode <<1 | 1, mid + 1, e, idx);
	}

	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] += (e - s + 1) * lazy[treeNode];
			if(s != e)
			{
				lazy[treeNode << 1]		+= lazy[treeNode];
				lazy[treeNode << 1 | 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
	public static void DFS(int node) {
		range[node][0] = ++cnt;
		for(Node next=adNode[node]; next!=null; next=next.next)
		{
			if(!visit[next.now])
			{
				visit[next.now] = true;
				DFS(next.now);
			}
		}
		range[node][1] = cnt;
	}
	
}