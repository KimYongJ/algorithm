//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17353
//1초 512MB
//update시 시작값의 합을 담는 startLazy와 몇번 업데이트를 하는지에 대해 합을 담는 countLazy를 준비
//특정 idx를 구할 때 공식 : (현재구하자고자하는노드 * 몇번 업데이트를했는지의합) + 몇번 업데이트를했는지의 합 - 시작노드들의 합
class Main{
	
	static int N, Q;
	static long[] arr, tree, startLazy, countLazy;
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		N			= read();// 1<=십만
		arr			= new long[N + 1];
		tree		= new long[N * 4];
		startLazy	= new long[N * 4];
		countLazy	= new long[N * 4];
		
		for(int i=1; i<=N; i++)
			arr[i] = read();// 0<=백만
		
		init(1, 1, N);
		
		Q = read();// 1<=십만
		
		while(Q-->0)
		{
			if(read() == 1)
				update(1, 1, N, read(), read());
			else
				sb.append( query(1, 1, N, read()) )
					.append('\n');
			
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
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}