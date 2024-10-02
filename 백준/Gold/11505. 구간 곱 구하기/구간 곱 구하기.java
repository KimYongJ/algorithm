//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/11505
class Main{
	
	static long 	MOD = 1_000_000_007;
	static long[]	tree;
	static int[]	arr;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static long init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = arr[s];
		
		int mid = (s + e) >> 1;
		
		return tree[treeNode] = (init(treeNode * 2, s, mid) * init(treeNode*2 +1, mid + 1, e)) % MOD;
	}
	public static long getRes(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 1;
		
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		return (getRes(treeNode*2, s, mid, left, right) * getRes(treeNode*2+1, mid+1, e, left, right)) % MOD;
	}
	public static long update(int treeNode, int s, int e, int originIdx, int after) {
		if(originIdx < s || e < originIdx)
			return tree[treeNode];
		
		if(s == e) 
			return tree[treeNode] = after;
		
		int mid = (s + e) >> 1;
		
		return tree[treeNode] = (update(treeNode<<1, s, mid, originIdx, after) *	update((treeNode<<1) + 1, mid + 1, e, originIdx, after)) % MOD;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N = read();	// 수의 개수(1<=백만)
		int M = read();	// 수의 변경이 일어나는 횟수(1<=만)
		int K = read();	// 구간 곱을 구하는 횟수(1<=만)
		int H = (int)Math.ceil(Math.log(N) / Math.log(2));
		
		arr = new int[N + 1];
		tree= new long[1<<(H + 1)];
		
		for(int i=1; i<=N; i++)
			arr[i] = read();

		init(1, 1, N);
		
		for(int i=M+K; i>=1; i--)
		{
			int cmd = read();
			int a	= read();
			int b	= read();

			if(cmd == 1)	// a를 b로바꾼다.
				update(1, 1, N, a, b);
			else			// a~b구간 곱을 구한다.
				sb.append( getRes(1, 1, N, a, b) )
					.append('\n');
		}
		
		System.out.print(sb);
	}
}