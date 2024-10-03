//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2268
class Main{
	
	static long[] arr, tree;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	static long sum(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 0;
		if(left <= s && e <= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return sum(treeNode*2, s, mid, left, right) + sum(treeNode*2+1, mid+1, e, left, right);
	}
	
	static void modify(int treeNode, int s, int e, int originIdx, long diff) {
		if(originIdx < s || e < originIdx)
			return;

		tree[treeNode] += diff;

		if(s != e)
		{
			int mid = (s + e) >> 1;
			modify(treeNode*2, s, mid, originIdx, diff);
			modify(treeNode*2+1, mid+1, e, originIdx, diff);
		}
	}
	
	public static void main(String[] args)throws Exception{
		StringBuilder	sb = new StringBuilder();
		int N	= read();
		int M	= read();
		int H	= (int)Math.ceil(Math.log(N) / Math.log(2));
		arr		= new long[N+1];
		tree	= new long[1<<(H+1)];

		
		while(M-->0)
		{
			int cmd	= read();
			int a	= read();
			int b	= read();
			
			if(cmd == 0)// a~b구간 합
			{	
				long res = 0;
				if(a < b)
					res = sum(1, 1, N, a, b);
				else
					res = sum(1, 1, N, b, a);
				sb.append( res )
					.append('\n');
			}
			else
			{
				modify(1, 1, N, a, b - arr[a]);
				arr[a] = b;
			}
			
		}
		System.out.print(sb.toString());
	}
}