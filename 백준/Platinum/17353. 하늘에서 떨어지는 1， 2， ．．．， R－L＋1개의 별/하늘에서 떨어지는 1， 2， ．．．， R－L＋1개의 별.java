//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17353
//1초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int N, Q;
	static long[] arr, tree, startLazy, countLazy;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N			= Integer.parseInt(br.readLine());// 1<=십만
		arr			= new long[N + 1];
		tree		= new long[N * 4];
		startLazy	= new long[N * 4];
		countLazy	= new long[N * 4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());// 0<=백만
		
		init(1, 1, N);
		
		Q = Integer.parseInt(br.readLine());// 1<=십만
		
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			if(flag == 1)
			{
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				
				update(1, 1, N, s, e);
			}
			else
			{
				int idx = Integer.parseInt(st.nextToken());
				long cnt = query(1, 1, N, idx);
				sb.append(cnt)
					.append('\n');
			}
		}
		System.out.print(sb);
	}
	public static void update(int treeNode, int s, int e, int left, int right) {
		
		propagate(treeNode , s, e);
		
		if(e < left || right < s)
			return;
		
		if(left<=s && e<=right)
		{
			startLazy[treeNode] = left;
			countLazy[treeNode] = 1;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right);
		update(treeNode << 1 | 1, mid + 1, e, left, right);
	}
	public static long query(int treeNode, int s, int e, int idx) {
		
		propagate(treeNode, s, e);
		
		if(idx < s || e < idx)
			return 0;
		if(s == e)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, idx)
				+ query(treeNode << 1 | 1, mid + 1, e, idx);
	}
	public static void propagate(int treeNode, int s, int e) {
		if(countLazy[treeNode] != 0 || startLazy[treeNode] != 0)
		{
			if(s == e)
			{
				tree[treeNode] += (s * countLazy[treeNode]) + countLazy[treeNode] - startLazy[treeNode];
			}
			else
			{
				countLazy[treeNode << 1]	+= countLazy[treeNode];
				countLazy[treeNode << 1 | 1]+= countLazy[treeNode];
				startLazy[treeNode << 1]	+= startLazy[treeNode];
				startLazy[treeNode << 1 | 1]+= startLazy[treeNode];
			}
			countLazy[treeNode] = 0;
			startLazy[treeNode] = 0;
		}
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
	}
}