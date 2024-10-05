//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1275
class Main{
	
	static long[] arr, tree;
	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
	
	public static long init(int treeNode, int s, int e){
		if(s == e)
			return tree[treeNode] = arr[s];
		
		int mid = (s + e) >> 1;
		
		return tree[treeNode] = init(treeNode<<1, s, mid) + init(treeNode<<1 | 1, mid + 1, e);
	}
	
	public static long update(int treeNode, int s, int e, int originIdx, int diff) {
		if(originIdx < s || e < originIdx)
			return tree[treeNode];
		
		if(s == e)
			return tree[treeNode] = diff;
		
		int mid = (s + e) >> 1;
		
		return tree[treeNode] = update(treeNode<<1, s, mid, originIdx, diff) + update((treeNode<<1) | 1, mid + 1, e, originIdx, diff);
	}
	
	public static long sum(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 0;
		
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return sum(treeNode<<1, s, mid, left, right) + sum((treeNode<<1) | 1, mid + 1, e, left, right);
	}
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N	= read();		// 수의개수(1<=십만)
		int Q	= read();		// 턴의개수(1<=십만)
		int H	= (int)Math.ceil(Math.log(N)/ Math.log(2));
		
		arr		= new long[N+1];
		tree	= new long[1<<(H+1)];

		for(int i=1; i<=N; i++)
			arr[i] = read();

		init(1, 1, N);

		while(Q-->0)
		{
			int x = read();
			int y = read();
			if(y < x)
			{
				int tmp = y;
				y = x;
				x = tmp;
			}
			
			sb.append(sum(1, 1, N, x, y))
				.append('\n');
			
			x = read();
			y = read();
			
			update(1, 1, N, x, y);
		}
		
		System.out.print(sb.toString());
	}
}