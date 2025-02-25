//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13030
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node;
	Node next;
	Node(int node, Node next){
		this.node=node;
		this.next=next;
	}
}
class Main{
	
	static final int MOD = 1_000_000_007;
	static int N, Q, cnt;
	static int[] depth;
	static long[] tree;
	static int[][] range;
	static long[][] lazy;
	static Node[] adNode;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N		= Integer.parseInt(st.nextToken());// 정점의개수(1<=삼십만)
		Q		= Integer.parseInt(st.nextToken());// 쿼리개수(1<=삼십만)
		tree	= new long[N * 4];
		depth	= new int[N + 1];
		lazy	= new long[N * 4][2];//더할 x, k, 누적된 k값
		range	= new int[N + 1][2];
		adNode	= new Node[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=2; i<=N; i++)
		{
			int parent = Integer.parseInt(st.nextToken());
			adNode[parent] = new Node(i, adNode[parent]);
		}
		
		DFS(1, 1);
		
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			if(f == 1)
			{
				int v = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				// v에 x - ((v의깊이 - 현재 노드의깊이)*k)
				update(1, 1, N, range[v][0], range[v][1], x, k);
			}
			else
			{
				int idx = Integer.parseInt(st.nextToken());
				long value = query(1, 1, N, range[idx][0]) % MOD;
				sb.append(value)
					.append('\n');
			}
		}
		System.out.print(sb);
	}
	public static long query(int treeNode, int s, int e, int idx) {
		
		propagate(treeNode, s, e);
		
		if(idx < s || e< idx)
			return 0;
		if(s == e)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		return query(treeNode << 1, s, mid, idx) 
				+ query(treeNode << 1 | 1, mid + 1, e, idx);
	}
	public static void update(int treeNode, int s, int e, int left, int right, long x, long k) {
		propagate(treeNode, s, e);
		if(e < left || right < s)
			return;
		if(left<=s && e<=right)
		{
			if(s == e)
			{
//				tree[treeNode] += (((x+depth[left]*k))%MOD) - ((depth[s]*k)%MOD);
//				tree[treeNode] = tree[treeNode] % MOD;
				tree[treeNode] = (tree[treeNode] + ((x + (long)depth[left] * k) % MOD - ((long)depth[s] * k) % MOD + MOD)) % MOD;
			}
			else
			{
//				lazy[treeNode][0] += (x + depth[left] * k)%MOD;
//				lazy[treeNode][1] += k;
//				lazy[treeNode][0] = lazy[treeNode][0] %MOD;
//				lazy[treeNode][1] = lazy[treeNode][1] % MOD;
                lazy[treeNode][0] = (lazy[treeNode][0] + (x + (long)depth[left] * k) % MOD) % MOD;
                lazy[treeNode][1] = (lazy[treeNode][1] + k) % MOD;
			}
			return;
		}
		int mid = (s + e) >> 1;
		update(treeNode << 1, s, mid, left, right, x, k);
		update(treeNode << 1 | 1, mid + 1, e, left, right, x, k);
	}
	public static void propagate(int treeNode, int s, int e)
	{
		if(lazy[treeNode][0] != 0 || lazy[treeNode][1] != 0)
		{
			if(s == e)
			{
//				tree[treeNode] += lazy[treeNode][0] - ((depth[s] * lazy[treeNode][1])%MOD);
//				tree[treeNode] = tree[treeNode] % MOD;
				  tree[treeNode] = (tree[treeNode] + lazy[treeNode][0] - ((long)depth[s] * lazy[treeNode][1]) % MOD + MOD) % MOD;
			}
			else
			{
//				lazy[treeNode << 1][0] += lazy[treeNode][0];
//				lazy[treeNode << 1][1] += lazy[treeNode][1];
//				lazy[treeNode << 1 | 1][0] += lazy[treeNode][0];
//				lazy[treeNode << 1 | 1][1] += lazy[treeNode][1];
//				lazy[treeNode << 1][0] = lazy[treeNode << 1][0] % MOD;
//				lazy[treeNode << 1][1] = lazy[treeNode << 1][1]% MOD;
//				lazy[treeNode << 1 | 1][0] = lazy[treeNode << 1 | 1][0]% MOD;
//				lazy[treeNode << 1 | 1][1] = lazy[treeNode << 1 | 1][1]% MOD;
                int leftChild = treeNode << 1, rightChild = treeNode << 1 | 1;
                lazy[leftChild][0] = (lazy[leftChild][0] + lazy[treeNode][0]) % MOD;
                lazy[leftChild][1] = (lazy[leftChild][1] + lazy[treeNode][1]) % MOD;
                lazy[rightChild][0] = (lazy[rightChild][0] + lazy[treeNode][0]) % MOD;
                lazy[rightChild][1] = (lazy[rightChild][1] + lazy[treeNode][1]) % MOD;
			}
			lazy[treeNode][0] = lazy[treeNode][1] = 0;
		}
	}
	public static void DFS(int node, int dep) {
		range[node][0] = ++cnt;
		depth[cnt] = dep;
		
		for(Node next=adNode[node]; next!=null; next=next.next)
			DFS(next.node, dep + 1);
		
		range[node][1] = cnt;
	}
}