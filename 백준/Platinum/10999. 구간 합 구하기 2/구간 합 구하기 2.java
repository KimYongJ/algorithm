//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10999
//2초 / 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static long[] arr, tree, lazy;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 수 개수 1<=백만
		int M = Integer.parseInt(st.nextToken());	// 변경 일어나는 개수 1<=만
		int K = Integer.parseInt(st.nextToken());	// 구간합 개수 1<=만
		int S = M + K;
		arr = new long[N+1];
		tree= new long[N<<2];
		lazy= new long[N<<2];
		
		for(int i=1; i<=N; i++)
			arr[i] = Long.parseLong(br.readLine());	// long MAX, MIN
		
		init(1, 1, N);
		
		StringBuilder sb = new StringBuilder();
		while(S-->0)
		{
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			if(t == 1)
			{
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				long v = Long.parseLong(st.nextToken());
				
				update(1, 1, N, l, r, v);
			}
			else	// 해당 구간의 구간 합
			{
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				sb.append(query(1, 1, N, l, r)).append('\n');
			}
		}
		
		System.out.print(sb);
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0) {
			tree[treeNode] += (e - s + 1) * lazy[treeNode];
			if(s != e)
			{
				lazy[treeNode << 1] += lazy[treeNode];
				lazy[treeNode << 1 | 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
	public static void update(int treeNode, int s, int e, int left, int right, long value) {
		
		propagate(treeNode, s, e);
		
		if(e<left || right < s)
			return;
		if(left<=s && e<=right)
		{
			lazy[treeNode] += value;
			propagate(treeNode, s, e);
			return;
		}
		
		int nextNode = treeNode << 1;
		int mid = (s + e) >> 1;
		update(nextNode, s, mid, left, right, value);
		update(nextNode | 1, mid + 1, e, left, right, value);
		
		tree[treeNode] = tree[nextNode] + tree[nextNode | 1];
	}
	public static long query(int treeNode, int s, int e, int left, int right)
	{
		propagate(treeNode, s, e);
		
		if(e <left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int nextNode = treeNode << 1;
		int mid = (s + e) >> 1;
		return query(nextNode, s, mid, left, right) + query(nextNode | 1, mid + 1, e, left, right);
	}
	public static long init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = arr[s];
		
		int nextNode= treeNode << 1;
		int mid		= (s + e) >> 1;
		
		return tree[treeNode] = init(nextNode, s, mid) 
								+ init(nextNode | 1, mid + 1, e); 
	}
}