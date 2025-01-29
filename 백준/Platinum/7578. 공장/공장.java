//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7578
//1초 / 256MB

class Main{
	
	static int N;
	static int[] arr, tree, counting;
	
	public static void main(String[] args)throws Exception{
		N			= read();				// 1<=오십만
		tree		= new int[N<<2];
		counting	= new int[1_000_001];	// 입력수 0<=백만
		arr			= new int[N+1];
		
		for(int i=1; i<=N; i++)
			counting[read()] = i;
		
		for(int i=1; i<=N; i++)
			arr[i] = counting[read()];
		
		long res = 0;
		for(int i=N; i>0; i--)
		{
			res += query(1, 1, N, 1, arr[i] - 1);
			update(1, 1, N, arr[i]);
		}
		
		System.out.print(res);
	}
	public static void update(int treeNode, int s, int e, int idx) {
		if(idx<s || e<idx)
			return;
		
		tree[treeNode] += 1;
		
		if(s != e)
		{
			int nextNode = treeNode << 1;
			int mid = (s + e) >> 1;
			update(nextNode, s, mid, idx);
			update(nextNode | 1, mid + 1, e, idx);
		}
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		if(left <= s && e<= right)
			return tree[treeNode];
		if(e < left || right < s)
			return 0;
		
		int nextNode = treeNode << 1;
		int mid = (s + e) >> 1;
		return query(nextNode, s, mid, left, right) +
				query(nextNode | 1, mid + 1, e, left, right);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}