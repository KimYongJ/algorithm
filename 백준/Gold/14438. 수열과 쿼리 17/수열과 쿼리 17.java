//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/14438

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int[] arr, tree;
	
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode] = arr[s];
			return;
		}
		int mid			= (s + e) >> 1;
		int nextNode	= treeNode << 1;
		
		init(nextNode, s, mid);
		init(nextNode | 1, mid + 1, e);
		
		tree[treeNode] = Math.min(tree[nextNode], tree[nextNode | 1]);
	}
	public static void update(int treeNode, int s, int e, int originIdx, int value) {
		if(originIdx < s || e < originIdx)
			return;
		if(s == e)
		{
			tree[treeNode] = value;
			return;
		}
		
		int mid			= (s + e) >> 1;
		int nextNode	= treeNode << 1;
		
		update(nextNode, s, mid, originIdx, value);
		update(nextNode | 1, mid + 1, e, originIdx, value);
		
		tree[treeNode] = Math.min(tree[nextNode], tree[nextNode | 1]);
	}
	public static int minQuery(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 1<<30;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int mid			= (s + e) >> 1;
		int nextNode	= treeNode << 1;
		
		return Math.min(minQuery(nextNode, s, mid, left, right), minQuery(nextNode | 1, mid + 1, e, left, right));
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int H = (int)Math.ceil(Math.log(N) / Math.log(2));
		
		arr		= new int[N+1];
		tree	= new int[1<<(H + 1)];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		init(1, 1, N);
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0)
		{
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(cmd == 1)
				update(1, 1, N, a, b);
			else
				sb.append( minQuery(1, 1, N, a, b) ).append('\n');
			
		}
		System.out.print(sb.toString());
	}
}
