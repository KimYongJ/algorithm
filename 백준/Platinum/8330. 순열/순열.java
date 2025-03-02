//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/8330
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static int[] freq, arr;
	static long[] tree, lazy, M;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N		= Integer.parseInt(br.readLine());
		arr		= new int[N + 1];
		freq	= new int[N + 1];
		tree	= new long[N * 4];
		lazy	= new long[N * 4];
		M		= new long[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
			freq[arr[i]]++;
		}
		
		long psum = 0;
		for(int i=1; i<=N; i++)
		{
			psum += freq[i];
			M[i] = psum - i;
		}
		
		Runnable check = () ->{
			long max = query(1, 1, N, 1, N);
			sb.append(max<=0 ? "TAK\n" : "NIE\n");
		};
		
		init(1, 1, N);
		
		check.run();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			st = new StringTokenizer(br.readLine());
			int i	= Integer.parseInt(st.nextToken());
			int old = arr[i];
			arr[i]	= Integer.parseInt(st.nextToken());
			
			update(1, 1, N, old, N, -1);
			update(1, 1, N, arr[i], N, 1);
			
			check.run();
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
		
		update(treeNode*2, s, mid, left, right, value);
		update(treeNode*2 + 1, mid + 1, e, left, right, value);
		
		tree[treeNode] = Math.max(tree[treeNode*2], tree[treeNode*2+1]);
	}
	public static long query(int treeNode, int s, int e, int left, int right) {
		
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return Long.MIN_VALUE;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		long l = query(treeNode*2, s, mid, left, right);
		long r = query(treeNode*2+1, mid + 1, e, left, right);
		
		return Math.max(l, r);
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode] = M[s];
			return;
		}
		
		int mid = (s + e) >> 1;
		
		init(treeNode * 2, s, mid);
		init(treeNode * 2 + 1, mid + 1, e);
		
		tree[treeNode] = Math.max(tree[treeNode*2], tree[treeNode*2+1]);
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] += lazy[treeNode];
			if(s!=e)
			{
				lazy[treeNode * 2]		+= lazy[treeNode];
				lazy[treeNode * 2 + 1]	+= lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
}