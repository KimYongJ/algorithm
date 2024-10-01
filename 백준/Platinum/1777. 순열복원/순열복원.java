//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1777
class Main{
	
	static int N, H;
	static int[] arr, tree, res;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	// 각 리프노드는 1로하고 나머지 노드들은 모두 누적합을 구한다.
	public static int init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = 1;
		
		int mid = (s + e) >> 1;
		
		return tree[treeNode] = init(treeNode*2, s, mid) + init(treeNode*2 + 1, mid + 1, e);
	}
	// 주어진 cnt만큼 리프노드에서 오른쪽으로 간 리프노드를 찾는다. cnt가 1이면 리프노드의 1이 왼쪽에서 한번 나온 위치의 다음 위치로, 즉 2번째로 감
	public static int query(int treeNode, int s, int e, int cnt) {
		
		tree[treeNode]--;// 현재 방문한 곳에서 하나를 빼주어 추후 연산을 줄여준다.
		
		if(s == e)
			return s;
		
		int mid = (s + e) >> 1;
		
		return cnt < tree[treeNode * 2]					? 
					query(treeNode * 2, s, mid, cnt)	: 
					query(treeNode * 2 + 1, mid + 1, e, cnt - tree[treeNode * 2]);
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		N		= read();	// 순열 크기 (1<=십만)
		H		= (int)Math.ceil(Math.log(N+1) / Math.log(2));
		arr 	= new int[N + 1];
		res 	= new int[N + 1];
		tree	= new int[1<<(H+1)];
		
		init(1, 1, N);
		
		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		for(int i=N; i>0; i--)		// 뒤에서부터 탐색하며 왼쪽에서 오른쪽으로 채워나간다.
			res[query(1, 1, N, arr[i])] = i;
		
		for(int i=N; i>=1; i--)
			sb.append(res[i]).append(' ');
		
		System.out.print(sb);		
	}
}