//https://www.acmicpc.net/problem/29618
//0.5초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int tree[];
	static int lazy[];
	static int N, Q;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		tree = new int[N * 4];
		lazy = new int[N * 4];
		
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			update(1, 1, N, a, b, x);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(query(1, 1, N, i)).append(' ');
		
		System.out.print(sb);
	}
	static void update(int treeNode, int s, int e, int left, int right, int val) {
		propagate(treeNode, s, e);
		if(e < left || right < s)
			return;
		if(left <= s && e <= right) {
			lazy[treeNode] = val;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right, val);
		update(treeNode << 1 | 1, mid + 1, e, left, right, val);
	}
	static int query(int treeNode, int s, int e, int targetIdx) {
		propagate(treeNode, s, e);
		if(s == e)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		if(targetIdx <= mid)
			return query(treeNode << 1, s, mid, targetIdx);
		
		return query(treeNode << 1 | 1, mid + 1, e, targetIdx);
	}
	static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] == 0)
			return;
		
		if(s == e && tree[treeNode] == 0)
			tree[treeNode] = lazy[treeNode];
		
		if(s != e)
		{
			if(lazy[treeNode << 1] == 0)
				lazy[treeNode << 1] = lazy[treeNode];
			if(lazy[treeNode << 1 | 1] == 0)
				lazy[treeNode << 1 | 1] = lazy[treeNode];
		}
		lazy[treeNode] = 0;
	}
}