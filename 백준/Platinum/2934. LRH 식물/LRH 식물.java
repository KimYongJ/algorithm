//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2934
//1초 / 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static final int MAX = 100_000;
	static int N;
	static int[] lazy, tree;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N	= Integer.parseInt(br.readLine());
		tree= new int[MAX<<2];
		lazy= new int[MAX<<2];
		
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int cnt1 = query(1, 1, MAX, l);
			int cnt2 = query(1, 1, MAX, r);
			if(cnt1 > 0)
				update(1, 1, MAX, l, l, -cnt1);
			if(cnt2 > 0)
				update(1, 1, MAX, r, r, -cnt2);
			
			sb.append(cnt1 + cnt2).append('\n');
			
			if(1 < r - l)
				update(1, 1, MAX, l + 1, r - 1, 1);
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
		update(treeNode << 1, s, mid, left, right, value);
		update(treeNode << 1 | 1, mid + 1, e, left, right, value);
		tree[treeNode] = tree[treeNode << 1] + tree[treeNode << 1 | 1];
	}
	public static int query(int treeNode, int s, int e, int idx) {
		propagate(treeNode, s, e);
		if(e < idx || idx < s)
			return 0;
		if(s == e)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		return query(treeNode << 1, s, mid, idx) + 
				query(treeNode << 1 | 1, mid + 1, e, idx);
		
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] += (e - s + 1)*lazy[treeNode];
			if(s!=e) {
				lazy[treeNode << 1]		+= lazy[treeNode];
				lazy[treeNode << 1 | 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
}