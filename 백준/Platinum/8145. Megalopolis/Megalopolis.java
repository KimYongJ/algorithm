//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/8145
//1초 128MB

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
	
	static int N, cnt;
	static int[] tree, lazy, depth, find;
	static int[][] range;
	static Node[] adNode;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N		= read();
		tree	= new int[N * 4];
		lazy	= new int[N * 4];
		depth	= new int[N + 1];
		find	= new int[N + 1];
		adNode	= new Node[N + 1];
		range	= new int[N + 1][2];
		
		for(int i=1; i<N; i++)
		{
			int a = read();
			int b = read();
			adNode[a] = new Node(b, adNode[a]);
		}
		
		DFS(1, 0);
		
		init(1, 1, N);
		
		int T = N + read() - 1;
		while(T-->0)
		{
			st = new StringTokenizer(br.readLine());
			if("W".equals(st.nextToken()))
			{
				int idx = Integer.parseInt(st.nextToken());
				sb.append(query(1, 1, N, range[idx][0]))
					.append('\n');
			}
			else
			{
				st.nextToken();
				int idx = Integer.parseInt(st.nextToken());
				update(1, 1, N, range[idx][0], range[idx][1]);
			}
			
		}
		System.out.print(sb);
	}
	public static void update(int treeNode, int s, int e, int left, int right) {
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return;
		if(left<=s && e<=right)
		{
			lazy[treeNode] = -1;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right);
		update(treeNode << 1 | 1, mid + 1, e, left, right);
	}
	public static int query(int treeNode, int s, int e, int idx) {
		propagate(treeNode, s, e);
		if(idx < s || e < idx)
			return 0;
		if(s == e)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		return query(treeNode << 1, s, mid, idx)
				+ query(treeNode << 1 | 1, mid + 1, e, idx);
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			// s는 트리에서의 노드번호 이기 때문에 깊이를 가져오려면, 깊이를 담을 때 넣은 노드번호를 갖고와야 한다.
			tree[treeNode] = depth[find[s]]; 
			return;
		}
		
		int mid = (s + e) >> 1;

		init(treeNode << 1, s, mid);
		init(treeNode << 1 | 1, mid + 1, e);
	}
	public static void DFS(int node, int dep) {
		range[node][0] = ++cnt; // node에 트리에서 사용할 번호(cnt)를 맵핑
		find[cnt] = node;		// 트리에서 사용할번호(cnt)에 node번호 매핑(init함수를 위해)
		depth[node] = dep;
		
		for(Node next=adNode[node]; next!=null; next=next.next)
			DFS(next.node, dep + 1);
		
		range[node][1] = cnt;
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] += lazy[treeNode];
			if(s != e)
			{
				lazy[treeNode << 1]		+= lazy[treeNode];
				lazy[treeNode << 1 | 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
