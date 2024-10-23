//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/25778

class Main{
	
	static long[] arr, seg;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static long init(int treeNode, int s, int e) {
		if(s == e)
			return seg[treeNode] = arr[s];
		
		int mid = (s + e) >> 1;
		int nextNode = treeNode << 1;
		
		return seg[treeNode] = init(nextNode, s, mid) + init(nextNode | 1, mid + 1, e);
	}
	
	public static void update(int treeNode, int s, int e, int originIdx, int diff) {
		if(originIdx < s || e < originIdx)
			return;
		
		seg[treeNode] += diff;
		
		if(s != e) {
			int mid		= (s + e) >> 1;
			int nextNode= treeNode << 1;
			update(nextNode, s, mid, originIdx, diff);
			update(nextNode | 1, mid + 1, e, originIdx, diff);
		}
	}
	
	public static long query(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 0;
		if(left <= s && e <= right)
			return seg[treeNode];
		
		int mid = (s + e) >> 1;
		int nextNode = treeNode << 1;
		return query(nextNode, s, mid, left, right) + query(nextNode | 1, mid + 1, e, left, right);
	}
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		
		int N = read();
		int H = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
		
		arr = new long[N+1];
		seg = new long[(1<<H)];
		
		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		init(1, 1, N);
		
		int T = read();
		while(T-->0)
		{
			char c	= (char)System.in.read();
			int a	= read();
			int b	= read();
			
			if(c == 'U')
				update(1, 1, N, a, b);
			else
				sb.append( query(1, 1, N, a, b) )
					.append('\n');
		}
		System.out.print(sb.toString());
	}
}