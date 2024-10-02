//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/10868
class Main{
	
	static int[] arr, segTree;
	// 빠른 입력을 위한 함수
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static int init(int treeNode, int s, int e) {
		if(s == e)
			return segTree[treeNode] = arr[s];
		int mid = (s + e) >> 1;
		return segTree[treeNode] = Math.min(init(treeNode * 2, s, mid), init(treeNode * 2 + 1, mid + 1, e));
	}
	public static int getMin(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 1<<30;
		if(left<=s && e<=right)
			return segTree[treeNode];
		
		int mid = (s + e) >> 1;
		return Math.min( getMin(treeNode*2, s, mid, left, right), getMin(treeNode*2 + 1, mid + 1, e, left, right) );
	}
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N	= read();									// 정수들의 수(1<=십만)
		int M	= read();									// 범위수(1<=십만)
		int H	= (int)Math.ceil(Math.log(N) / Math.log(2));// 세그먼트 트리의 높이
		arr		= new int[N+1];
		segTree = new int[1<<(H + 1)];
		
		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		init(1, 1, N);
		
		while(M-->0)
		{
			int a = read();
			int b = read();
			sb.append(getMin(1, 1, N, a, b))
				.append('\n');
		}
		
		System.out.print(sb.toString());
	}
}