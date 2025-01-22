//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10090
//1초 / 256MB
//요약 : 배열의 왼쪽부터 시작하여 오른쪽으로 갈때 자기보다 작은 쌍이 몇개나있는지 쌍의 총개수 출력
class Main{
	
	static int N;
	static int[] arr, tree;

	public static void main(String[] args)throws Exception{
		N	= read();
		tree= new int[N<<2];
		arr	= new int[N];

		for(int i=0; i<N; i++)
			arr[i] = read();
		
		long sum = 0;
		for(int i=N-1; i>=0; i--)
		{
			sum += query(1, 1, N, 1, arr[i] - 1);
			update(1, 1, N, arr[i]);
		}
		System.out.print(sum);
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int nextNode= treeNode << 1;
		int mid		= (s + e) >> 1;
		
		return query(nextNode, s, mid, left, right)
				+ query(nextNode | 1, mid + 1, e, left, right);
	}
	
	public static void update(int treeNode, int s, int e, int idx) {
		if(idx < s || e < idx)
			return;
		
		tree[treeNode] += 1;
		
		if(s != e)
		{
			int nextNode= treeNode << 1;
			int mid		= (s + e) >> 1;
			update(nextNode, s, mid, idx);
			update(nextNode | 1, mid + 1, e, idx);
		}
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}