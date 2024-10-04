//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14427
class Main{
	
	static int[] arr, tree;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static int init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = arr[s];
		
		int mid = (s + e) >> 1;
		
		return tree[treeNode] = Math.min(init(treeNode*2, s, mid), init(treeNode*2+1, mid + 1, e));
	}
	
	public static void update(int treeNode, int s, int e, int originIdx, int diff) {
		if(originIdx < s || e < originIdx)
			return;

		if(s == e){
			tree[treeNode] = diff;
			return;
		}
		
		int mid				= (s + e) >> 1;
		int nextTreeNode	= treeNode << 1;
		
		update(nextTreeNode, s, mid, originIdx, diff);
		update(nextTreeNode | 1, mid + 1, e, originIdx, diff);
		
		tree[treeNode] = Math.min(tree[nextTreeNode], tree[nextTreeNode | 1]);
	}
	
	public static int getMinIdx(int treeNode, int s, int e) {
		if(s == e)
			return s;
		
		int mid				= (s + e) >> 1;
		int nextTreeNode	= treeNode << 1;
		
		return tree[nextTreeNode] <= tree[nextTreeNode | 1] 	? 
						getMinIdx(nextTreeNode, s, mid)			:
						getMinIdx(nextTreeNode | 1, mid + 1, e);
	}
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		
		int N	= read();									// 수열의크기 (1<=십만)
		int H	= (int)Math.ceil(Math.log(N) / Math.log(2));// 세그먼트 트리의 높이
		arr 	= new int[N+1];
		tree	= new int[1<<(H+1)];
		
		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		init(1, 1, N);			// 세그먼트 트리(배열) 초기화
		
		int T = read();
		
		while(T-- > 0)
			if(read() == 2)		// 해당 배열에서 가장작은 값의 인덱스 출력			
				sb.append(getMinIdx(1, 1, N)).append('\n');
			else				// 해당 위치 값을 없데이트, 그 후 min 최신화
				update(1, 1, N, read(), read());
			

		System.out.print(sb.toString());
	}
}