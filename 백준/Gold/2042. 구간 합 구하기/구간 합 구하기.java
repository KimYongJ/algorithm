//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2042
class Main{
	
	static long[] arr, tree;
    static long read() throws Exception {
        long c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
	public static long init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = arr[s];
		
		int mid = (s + e) >> 1;
		
		return tree[treeNode] = init(treeNode*2, s, mid) + init(treeNode*2+1, mid+1, e);
	}
	public static void update(int treeNode, int s, int e, int originIdx, long diff) {
		if(originIdx < s || e < originIdx)
			return;
		if(s == e) {
			tree[treeNode] = diff;
			return;
		}
		int mid = (s + e) >> 1;
		update(treeNode * 2, s, mid, originIdx, diff);
		update(treeNode * 2 + 1, mid + 1, e, originIdx, diff);
		
		tree[treeNode] = tree[treeNode * 2] + tree[treeNode * 2 + 1];
	}
	public static long query(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 0;
		if(left<=s && e<= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		return query(treeNode * 2, s, mid, left, right) + query(treeNode * 2 + 1, mid + 1, e, left, right);
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N	= (int)read();				// 수의개수 (1<=백만)
		int M	= (int)read();				// 변경이 일어나는 횟수(1<=만)
		int K	= (int)read();				// 구간합을 구하는 횟수(1<=만)
		int H	= (int)Math.ceil(Math.log(N) / Math.log(2));	// 트리의 높이
		arr		= new long[N+1];
		tree	= new long[1<<(H+1)];
		
		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		init(1, 1, N);
		
		for(int i=M+K; i>=1; i--)
		{
			int cmd = (int)read();
			int a	= (int)read();
			long b	= read();
			if(cmd == 1)		// a -> b 변경
				update(1, 1, N, a, b);
			else
				sb.append(query(1, 1, N, a, (int)b)).append('\n');
		}
		System.out.print(sb.toString());
	}
}