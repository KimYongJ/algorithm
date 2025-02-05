//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14245
//2초 / 512mb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static int[] arr, tree, lazy;
	
	public static void main(String[] args)throws Exception{ 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		tree= new int[N<<2];
		lazy= new int[N<<2];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		init(1, 1, N);
		
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			if(t == 1)
			{
				int s = Integer.parseInt(st.nextToken())+1;
				int e = Integer.parseInt(st.nextToken())+1;
				int v = Integer.parseInt(st.nextToken());
				update(1, 1, N, s, e, v);
			}
			else
				sb.append(query(1, 1, N, Integer.parseInt(st.nextToken())+1))
					.append('\n');
		}
		System.out.print(sb);
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] ^= lazy[treeNode];
			if(s!=e)
			{
				lazy[treeNode << 1] ^= lazy[treeNode];
				lazy[treeNode << 1 | 1] ^= lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
	public static void update(int treeNode, int s, int e, int left, int right, int target) {
		propagate(treeNode, s, e);
		
		if(e<left || right < s)
			return;
		if(left<=s && e<=right)
		{
			lazy[treeNode] = target;
			return;
		}
		
		int nextNode= treeNode << 1;
		int mid		= (s + e) >> 1;
		
		update(nextNode, s, mid, left, right, target);
		update(nextNode | 1, mid + 1, e, left, right, target);
	}
	public static int query(int treeNode, int s, int e, int idx) {
		
		propagate(treeNode, s, e);
		
		if(e<idx || idx < s)
			return -1;
		if(s == e)
			return tree[treeNode];
		
		int nextNode= treeNode << 1;
		int mid		= (s + e) >> 1;
		int r1 = query(nextNode, s, mid, idx);
		int r2 = query(nextNode | 1, mid + 1, e, idx);
		
		return Math.max(r1, r2);
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
}