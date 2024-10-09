//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/12837
class Main{
	
	static long[] tree;
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
	    boolean m = n == 13;
	    if (m)n = System.in.read() & 15;
	    while ((c = System.in.read()) >= 48) {
	    n = (n << 3) + (n << 1) + (c & 15);}
	    return m ? ~n + 1 : n;
	 }
	 
	public static void update(int treeNode, int s, int e, int originIdx, long diff) {
		if(originIdx < s | e < originIdx)
			return;
		
		tree[treeNode] += diff;
		
		if(s!=e) {
			int mid = (s + e) >> 1;
			update(treeNode*2, s, mid, originIdx, diff);
			update(treeNode*2+1, mid + 1, e, originIdx, diff);
		}
	}
	public static long query(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		return query(treeNode*2, s, mid, left, right) + query(treeNode*2+1, mid+1, e, left, right);
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N = read();			// 1<=백만
		int Q = read();			// 1<=십만
		int H = (int)Math.ceil(Math.log(N) / Math.log(2));
		
		tree= new long[1<<(H + 1)];
		
		while(Q-->0)
		{
			int cmd	= read();
			int a	= read();
			int b	= read();
			
			if(cmd == 1)
				update(1, 1, N, a, b);
			else
				sb.append( query(1, 1, N, a, b) ).append('\n');
		}
		System.out.print(sb);
	}
}