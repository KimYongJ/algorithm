//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3653
//1초 / 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, M, maxIdx, LEN;
	static int[] tree, pos;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N		= Integer.parseInt(st.nextToken());
			M		= Integer.parseInt(st.nextToken());
			LEN		= N + M;
			pos		= new int[N + 1];
			tree	= new int[LEN<<2];
			maxIdx	= N;
			for(int i=1; i<=N; i++)
				pos[i] = N - i + 1;
			
			init(1, 1, LEN);
			
			st = new StringTokenizer(br.readLine());
			while(M-->0)
			{
				int target = Integer.parseInt(st.nextToken());
				
				sb.append(query(1, 1, LEN, pos[target] + 1, maxIdx)).append(' ');
				
				update(1, 1, LEN, pos[target], 0);
				
				pos[target] = ++maxIdx;
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int nextNode= treeNode << 1;
		int mid		= (s + e) >> 1;
		
		return query(nextNode, s, mid, left, right) + query(nextNode | 1, mid + 1, e, left, right);
	}
	public static void update(int treeNode, int s, int e, int idx, int value) {
		if(e<idx || idx < s)
			return;
		if(s == e)
		{
			tree[treeNode] = value;
			return;
		}
		int nextNode	= treeNode << 1;
		int mid			= (s + e) >> 1;
		update(nextNode, s, mid, idx, value);
		update(nextNode | 1, mid + 1, e, idx, value);
		
		tree[treeNode] = tree[nextNode] + tree[nextNode | 1];
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode] = 1;
			return;
		}
		int nextNode = treeNode << 1;
		int mid = (s + e) >> 1;
		init(nextNode, s, mid);
		init(nextNode | 1, mid + 1, e);
		tree[treeNode] = tree[nextNode] + tree[nextNode | 1];
	}
}
