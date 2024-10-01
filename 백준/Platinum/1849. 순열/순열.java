//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1849
class Main{
	
	static int N, H, idx;
	static int[] arr, tree;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static int init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = 1;	// 리프노드는 1 대입
		
		int mid = (s + e) >> 1;
		// 내부 노드들은 리프노드부터 누적합으로 계산
		return tree[treeNode] = init(treeNode*2, s, mid) + init(treeNode*2 + 1, mid + 1, e);
	}
	public static void update(int treeNode, int s, int e,int originIdx) {
		if(originIdx < s || e < originIdx)	// s~e범위가 originIdx에 해당하지 않으면 바로 종료
			return;
		
		tree[treeNode]--;	// 값 업데이트
		
		if(s != e)			// 리프노드가 아닌 경우 계속 연산함
		{
			int mid = (s + e) >> 1;
			update(treeNode * 2, s, mid , originIdx);
			update(treeNode * 2 + 1, mid +1, e, originIdx);
		}
	}
	public static void query(int treeNode, int s, int e, int cnt) {
		if(s == e)				// 리프노드에 들어 왔다는 것은 cnt만큼 앞이 비어있다는 것을 의미
		{
			update(1, 1, N, s);	// 해당 위치는 사용했기 때문에 update해서 s(리프노드)부터 루트노드까지 -1을 빼줌
			arr[s] = ++idx;		// 결과 대입
			return;
		}
		int mid = (s + e) >> 1;
		if(cnt <= tree[treeNode*2])
			query(treeNode * 2, s , mid, cnt);
		else
			query(treeNode * 2 + 1, mid + 1, e, cnt - tree[treeNode * 2]);
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		N	= read();										// 원소개수 (1<=십만)
		H	= (int)Math.ceil(Math.log(N) / Math.log(2));	// 세그먼트 트리의 높이
		arr = new int[N + 1];
		tree= new int[1<<(H + 1)];
		
		// 세그먼트 트리의 노드들은 자기 앞에 비어있는 숫자를 갖고있는 것으로 생각하면됨, 각 리프노드들은 1이며, 그위 노드들은 리프노드들의 누적합
		init(1, 1, N);
		// 	각 입력에 대해 1번부터 N번까지 숫자 중 자기 앞에 비어있는 숫자와 같은 곳에 들어가 결과를 대입한다
		for(int i=0; i<N; i++)
			query(1, 1, N, read() + 1);
		
		for(int i=1; i<=N; i++)
			sb.append(arr[i]).append('\n');
		
		System.out.print(sb);
	}
}