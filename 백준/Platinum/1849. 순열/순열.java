//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1849

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static int N, H, idx;
	static int[] arr, tree;
	public static int init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = 1;
		int mid = (s + e) >> 1;
		return tree[treeNode] = init(treeNode*2, s, mid) + init(treeNode*2 + 1, mid + 1, e);
	}
	public static void update(int treeNode, int s, int e,int originIdx, int diff) {
		if(originIdx < s || e < originIdx)
			return;
		
		tree[treeNode] += diff;
		
		if(s != e) {
			int mid = (s + e) >> 1;
			update(treeNode * 2, s, mid , originIdx, diff);
			update(treeNode * 2 + 1, mid +1, e, originIdx, diff);
		}
	}
	public static void query(int treeNode, int s, int e, int cnt) {
		if(s == e){
			update(1, 1, N, s, -1);
			arr[s] = ++idx;
			return;
		}
		int mid = (s + e) >> 1;
		if(cnt <= tree[treeNode*2])
			query(treeNode * 2, s , mid, cnt);
		else
			query(treeNode * 2 + 1, mid + 1, e, cnt - tree[treeNode * 2]);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N	= Integer.parseInt(br.readLine());	// 원소개수 (1<=십만)
		H	= (int)Math.ceil(Math.log(N) / Math.log(2));	// 세그먼트 트리의 높이
		arr = new int[N + 1];
		tree= new int[1<<(H + 2)];
		
		init(1, 1, N);	// 0~N까지 자기 앞에 비어있는 숫자
		
		for(int i=0; i<N; i++)
		{
			int cnt = Integer.parseInt(br.readLine());
			query(1, 1, N, cnt+1);
		}
		
		for(int i=1; i<=N; i++)
			sb.append(arr[i]).append('\n');
		
		System.out.print(sb);
	}
}