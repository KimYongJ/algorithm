//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16975
//2초 / 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static long[] arr, tree, lazy;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N	= Integer.parseInt(br.readLine());	// 1<=십만
		arr = new long[N+1];
		tree= new long[N<<2];
		lazy= new long[N<<2];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());	// 1<=백만
		
		init(1, 1, N);
		
		int T = Integer.parseInt(br.readLine());	// 1<=십만
		while(T-->0)
		{
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			if(f == 1)
			{
				int l = Integer.parseInt(st.nextToken());	//1<=N
				int r = Integer.parseInt(st.nextToken());	//1<=N
				int k = Integer.parseInt(st.nextToken());	//|백만|
				
				update(1, 1, N, l, r, k);
			}
			else
			{
				int t = Integer.parseInt(st.nextToken());
				sb.append(query(1, 1, N, t, t)).append('\n');
			}
		}
		System.out.print(sb);
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode] = arr[s];
			return;
		}
		int nextNode= treeNode << 1;
		int mid		= (s + e) >> 1;
		init(nextNode, s, mid);
		init(nextNode | 1, mid + 1, e);
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
	public static void update(int treeNode, int s, int e, int left, int right, int diff) {
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return;
		if(left <= s && e <= right)
		{
			lazy[treeNode] = diff;
			propagate(treeNode, s, e);
			return;
		}
		
		int nextNode= treeNode << 1;
		int mid		= (s + e) >> 1;
		
		update(nextNode, s, mid, left, right, diff);
		update(nextNode | 1, mid + 1, e, left, right, diff);
	}
	public static long query(int treeNode, int s, int e, int left, int right) {
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return 0;
		if(left <= s && e <= right)
			return tree[treeNode];
		
		int nextNode= treeNode << 1;
		int mid 	= (s + e) >> 1;
		
		return query(nextNode, s, mid, left, right) 
				+ query(nextNode | 1, mid + 1, e, left, right);
	}
}