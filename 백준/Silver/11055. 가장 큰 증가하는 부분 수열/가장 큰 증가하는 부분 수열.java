//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11055
//1초 / 256MB
class Main{
	
	static final int MAX = 1000;
	static int N;
	static int[] dp, arr, tree;
	
	public static void main(String[] args)throws Exception{
		N	= read();	// 1<=천
		arr	= new int[N+1];
		dp	= new int[N+1];
		tree= new int[MAX<<2];
		
		for(int i=1; i<=N; i++)
			dp[i] = arr[i] = read();//1<=천

		int max = 0;
		
		for(int i=1; i<=N; i++)
		{
			// 1부터 arr[i]-1 값 범위에  가장큰 dp값을 갖고온다. 
			// 즉, arr[i]보다 작은 범위면서 이전에 나온 dp값 중 가장 큰 것을 가져온다.
			int maxDP = query(1, 1, MAX, 1, arr[i]-1);
			
			dp[i] = Math.max(dp[i],maxDP + arr[i]);
			// arr[i] 데이터 위치에 dp[i]값을 저장한다.
			update(1, 1, MAX, arr[i], dp[i]);
			
			max = Math.max(max, dp[i]);
		}
		System.out.print(max);
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		if(e<left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int nextNode= treeNode << 1;
		int mid		= (s + e) >> 1;
		int max1	= query(nextNode, s, mid, left, right);
		int max2	= query(nextNode | 1, mid + 1, e, left, right);
		
		return Math.max(max1, max2);
	}
	public static void update(int treeNode, int s, int e, int idx, int value) {
		if(e < idx || idx < s)
			return;
		if(s==e)
		{
			tree[treeNode] = value;
			return;
		}
		
		int nextNode= treeNode << 1;
		int mid		= (s + e) >> 1;
		
		update(nextNode, s, mid, idx, value);
		update(nextNode | 1, mid + 1, e, idx, value);
		
		tree[treeNode] = Math.max(tree[nextNode], tree[nextNode | 1]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
