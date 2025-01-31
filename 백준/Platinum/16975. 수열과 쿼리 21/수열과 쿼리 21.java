//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16975
//2초 / 512MB

class Main{
	
	static int N;
	static long[] arr, tree, lazy;
	
	public static void main(String[] args)throws Exception{

		StringBuilder sb = new StringBuilder();
		N	= read();	// 1<=십만
		arr = new long[N+1];
		tree= new long[N<<2];
		lazy= new long[N<<2];
		
		for(int i=1; i<=N; i++)
			arr[i] = read();// 1<=백만
		
		init(1, 1, N);
		
		int T = read();	// 1<=십만
		
		while(T-->0)
		{
			if(read() == 1)
			{
				//1<=N
				//1<=N
				//|백만|
				update(1, 1, N, read(), read(), read());
			}
			else
			{
				sb.append(query(1, 1, N, read())).append('\n');
			}
		}
		System.out.print(sb);
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode] = arr[s];
			return;
		}
		
		int mid		= (s + e) >> 1;
		
		init(treeNode << 1, s, mid);
		init(treeNode << 1 | 1, mid + 1, e);
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] += (e - s + 1) * lazy[treeNode];
			if(s != e)
			{
				lazy[treeNode << 1]		+= lazy[treeNode];
				lazy[treeNode << 1 | 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
	public static void update(int treeNode, int s, int e, int left, int right, int diff) {
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return;
		
		if(left <= s && e <= right)
		{
			lazy[treeNode] += diff;
			propagate(treeNode, s, e);
			return;
		}

		int mid		= (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right, diff);
		update(treeNode << 1 | 1, mid + 1, e, left, right, diff);
	}
	public static long query(int treeNode, int s, int e, int idx) {
		propagate(treeNode, s, e);
		
		if(e < idx || idx < s)
			return 0;
		if(s==e)
			return tree[treeNode];

		int mid 	= (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, idx) 
				+ query(treeNode << 1 | 1, mid + 1, e, idx);
	}
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
}