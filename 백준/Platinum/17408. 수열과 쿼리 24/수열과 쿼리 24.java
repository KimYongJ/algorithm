//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17408
//1초 / 512MB
class Main{
	
	static int N, M;
	static int[] arr, tree;
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		N		= read();//수열의 크기 N(2<=십만)
		tree	= new int[N<<2];
		arr		= new int[N+1];

		for(int i=1; i<=N; i++)
			arr[i] = read();//1<=십억
		
		init(1, 1, N);
		
		M = read();// 쿼리 수 1<=십만
		while(M-->0)
		{
			int f = read();
			int a = read();
			int b = read();
			
			if(f==1)
				update(1, 1, N, a, b);
			else
			{
				int idx = query(1, 1, N, a, b);
				int idx1= query(1, 1, N, a, idx-1);
				int idx2= query(1, 1, N, idx+1, b);
				
				sb.append(arr[idx] + Math.max(arr[idx1], arr[idx2])).append('\n');
			}
		}
		System.out.print(sb);
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		int mid = (s + e) >> 1;
		int node= treeNode << 1;
		
		int idx1 = query(node, s, mid, left, right);
		int idx2 = query(node|1, mid + 1, e, left, right);
		if(arr[idx1] < arr[idx2])
			return idx2;
		else
			return idx1;
	}
	public static void update(int treeNode, int s, int e, int idx, int value) {
		if(e<idx || idx<s)
			return;
		if(s == e)
		{
			arr[s] = value;
			return;
		}
		int mid = (s + e) >> 1;
		int node= treeNode << 1;
		
		update(node, s, mid, idx, value);
		update(node|1, mid + 1, e, idx, value);
		
		if(arr[tree[node]] < arr[tree[node | 1]])
			tree[treeNode] = tree[node | 1];
		else
			tree[treeNode] = tree[node];
	}
	// 인덱스 트리
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode] = s;
			return;
		}
		int mid = (s + e) >> 1;
		int node= treeNode << 1;
		
		init(node, s, mid);
		init(node| 1, mid + 1, e);
		
		if(arr[tree[node]] < arr[tree[node | 1]])
			tree[treeNode] = tree[node | 1];
		else
			tree[treeNode] = tree[node];
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}