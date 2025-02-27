//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1161
//2초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Node implements Comparable<Node>{
	int s, e, m;
	Node(int s, int e, int m){
		this.s=s; this.e=e; this.m=m;
	}
	@Override
	public int compareTo(Node o) {
		return this.e - o.e;
	}
}
class Main{
	
	static int G, N, C, total;
	static int[] lazy, tree;
	static Node[] node;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		G		= Integer.parseInt(st.nextToken());// 그룹수(1<=오천)
		N		= Integer.parseInt(st.nextToken());// 정류장개수(1<=이만)
		C		= Integer.parseInt(st.nextToken());// 버스 최대 수용인원(1<=백)
		node	= new Node[G];
		tree	= new int[N << 2];
		lazy	= new int[N << 2];
		
		for(int i=0; i<G; i++)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());// 출발정류장
			int e = Integer.parseInt(st.nextToken());// 내릴정류장
			int m = Integer.parseInt(st.nextToken());// 인원
			node[i] = new Node(s,e,m);
		}
		
		Arrays.sort(node);
		
		for(Node now : node)
		{
			int max = query(1, 1, N, now.s, now.e - 1);
			int min = Math.min(now.m, C - max);
			if(0<min)
			{
				total += min;
				update(1, 1, N, now.s, now.e - 1, min);
			}
		}
		System.out.print(total);
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
		update(treeNode << 1 | 1, mid+1, e, left, right, value);
		
		tree[treeNode] = Math.max(tree[treeNode << 1], tree[treeNode << 1 | 1]);
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		propagate(treeNode, s, e);
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		int mid = (s + e) >> 1;
		int q1 = query(treeNode << 1, s, mid, left, right);
		int q2 = query(treeNode << 1 | 1, mid+1, e, left, right);
		return Math.max(q1, q2);
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
}