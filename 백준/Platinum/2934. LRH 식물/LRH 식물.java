//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2934
//1초 / 128MB
class Main{
	
	static final int MAX = 100_000;
	static int N;
	static int[] lazy, tree;
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		N		= read();
		tree	= new int[MAX<<2];
		lazy	= new int[MAX<<2];
		
		while(N-->0)
		{
			int l = read();
			int r = read();
			
			//쿼리로 해당 구간의 값을 가져오면서 동시에 그 인덱스의 값을 0으로 변경함
			sb.append(query(1, 1, MAX, l) + query(1, 1, MAX, r))
				.append('\n');
			
			if(1 < r - l)
				update(1, 1, MAX, l + 1, r - 1, 1);
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
		
		update(treeNode << 1, s, mid, left, right, value);
		update(treeNode << 1 | 1, mid + 1, e, left, right, value);
		
		tree[treeNode] = tree[treeNode << 1] + tree[treeNode << 1 | 1];
	}
	public static int query(int treeNode, int s, int e, int idx) {
		
		propagate(treeNode, s, e);
		
		if(e < idx || idx < s)
			return 0;
		
		if(s == e)
		{
			int value = tree[treeNode];
			tree[treeNode] = 0;
			return value;
		}
		
		int mid = (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, idx) + 
				query(treeNode << 1 | 1, mid + 1, e, idx);
		
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] += (e - s + 1)*lazy[treeNode];
			if(s!=e) {
				lazy[treeNode << 1]		+= lazy[treeNode];
				lazy[treeNode << 1 | 1] += lazy[treeNode];
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