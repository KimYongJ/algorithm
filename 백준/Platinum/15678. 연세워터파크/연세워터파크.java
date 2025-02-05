//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15678
//1초 / 128mb
//요약 : DP문제의 최장 감소or증가 하는 부분수열의 최대합을 구하는 문제랑 비슷하다. 응용한것. 다만 범위가 커서 세그먼트로함
//비슷한 문제 : 11055 + 17216
class Main{
	
	static final long MIN = Long.MIN_VALUE;
	static int N, D;
	static long[] tree, dp;
	
	public static void main(String[] args)throws Exception{ 
		N		= read();	// 2<=십만
		D		= read();	// 2<=N-1
		tree	= new long[N<<2];
		dp		= new long[N+1];

		long max = MIN;
        
		for(int i=1; i<=N; i++)
		{
			dp[i] = read();
			
			long maxValue = query(1, 1, N, i-D, i-1);
			
			if(maxValue != MIN)
				dp[i] = Math.max(dp[i], dp[i] + maxValue);
			
			update(1, 1, N, i, dp[i]);
			
			max = Math.max(max, dp[i]);
		}
		System.out.print(max);
	}
	public static void update(int treeNode, int s, int e, int idx, long value) {
		if(e<idx || idx< s)
			return;
		if(s == e)
		{
			tree[treeNode] = value;
			return;
		}
		int nextNode= treeNode << 1;
		int mid		= (s + e) >> 1;
		update(nextNode, s, mid, idx, value);
		update(nextNode | 1, mid + 1, e, idx, value);
		
		tree[treeNode] = Math.max(tree[nextNode], tree[nextNode |1]);
	}
	public static long query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return MIN;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int nextNode= treeNode << 1;
		int mid		= (s + e) >> 1;
		long max1	= query(nextNode, s, mid, left, right);
		long max2	= query(nextNode | 1, mid + 1, e, left, right);
		
		return Math.max(max1, max2);
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