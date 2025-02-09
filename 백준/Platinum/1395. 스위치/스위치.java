//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1395
//1초 / 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, T;
	static int[] arr, tree, lazy;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[N + 1];
		tree= new int[N<<2];
		lazy= new int[N<<2];
		
		while(T-->0)
		{
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			if(f == 0)	// 스위치 반전
				update(1, 1, N, l, r);
			else		// 출력
				sb.append(query(1, 1, N, l, r)).append('\n');
		}
		System.out.print(sb);
	}
	public static void update(int treeNode, int s, int e,int left, int right) {
		
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return;
		
		if(left<=s && e<=right)
		{
			lazy[treeNode] ^= 1;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right);
		update(treeNode << 1 | 1, mid + 1, e, left, right);
		
		tree[treeNode] = tree[treeNode << 1] + tree[treeNode << 1 | 1];
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return 0;
		
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, left, right) + 
				query(treeNode << 1 | 1, mid + 1, e, left, right);
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] = (e - s + 1) - tree[treeNode];
			if(s != e)
			{
				lazy[treeNode << 1]		^= lazy[treeNode];
				lazy[treeNode << 1 | 1] ^= lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
}