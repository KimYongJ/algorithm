//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15967
//1.5초 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, Q1, Q2;
	static long[] arr, tree, lazy;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N	= Integer.parseInt(st.nextToken());// 강화한 날의수(1<=백만)
		Q1	= Integer.parseInt(st.nextToken());// 변덕부린날의수(0<=백만)
		Q2	= Integer.parseInt(st.nextToken());// 출력하는 개수(0<=백만)
		arr	= new long[N + 1];
		tree= new long[N * 4];
		lazy= new long[N * 4];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());// i번째날에 케릭을 강화한 양(1<=15만)
		
		init(1, 1, N);
		
		Q1 += Q2;
		
		while(Q1-->0)
		{
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			if(f == 1)
				sb.append(query(1, 1, N, s, e)).append('\n');
			else
			{
				int v = Integer.parseInt(st.nextToken());
				update(1, 1, N, s, e, v);
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
		update(treeNode << 1, s, mid, left, right, value);
		update(treeNode << 1 | 1, mid + 1, e, left, right, value);
		tree[treeNode] = tree[treeNode << 1] + tree[treeNode << 1 | 1];
	}
	public static long query(int treeNode, int s, int e, int left, int right) {
		
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		int mid = (s + e) >> 1;
		return query(treeNode << 1, s, mid, left, right)
				+ query(treeNode << 1 | 1, mid + 1, e, left, right);
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e) {
			tree[treeNode] = arr[s];
			return;
		}
		int mid = (s + e) >> 1;
		init(treeNode << 1, s, mid);
		init(treeNode << 1 | 1, mid + 1, e);
		tree[treeNode] = tree[treeNode << 1] + tree[treeNode << 1 | 1];
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] += lazy[treeNode] * (e - s + 1);
			if(s != e)
			{
				lazy[treeNode << 1]		+= lazy[treeNode];
				lazy[treeNode << 1 | 1]	+= lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
}
