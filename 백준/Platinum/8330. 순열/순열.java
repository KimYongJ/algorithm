//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/8330
//1초 128MB

class Main{
	
	static int N;
	static int[] freq, arr;
	static int[] tree, lazy, M;
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		N		= read();
		arr		= new int[N + 1];
		freq	= new int[N + 1];
		tree	= new int[N * 4];
		lazy	= new int[N * 4];
		M		= new int[N + 1];
		
		for(int i=1; i<=N; i++)
		{
			arr[i] = read();
			freq[arr[i]]++;// 해당 숫자가 몇번 나왔는지 체크한다.
		}
		
		int psum = 0;
		for(int i=1; i<=N; i++)
		{
			psum += freq[i];	// 1부터 i까지 나온 숫자들의 총 개수를 누적한다.
			M[i] = psum - i;	// 총개수에 자기자신을 마이너스 한다.
		    // 모든 i에 대해 M[i] <= 0 이면 '접두사 합 <= i' 조건이 만족되어 순열이 가능하다.
		    // 어느 지점에서라도 M[i]가 양수가 되면(> 0) 접두사 합이 i보다 커서 순열 불가능.
		}
		
		init(1, 1, N);
		
		sb.append(query(1, 1, N, 1, N)<=0 ? "TAK\n" : "NIE\n");
		
		int T = read();
		while(T-->0)
		{
			int i	= read();
			int old = arr[i];
			arr[i]	= read();
			
			update(1, 1, N, old, N, -1);
			update(1, 1, N, arr[i], N, 1);
			
			sb.append(query(1, 1, N, 1, N)<=0 ? "TAK\n" : "NIE\n");
		}
		System.out.print(sb);
	}
	public static void update(int treeNode, int s, int e, int left, int right, int value) {
		
		propagate(treeNode, s, e);
		
		if(e < left || right < s) 
			return;
		if(left<=s && e<=right)
		{
			lazy[treeNode] = value;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode*2, s, mid, left, right, value);
		update(treeNode*2 + 1, mid + 1, e, left, right, value);
		
		tree[treeNode] = Math.max(tree[treeNode*2], tree[treeNode*2+1]);
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		int l = query(treeNode*2, s, mid, left, right);
		int r = query(treeNode*2+1, mid + 1, e, left, right);
		
		return Math.max(l, r);
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode] = M[s];
			return;
		}
		
		int mid = (s + e) >> 1;
		
		init(treeNode * 2, s, mid);
		init(treeNode * 2 + 1, mid + 1, e);
		
		tree[treeNode] = Math.max(tree[treeNode*2], tree[treeNode*2+1]);
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] += lazy[treeNode];
			if(s!=e)
			{
				lazy[treeNode * 2]		+= lazy[treeNode];
				lazy[treeNode * 2 + 1]	+= lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}