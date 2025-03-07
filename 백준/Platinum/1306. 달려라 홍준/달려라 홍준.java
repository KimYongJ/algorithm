//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1306
//2초/256MB

class Main{
	
	static int N, M;
	static int[] arr, tree;
	
	public static void main(String[] args)throws Exception{
		N		= read();	// 칸수 1<=백만
		M		= read();	// 시야 1<=M
		arr		= new int[N+1];
		tree	= new int[N<<2];
		
		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		init(1, 1, N);

		StringBuilder sb = new StringBuilder();
		for(int s=M,p=M-1; s<=N-M+1; s++)
			sb.append(query(1,1,N,s-p,s+p)).append(' ');

		System.out.print(sb);
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int nextNode	= treeNode << 1;
		int mid			= (s + e) >> 1;
		int num1		= query(nextNode, s, mid, left, right);
		int num2		= query(nextNode | 1, mid + 1, e, left, right);
		
		return Math.max(num1, num2);
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode] = arr[s];
			return;
		}
		
		int nextNode	= treeNode << 1;
		int mid			= (s + e) >> 1;
		init(nextNode, s, mid);
		init(nextNode | 1, mid + 1, e);
		
		tree[treeNode] = Math.max(tree[nextNode], tree[nextNode | 1]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}