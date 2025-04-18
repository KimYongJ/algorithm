//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/5916
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
	
	static int N, M, H;
	static int dpth = 0;
	static int[] depth;
	static int[][] range, parent;
	static long[] tree;
	static Node adNode[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N		= Integer.parseInt(st.nextToken());// 농장의 수N(1<=100,000)
		M		= Integer.parseInt(st.nextToken());// 쿼리수 M(1<=100,000)
		H		= (int)(Math.log(N) / Math.log(2));
		depth	= new int[N + 1];
		parent	= new int[N + 1][H + 1];
		tree	= new long[N * 4];
		range	= new int[N + 1][2];
		adNode	= new Node[N + 1];
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		
		dfs_euler(1, 0, 0);
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			char cmd= st.nextToken().charAt(0);
			int L	= Integer.parseInt(st.nextToken());
			int R	= Integer.parseInt(st.nextToken());

			if(cmd == 'P')	// 해당 범위 나무를 심음
			{
				int nodeLCA = getLCA(L, R);
				// update를 할 때, 주어진 노드를 트리에서의 노드로 변경한다. 이 트리에서의 노드는 euler 함수를돌면서 구한 것
				update( 1, 1, N, range[L][0], 1 );
				update( 1, 1, N, range[R][0], 1 );
				update( 1, 1, N, range[nodeLCA][0], -2);
			}
			else			// 해당 범위 개수 출력
			{
				int node = range[L][0] < range[R][0] ? R : L;
				
				long ans = query( 1, 1, N, range[node][0], range[node][1]);
				
				sb.append(ans)
					.append('\n');
			}
		}
		System.out.print(sb);
	}
	public static long query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, left, right)
				+ query(treeNode << 1 | 1, mid + 1, e, left, right);
	}
	public static void update(int treeNode, int s, int e, int targetIdx, int value) {
		if(targetIdx < s || e < targetIdx)
			return;
		
		tree[treeNode] += value;
		
		if(s != e)
		{
			int mid = (s + e) >> 1;
			update(treeNode << 1, s, mid, targetIdx, value);
			update(treeNode << 1 | 1, mid + 1, e, targetIdx, value);
		}
	}
	public static int getLCA(int node1, int node2) {
		if(depth[node1] < depth[node2])	// node1을 깊이가 더 큰 것으로 변환
		{
			int tmp = node1;
			node1 = node2;
			node2 = tmp;
		}
		// 깊이가 같아질 때까지 node1의 깊이를 줄임
		int diff = depth[node1] - depth[node2];
		for(int k=0; k<=H; k++)
		{
			int bit = 1<<k;
			if((diff & bit) != 0)
			{
				node1 = parent[node1][k];
			}
			else if(bit > diff)
				break;
		}
		
		if(node1 == node2)	// 두노드가 같다면 그대로 리턴
			return node1;

		for(int k=H; k>=0; k--)
		{
			if(parent[node1][k] != parent[node2][k])
			{
				node1 = parent[node1][k];
				node2 = parent[node2][k];
			}
		}
		
		return parent[node1][0];
	}
	public static void dfs_euler(int node, int parentNode, int treeDepth) {
		range[node][0]	= ++dpth;		// 세그먼트 트리에서의 노드번호
		depth[node]		= treeDepth;	// 해당 노드의 깊이
		parent[node][0]	= parentNode;	// 원래 노드번호의 부모노드 저장
		
		// 2^k 조상 정보 채우기
		for(int k=1; k<=H; k++)
			// parent[node][0]은 1단계위 조상, [1]은 2단계 위 조상, [2]는 4단계위 조상, [3]은 8단계 위조상이 저장된다.
			// 즉 parent[node][k] = 2^(k) 조상이 저장된다.
			parent[node][k] = parent[ parent[node][k-1] ][k - 1];
		
		for(Node next = adNode[node]; next != null; next=next.next)
			if(next.node != parentNode)
				dfs_euler(next.node, node, treeDepth + 1);

		range[node][1] = dpth;	// 세그먼트 트리에서의 노드번호
	}
}
//4 6		// 농장의 수N(1<=100,000), 쿼리수 M(1<=100,000)
//1 4
//2 4
//3 4
//P 2 3	// u~v 사이 모든 도로에 나무를 심흠
//P 1 3
//Q 3 4	// u~v 사이 모든 도로에 존재하는 나무의 합을 출력
//P 1 4
//Q 2 4
//Q 1 4
////답
//2
//1
//2