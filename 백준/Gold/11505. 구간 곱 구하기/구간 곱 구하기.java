//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11505
class Main{
	
	static final int MOD = 1_000_000_007;
	static long[] arr, tree;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static long init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = arr[s];
		int mid = (s + e) >> 1;
		return tree[treeNode] = (init(treeNode*2, s, mid) * init(treeNode*2+1, mid+1, e)) % MOD;
	}
	public static void update(int treeNode, int s, int e, int originIdx, int diff) {
		if(originIdx < s || e < originIdx)
			return;
		if(s == e)
		{
			tree[treeNode] = diff;
			return;
		}
		
		int mid = (s + e) >> 1;
		update(treeNode*2, s, mid, originIdx, diff);
		update(treeNode*2+1, mid+1, e, originIdx, diff);
		
		tree[treeNode] = (tree[treeNode * 2] * tree[treeNode * 2 + 1]) % MOD;
	}
	public static long query(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 1;
		
		if(left <= s && e <= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return (query(treeNode*2, s, mid, left, right) * query(treeNode*2+1, mid+1, e, left, right)) % MOD;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N	= read();
		int M	= read() + read();
		int H	= (int)Math.ceil(Math.log(N) / Math.log(2));
		arr		= new long[N + 1];
		tree	= new long[1<<(H+1)];
		
		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		init(1, 1, N);
		
		while(M-->0)
		{
			int cmd	= read();
			int a	= read();
			int b	= read();
			
			if(cmd == 1)	// arr[a]를 b로 치환
				update(1, 1, N, a, b);
			else			// a ~ b 범위 곱셈 출력
				sb.append( query(1, 1, N, a, b) )
					.append('\n');
		}
		System.out.print(sb.toString());
	}
}