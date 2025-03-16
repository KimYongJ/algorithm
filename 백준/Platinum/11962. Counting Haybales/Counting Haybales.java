//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11962
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	static final long MAX = 1L<<62;
	static long[] arr, lazy;
	static long[][] tree;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N	= Integer.parseInt(st.nextToken());
		int Q	= Integer.parseInt(st.nextToken());
		int len = N * 4;
		
		arr		= new long[N + 1];
		lazy	= new long[len];
		tree	= new long[len][2];// [0] = sum, [1] = min
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<len; i++)
			tree[i][1] = MAX;
		
		init(1, 1, N);
		
		StringBuilder sb = new StringBuilder();
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			char flag = st.nextToken().charAt(0);
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			if(flag == 'P')
			{
				int V = Integer.parseInt(st.nextToken());
				update(1, 1, N, A, B, V);
				
				continue;
			}
			
			long res[] = query(1, 1, N, A, B);
			
			sb.append(flag == 'S' ? res[0] : res[1])
				.append('\n');
		}
		System.out.print(sb);
	}
	public static long[] query(int treeNode, int s, int e, int left, int right) {
		
		propagate(treeNode, s, e);
		
		if(right < s || e < left)
			return new long[] {0, MAX};
		
		if(left <=s && e <= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		int next= treeNode << 1;
		
		long[] res1 = query(next, s, mid, left, right);
		long[] res2 = query(next | 1, mid + 1, e, left, right);
		
		return new long[] {res1[0] + res2[0], Math.min(res1[1], res2[1])};
	}
	public static void update(int treeNode, int s, int e, int left, int right, long value) {
		
		propagate(treeNode, s, e);
		
		if(right < s || e < left)
			return;
		
		if(left <=s && e <= right)
		{
			lazy[treeNode] = value;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		int next= treeNode << 1;
		
		update(next, s, mid, left, right, value);
		update(next | 1, mid + 1, e, left, right, value);
		
		tree[treeNode][0] = tree[next][0] + tree[next | 1][0];
		tree[treeNode][1] = Math.min( tree[next][1], tree[next | 1][1] );
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode][0] = tree[treeNode][1] = arr[s];
			return;
		}
		
		int mid = (s + e) >> 1;
		int next= treeNode << 1;
		
		init(next, s, mid);
		init(next | 1, mid + 1, e);
		
		tree[treeNode][0] = tree[next][0] + tree[next | 1][0];
		tree[treeNode][1] = Math.min(tree[next][1], tree[next | 1][1]);
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode][0] += (e - s + 1) * lazy[treeNode];
			tree[treeNode][1] += lazy[treeNode];
			if(s != e)
			{
				lazy[treeNode << 1]		+= lazy[treeNode];
				lazy[treeNode << 1 | 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
}