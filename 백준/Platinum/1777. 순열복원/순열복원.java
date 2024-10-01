//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1777
class Main{
	
	static int N, H, idx;
	static int[] arr, tree, res;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static int init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = 1;
		
		int mid = (s + e) >> 1;
		
		return tree[treeNode] = init(treeNode*2, s, mid) + init(treeNode*2 + 1, mid + 1, e);
	}
	public static void update(int treeNode, int s, int e, int originIdx, int diff) {
		if(originIdx < s || e < originIdx)
			return;
		
		tree[treeNode] += diff;
		
		if(s == e)
			return;
		
		int mid = (s + e) >> 1;

		update(treeNode * 2, s, mid, originIdx, diff);
		update(treeNode * 2 + 1, mid + 1, e, originIdx, diff);
	}
	public static void query(int treeNode, int s, int e, int cnt) {
		if(s == e) {
			update(1, 1, N, s, -1);
			res[N - s + 1] = idx--;
			return;
		}
		
		int mid = (s + e) >> 1;
		
		if(cnt < tree[treeNode * 2])
			query(treeNode * 2, s, mid, cnt);
		else
			query(treeNode * 2 + 1, mid + 1, e, cnt - tree[treeNode * 2]);
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		N		= read();	// 순열 크기 (1<=십만)
		H		= (int)Math.ceil(Math.log(N+1) / Math.log(2));
		idx 	= N;
		arr 	= new int[N + 1];
		res 	= new int[N + 1];
		tree	= new int[1<<(H+1)];
		
		init(1, 1, N);
		
		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		for(int i=N; i>0; i--)		// 뒤에서부터 탐색하며 왼쪽에서 오른쪽으로 채워나간다.
			query(1, 1, N, arr[i]);
		
		for(int i=1; i<=N; i++)
			sb.append(res[i]).append(' ');
		
		System.out.print(sb);		
	}
}