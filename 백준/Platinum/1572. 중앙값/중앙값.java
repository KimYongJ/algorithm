//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1572
class Main{
	static final int MAX = 65537;
	static int[] arr, tree;
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void update(int treeNode, int s, int e, int targetNum, int diff) {
		if(targetNum < s || e < targetNum)
			return;
		
		tree[treeNode] += diff;
		
		if(s != e)// 리프노드가 아닌 경우 지속 연산진행
		{
			 int mid = (s + e) >> 1;
			update(treeNode*2, s, mid, targetNum, diff);
			update(treeNode*2+1, mid + 1, e, targetNum, diff);
		}
	}
	public static int query(int treeNode, int s, int e, int cnt) {
		if(s == e)	// 리프노드인 경우는 무조건 중앙 값이 되어 반환하도록 아래 코드를 작성한다.
			return s;
		
		int mid = (s + e) >> 1;
		
		return cnt <= tree[treeNode*2]			?
				query(treeNode*2, s, mid, cnt)	: 
				query(treeNode*2 + 1, mid + 1, e, cnt - tree[treeNode*2]);
	}
	public static void main(String[] args)throws Exception{
		int N	= read();			// 수의총 개수(1<=25만)
		int K	= read();			// 수열길이(1<=오천)
		int h	= (int)Math.ceil(Math.log(MAX) / Math.log(2));//세그먼트 트리의 높이
		int cnt = (K+1) >> 1;		// 찾으려는 중앙 값 개수
		// 세그먼트 트리는 항상 개수가 K개만 들어가있도록 만든다.
		tree	= new int[1<<(h+1)];// 세그먼트 트리의 노드는 자기 자식노드들의 개수 의 합이다. 각 리프노드는 숫자를 의미한다.(1~65536까지)
		arr		= new int[N];
		
		long res = 0;
		for(int i=0, j = 1-K; i<N; i++, j++)
		{
			arr[i] = read();
			update(1, 0, MAX, arr[i], 1);		// 세그먼트 트리에 값 업데이트
			if(0<=j)
			{
				res += query(1, 0, MAX, cnt);
				update(1, 0, MAX, arr[j], -1);	// 다음 연산을 위해 처음 숫자를 -1 해준다.
			}
		}
		System.out.print(res);
	}
}