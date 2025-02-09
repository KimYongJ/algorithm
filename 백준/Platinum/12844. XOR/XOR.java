//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/12844
//2초 / 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static int[] tree, arr, lazy;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N	= Integer.parseInt(br.readLine());
		arr = new int[N + 1];
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
			int f = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken())+1;
			int r = Integer.parseInt(st.nextToken())+1;
			if(f == 1)
			{
				int v = Integer.parseInt(st.nextToken());
				update(1, 1, N, l, r, v);
			}
			else
			{
				sb.append(query(1, 1, N, l, r)).append('\n');
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
			lazy[treeNode] ^= value;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right, value);
		update(treeNode << 1 | 1, mid + 1, e, left, right, value);
		
		tree[treeNode] = tree[treeNode << 1] ^ tree[treeNode << 1 | 1];
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return 0;
		if(left <= s && e<= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		return query(treeNode << 1, s, mid, left, right) ^
				query(treeNode << 1 | 1, mid + 1, e, left, right);
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode] = arr[s];
			return;
		}
		int mid = (s + e) >> 1;
		init(treeNode << 1, s, mid);
		init(treeNode << 1 | 1, mid + 1, e);
		
		tree[treeNode] = tree[treeNode << 1] ^ tree[treeNode << 1 | 1];
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			if((e - s + 1)%2 == 1)
				tree[treeNode] ^= lazy[treeNode];
			if(s != e)
			{
				lazy[treeNode << 1]		^= lazy[treeNode];
				lazy[treeNode << 1 | 1] ^= lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
}