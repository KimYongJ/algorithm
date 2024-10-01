//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1572

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static final int MAX = 65537;
	static int N, K, h;
	static int[] arr, tree;
	
	public static void update(int treeNode, int s, int e, int target, int diff) {
		if(target < s || e < target)
			return;
		
		tree[treeNode] += diff;
		
		if(s != e) {
			int mid = (s + e) >> 1;
			update(treeNode*2, s, mid, target, diff);
			update(treeNode*2 + 1, mid + 1, e, target, diff);
		}
	}
	public static int query(int treeNode, int s, int e, int cnt) {
		if(s == e)
			return s;
		
		int mid = (s + e) >> 1;
		if(cnt <= tree[treeNode*2])
			return query(treeNode*2, s, mid, cnt);
		else
			return query(treeNode*2 + 1, mid + 1, e, cnt - tree[treeNode*2]);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N	= Integer.parseInt(st.nextToken());				// 1<=25만, 항상 K보다크거나 같음
		K	= Integer.parseInt(st.nextToken());				// 1<=오천
		h	= (int)Math.ceil(Math.log(MAX) / Math.log(2));
		arr = new int[N+1];
		tree= new int[1<<(h + 1)];
		
		long res = 0;
		int cnt = (K + 1) >> 1;
		for(int i=1; i<=N; i++)
		{
			arr[i] = Integer.parseInt(br.readLine());
			
			update(1, 0, MAX, arr[i], 1);
			
			if(K <= i)
			{
				res += query(1, 0, MAX, cnt);
				update(1, 0, MAX, arr[i - K + 1], -1);
			}
		}
		System.out.print(res);
	}
}