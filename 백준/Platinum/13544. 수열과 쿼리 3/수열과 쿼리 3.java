//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/13544
//1초 / 512MB

class Main{
	
	static int N;
	static int[] arr;
	static int[][] tree;
	
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		N	= read();
		arr = new int[N + 1];
		tree= new int[N<<2][];

		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		init(1, 1, N);
		
		int last_ans = 0;
		
		int T = read();
		while(T-->0)
		{
			int a = read() ^ last_ans;
			int b = read() ^ last_ans;
			int c = read() ^ last_ans;
			
			last_ans = query(1, 1, N, a, b, c);
			
			sb.append(last_ans).append('\n');
		}
		
		System.out.print(sb);
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode] = new int[] {arr[s]};
			return;
		}
		int mid = (s + e) >> 1;
		init(treeNode << 1, s, mid);
		init(treeNode << 1 | 1, mid + 1, e);
		tree[treeNode] = merge(tree[treeNode << 1], tree[treeNode << 1 | 1]);
	}
	public static int query(int treeNode, int s, int e, int left, int right, int target) {
		if(e < left || right < s)
			return 0;
		if(left <= s && e <= right)
		{
			int idx = binarySearch(tree[treeNode], target);
			return idx < 0 ? 0 : tree[treeNode].length - idx;
		}
		int mid = (s + e) >> 1;
		return query(treeNode << 1, s, mid, left, right, target) + 
				query(treeNode << 1 | 1, mid + 1, e, left, right, target);
	}
	public static int binarySearch(int[] arr, int target) {
		int s = 0;
		int e = arr.length - 1;
		int r = -1;
		while(s <= e) {
			int mid = (s + e) >> 1;
			if(target < arr[mid]) {
				r = mid;
				e = mid - 1;
			}
			else s = mid + 1;
		}
		return r;
	}
	public static int[] merge(int[] left, int[] right) {
		int result[] = new int[left.length + right.length];
		int l = 0;
		int r = 0;
		int i = 0;
		while(l < left.length && r < right.length)
			result[i++] = left[l] <= right[r] ? left[l++] : right[r++];
		
		while(l < left.length)result[i++] = left[l++];
		while(r < right.length)result[i++]= right[r++];
		
		return result;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}