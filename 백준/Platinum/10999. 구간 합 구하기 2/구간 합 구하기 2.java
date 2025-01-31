//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10999
//2초 / 256MB

class Main{
	
	static int N, M, K;
	static long[] arr, tree, lazy;
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		N	= readInt();	// 1<=백만
		M	= readInt();	// 1<=만
		K	= readInt();	// 1<=만
		
		arr = new long[N+1];
		tree= new long[N<<2];
		lazy= new long[N<<2];		// 지연 연산을 위한 배열
		
		for(int i=1; i<=N; i++)
			arr[i] = readLong();	// long 최소 최대범위
		
		init(1, 1, N);
		
		int T = M + K;
		while(T-->0)
		{
			int f = readInt();
			int b = readInt();
			int c = readInt();
			
			if(f == 1)	// b,c에 d를 더한다.
				update(1, 1, N, b, c, readLong());
			
			else		// b, c 수의 합
				sb.append(query(1, 1, N, b, c)).append('\n');
		}
		
		System.out.print(sb);
	}
	public static long query(int treeNode, int s, int e, int left, int right) {
		propagate(treeNode, s, e);
		
		if(e<left || right <s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int nextNode= treeNode << 1;
		int mid		= (s + e) >> 1;
		
		return query(nextNode, s, mid, left, right) 
				+ query(nextNode | 1, mid + 1, e, left, right);
	}
	public static void update(int treeNode, int s, int e, int left, int right,long diff) {
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return;
		if(left<= s && e<=right)
		{
			lazy[treeNode] = diff;
			propagate(treeNode, s, e);
			return;
		}
		
		int nextNode= treeNode << 1;
		int mid		= (s + e) >> 1;
		
		update(nextNode, s, mid, left, right, diff);
		update(nextNode | 1, mid + 1, e, left, right, diff);
		
		tree[treeNode] = tree[nextNode] + tree[nextNode | 1];
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			// 현재 treeNode에 값 갱신
			tree[treeNode] += (e - s + 1) * lazy[treeNode];
			// leaf노드가 아니면, 다음 lazy에 전파 갱싱
			if(s != e)
			{
				lazy[treeNode << 1]		+= lazy[treeNode];
				lazy[treeNode << 1 | 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
	public static long init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = arr[s];
		
		int nextNode= treeNode << 1;
		int mid		= (s + e) >> 1;
		return tree[treeNode] = init(nextNode, s, mid) 
								+ init(nextNode | 1, mid + 1, e);
	}
	static int readInt() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
    static long readLong() throws Exception {
        long c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
}