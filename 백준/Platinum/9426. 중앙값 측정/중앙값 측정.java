//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9426
//1초 / 256MB
//슬라이딩 윈도우로 특정 범위안에서 숫자를 넣고 빼며 중앙 값을 구한다.
class Main{
	
	static final int LEN = 65535;
	static int[] arr, tree;
	static int N, K;
	static long result;
	
	public static void main(String[] args)throws Exception{
		N	= read();	// 1<=이십오만
		K	= read();	// 0<=65535
		arr	= new int[N];
		tree= new int[LEN<<2];
		
		for(int i=0; i<N; i++)
		{
			arr[i] = read();
			if(i < K)
				update(1, 0, LEN, arr[i], 1);
		}
		
		int k = (K + 1) / 2;
		
		result += query(1, 0, LEN, k);
		
		for(int i=K; i<N; i++)
		{
			update(1, 0, LEN, arr[i-K], -1);
			update(1, 0, LEN, arr[i], 1);
			result += query(1, 0, LEN, k);
		}
		
		System.out.print(result);
	}
	public static int query(int treeNode, int s, int e, int target) {
		if(s == e)
			return s;
		
		int mid = (s + e) >> 1;
		int nextNode = treeNode << 1;
		if(tree[nextNode] >= target)
			return query(nextNode, s, mid, target);
		else
			return query(nextNode | 1, mid + 1, e, target - tree[nextNode]);
	}
	public static void update(int treeNode, int s, int e, int idx, int value) {
		if(e < idx || idx < s)
			return;
		
		tree[treeNode] += value;
		
		if(s != e)
		{
			int mid = (s + e) >> 1;
			update(treeNode<<1, s, mid, idx, value);
			update(treeNode<<1 | 1, mid + 1, e, idx, value);
		}
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}