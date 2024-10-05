//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6213
class Main{
	
	static int[] arr, minSeg, maxSeg;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void initMin(int treeNode, int s, int e) {
		if(s == e) {
			minSeg[treeNode] = arr[s];
			return;
		}
		
		int mid			= (s + e) >> 1;
		int nextNode	= treeNode << 1;
		
		initMin(nextNode, s, mid);
		initMin(nextNode | 1, mid + 1, e);
		
		minSeg[treeNode] = Math.min(minSeg[nextNode], minSeg[nextNode | 1]);
	}
	public static void initMax(int treeNode, int s, int e) {
		if(s == e) {
			maxSeg[treeNode] = arr[s];
			return;
		}
		
		int mid			= (s + e) >> 1;
		int nextNode	= treeNode << 1;
		
		initMax(nextNode, s, mid);
		initMax(nextNode | 1, mid + 1, e);
		
		maxSeg[treeNode] = Math.max(maxSeg[nextNode], maxSeg[nextNode | 1]);
	}
	public static int getMax(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 0;
		
		if(left<=s && e<=right)
			return maxSeg[treeNode];
		
		int mid			= (s + e) >> 1;
		int nextNode	= treeNode << 1;
		
		return Math.max(getMax(nextNode, s, mid, left, right), getMax(nextNode | 1, mid + 1, e, left, right));
	}
	public static int getMin(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 1_000_001;
		
		if(left<=s && e<=right)
			return minSeg[treeNode];
		
		int mid			= (s + e) >> 1;
		int nextNode	= treeNode << 1;
		
		return Math.min(getMin(nextNode, s, mid, left, right), getMin(nextNode | 1, mid + 1, e, left, right));
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N	= read();									// 1<=5만
		int Q	= read();									// 1<=18만
		int H	= (int)Math.ceil(Math.log(N) / Math.log(2));// tree height
		arr		= new int[N+1];
		minSeg	= new int[1<<(H+1)];
		maxSeg	= new int[1<<(H+1)];
		
		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		initMin(1, 1, N);
		initMax(1, 1, N);
		
		while(Q-- > 0)
		{
			int left	= read();
			int right	= read();
			
			sb.append(getMax(1, 1, N, left, right) - getMin(1, 1, N, left, right))
				.append('\n');
		}
		System.out.print(sb);
	}
}