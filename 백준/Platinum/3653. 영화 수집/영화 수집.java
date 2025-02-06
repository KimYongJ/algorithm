//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3653
//1초 / 256MB
class Main{
	
	static int N, M, maxIdx, LEN;
	static int[] tree, pos;
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		
		int T = read();
		
		while(T-->0)
		{
			N		= read();
			M		= read();
			LEN		= N + M;
			pos		= new int[N + 1];
			tree	= new int[LEN<<2];
			maxIdx	= N;
			for(int i=1; i<=N; i++)
				pos[i] = N - i + 1;
			
			init(1, 1, LEN);
			
			while(M-->0)
			{
				int target	= read();
				int cnt		= query(1, 1, LEN, pos[target] + 1, maxIdx);
				
				sb.append(cnt).append(' ');
				
				if(cnt != 0)
				{
					update(1, 1, LEN, pos[target], 0);
					
					pos[target] = ++maxIdx;
				}
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		
		if(left<=s && e<=right)
			return tree[treeNode];

		int mid		= (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, left, right) 
				+ query(treeNode << 1 | 1, mid + 1, e, left, right);
	}
	public static void update(int treeNode, int s, int e, int idx, int value) {
		if(e<idx || idx < s)
			return;
		if(s == e)
		{
			tree[treeNode] = value;
			return;
		}

		int mid			= (s + e) >> 1;
		update(treeNode << 1, s, mid, idx, value);
		update(treeNode << 1 | 1, mid + 1, e, idx, value);
		
		tree[treeNode] = tree[treeNode << 1] + tree[treeNode << 1 | 1];
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode] = 1;
			return;
		}
		
		int mid = (s + e) >> 1;
		
		init(treeNode << 1, s, mid);
		init(treeNode << 1 | 1, mid + 1, e);
		
		tree[treeNode] = tree[treeNode << 1] + tree[treeNode << 1 | 1];
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
