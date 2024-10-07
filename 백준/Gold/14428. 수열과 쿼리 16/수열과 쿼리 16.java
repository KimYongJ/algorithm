//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/14428

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int[] arr, tree;
	
	public static int init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = s;
		
		int mid			= (s + e) >> 1;
		int nextNode	= treeNode<<1;
		int l			= init(nextNode, s, mid);
		int r			= init(nextNode|1, mid + 1, e);
		
		return tree[treeNode] = arr[l] <= arr[r] ? l : r;
	}
	public static int getMin(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int mid			= (s + e) >> 1;
		int nextNode	= treeNode << 1;
		int l			= getMin(nextNode, s, mid, left, right);
		int r			= getMin(nextNode | 1, mid + 1, e, left, right);
		
		return arr[l] <= arr[r] ? l : r;
	}
	public static void update(int treeNode, int s, int e, int originIdx) {
		if(originIdx < s || e < originIdx || s == e)
			return;

		int mid			= (s + e) >> 1;
		int nextNode	= treeNode << 1;
		
		update(nextNode, s, mid, originIdx);
		update(nextNode | 1, mid + 1, e, originIdx);
		
		tree[treeNode] = 
				arr[tree[nextNode]] <= arr[tree[nextNode|1]] ? 
				tree[nextNode] : tree[nextNode|1];
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int H = (int)Math.ceil(Math.log(N) / Math.log(2));
		arr		= new int[N+1];
		tree	= new int[1<<(H+1)];
		arr[0]	= Integer.MAX_VALUE;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		init(1, 1, N);
		
		int Q = Integer.parseInt(br.readLine());
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(cmd == 1)		// arr[a] = b로 변경
			{
				arr[a] = b;
				update(1, 1, N, a);
			}
			else
				sb.append( getMin(1, 1, N, a, b) ).append('\n');
			
		}
		System.out.print(sb);
	}
}