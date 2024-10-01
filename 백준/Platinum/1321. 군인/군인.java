//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1321

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, H;
	static long[] arr, tree;
	public static long init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = arr[s];
		
		int mid = (s + e) >> 1;
		return tree[treeNode] = init(treeNode*2, s, mid) + init(treeNode*2 + 1, mid + 1, e);
	}
	public static void update(int treeNode, int s, int e, int originIdx, int diff) {
		if(originIdx < s || e < originIdx)
			return;
		
		tree[treeNode] += diff;
		
		if(s != e) {
			int mid = (s + e) >> 1;
			update(treeNode * 2, s, mid, originIdx, diff);
			update(treeNode * 2 + 1, mid + 1, e, originIdx, diff);
		}
	}
	public static int query(int treeNode, int s, int e, long target)
	{
		if(s == e)
			return s;

		int mid = (s + e) >> 1;
		if(target <= tree[treeNode * 2])
			return query(treeNode * 2, s , mid, target);
		else
			return query(treeNode * 2 + 1, mid + 1, e, target - tree[treeNode * 2]);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N	= Integer.parseInt(br.readLine());
		H	= (int)Math.ceil(Math.log(N) / Math.log(2));
		arr	= new long[N+1];
		tree= new long[1<<(H + 1)];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		init(1, 1, N);
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0)
		{
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			if(cmd == 1)	// 부대 갱신
			{
				int idx = Integer.parseInt(st.nextToken());
				int diff = Integer.parseInt(st.nextToken());
				update(1, 1, N, idx, diff);
			}
			else	// 해당 값의 부대 출력
			{
				int cnt = Integer.parseInt(st.nextToken());
				sb.append( query(1, 1, N, cnt) ).append('\n');
			}
		}
		System.out.print(sb.toString());
	}
}