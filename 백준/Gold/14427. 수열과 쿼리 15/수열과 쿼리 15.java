//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14427

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int[] arr, tree;
	public static int init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = arr[s];
		
		int mid = (s + e) >> 1;
		
		return tree[treeNode] = Math.min(init(treeNode*2, s, mid), init(treeNode*2+1, mid + 1, e));
	}
	public static void update(int treeNode, int s, int e, int originIdx, int diff) {
		if(originIdx < s || e < originIdx)
			return;

		if(s == e) {
			tree[treeNode] = diff;
			return;
		}
		
		int mid = (s + e) >> 1;
		update(treeNode*2, s, mid, originIdx, diff);
		update(treeNode*2+1, mid + 1, e, originIdx, diff);
		
		tree[treeNode] = Math.min(tree[treeNode * 2], tree[treeNode * 2 + 1]);
	}
	public static int getMinIdx(int treeNode, int s, int e) {
		if(s == e)
			return s;
		int mid = (s + e) >> 1;
		int nextTreeNode = treeNode << 1;
		if(tree[nextTreeNode] <= tree[nextTreeNode | 1])
			return getMinIdx(nextTreeNode, s, mid);
		else
			return getMinIdx(nextTreeNode | 1, mid + 1, e);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());	// 수열의크기 (1<=십만)
		int H = (int)Math.ceil(Math.log(N) / Math.log(2));
		
		arr = new int[N+1];
		tree= new int[1<<(H+1)];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		init(1, 1, N);
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0)
		{
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			if(cmd == 2)	// 해당 배열에서 가장작은 값의 인덱스 출력			
				sb.append(getMinIdx(1, 1, N)).append('\n');
			else
			{
				int idx = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				update(1, 1, N, idx, value);
			}
		}
		System.out.print(sb.toString());
	}
}