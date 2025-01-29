//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15459
//2초 / 512MB

class Main{
	
	static int[] arr, max, tree;
	
	public static void main(String[] args)throws Exception{
		int N	= readInt();	// 1<=십만
		long M	= readLong();	// 1<=100경
		arr		= new int[N+1];
		max		= new int[N+1];
		tree	= new int[N<<2];
		
		for(int i=1; i<=N; i++)
		{
			arr[i] = readInt();	// 1<=10억
			max[i] = readInt();	// 1<=10억
		}
		
		init(1, 1, N);
		
		long sum	= 0;
		int result	= Integer.MAX_VALUE;	// 식사의 최소 매운맛
		int s		= 1;
		int e		= 0;
		while(++e<=N)
		{
			sum += arr[e];

			while(M <= sum)
			{
				int max = query(1, 1, N, s, e);
				result = Math.min(result, max);
				sum -= arr[s++];
			}
			
		}
		
		System.out.print(result);
	}
	public static int init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = max[s];
		
		int nextNode = treeNode << 1;
		int mid = (s + e) >> 1;
		int num1 = init(nextNode, s, mid);
		int num2 = init(nextNode | 1, mid + 1, e);
		
		return tree[treeNode] = Math.max(num1, num2);
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		int nextNode = treeNode << 1;
		int mid = (s + e) >> 1;
		int num1 = query(nextNode, s, mid, left, right);
		int num2 = query(nextNode | 1, mid + 1, e, left, right);
		return Math.max(num1, num2);
	}
	static int readInt() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	static long readLong() throws Exception {
		long c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}