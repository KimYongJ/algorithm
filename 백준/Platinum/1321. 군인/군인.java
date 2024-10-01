//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1321
class Main{
	
	static int N, H;
	static int[] arr, tree;
	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
    
	public static int init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = arr[s];
		
		int mid = (s + e) >> 1;
		return tree[treeNode] = init(treeNode*2, s, mid) + init(treeNode*2 + 1, mid + 1, e);
	}
	
	public static void update(int treeNode, int s, int e, int originIdx, int diff) {
		if(originIdx < s || e < originIdx)
			return;
		
		tree[treeNode] += diff;
		
		if(s != e) {
			int mid = (s + e) >> 1;
			update(treeNode * 2, s, mid, originIdx, diff);
			update(treeNode * 2 + 1, mid + 1, e, originIdx, diff);
		}
	}
	
	public static int query(int treeNode, int s, int e, long target)
	{
		if(s == e)	// 리프노드 도달시 정답이기 때문에 바로 리턴, 루트 노드 도달시 무조건 정답이 되도록 아래 코드를 잘 만들어야 함
			return s;

		int mid = (s + e) >> 1;
        
		if(target <= tree[treeNode * 2])
			return query(treeNode * 2, s , mid, target);
		else
			return query(treeNode * 2 + 1, mid + 1, e, target - tree[treeNode * 2]);
	}
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		N		= read();
		H		= (int)Math.ceil(Math.log(N) / Math.log(2));
		arr		= new int[N+1];
		tree	= new int[1<<(H + 1)];
		
		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		init(1, 1, N);				// 세그먼트 트리 초기화, 각 노드의 누적합을 구한다.
		
		int T = read();
		
		while(T-- > 0)
		{
			// 부대 갱신
			if(read() == 1)			// 목표 위치와, 변경할 값을 전달하여 세그먼트 트리를 업데이트한다.
				update(1, 1, N, read(), read());
			// 해당 값의 부대 출력
			else					// 군인군번을 누적합과 비교해가며 답을 찾는다.
				sb.append( query(1, 1, N, read()) ).append('\n');
		}
		System.out.print(sb.toString());
	}
}