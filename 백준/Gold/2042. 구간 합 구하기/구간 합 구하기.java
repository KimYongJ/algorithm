//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2042
class Main{
    static long read() throws Exception {
    	long c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
    // 세그먼트 트리를 초기화하는 메서드
    // tree[]	: 세그먼트 트리를 저장할 배열
    // arr[]	: 원본 배열
    // idx		: 현재 노드 인덱스
    // start	: 현재 노드가 담당하는 구간의 시작 인덱스
    // end		: 현재 노드가 담당하는 구간의 끝 인덱스
	public static long init(long tree[], long[] arr, int idx, int start, int end) {
		if(start == end)	// 배열의 시작과 끝이 같다면 leaf노드이므로 원소 배열 값 그대로 담는다.
			return tree[idx] = arr[start];
		else				// 내부노드인 경우 왼쪽, 오른쪽 자식노드의 합을 담는다.
		{
			int mid = (start + end) >> 1;
			return tree[idx] = init(tree, arr, idx<<1, start, mid)
							+	init(tree, arr, (idx<<1) + 1, mid + 1, end);
		}
	}
    // 세그먼트 트리를 업데이트하는 메서드
    // tree[]	: 세그먼트 트리 배열
    // node		: 현재 노드 인덱스
    // start	: 현재 노드가 담당하는 구간의 시작 인덱스
    // end		: 현재 노드가 담당하는 구간의 끝 인덱스
    // idx		: 업데이트할 배열의 인덱스
    // diff		: 업데이트 값 (변경된 값 - 기존 값)
	public static void update(long tree[], int node, int start, int end, int idx, long diff) {
		if(idx < start || end < idx) // 변경할 idx 값이 범위 밖이면 종료
			return;
		// 현재 노드의 합에 차이를 반영
		tree[node] += diff;
		
		// 리프노드가 아닐 경우 아래 자식노드들(왼쪽, 오른쪽)도 확인
		if(start != end)
		{
			int mid = (start + end) >> 1;
			update(tree, node<<1, start, mid, idx, diff);
			update(tree, (node<<1)+1, mid+1, end, idx, diff);
		}
	}
    // 세그먼트 트리를 이용해 구간 합을 계산하는 메서드
    // tree[]	: 세그먼트 트리 배열
    // node		: 현재 노드 인덱스
    // start	: 현재 노드가 담당하는 구간의 시작 인덱스
    // end		: 현재 노드가 담당하는 구간의 끝 인덱스
    // left		: 쿼리의 시작 인덱스
    // right	: 쿼리의 끝 인덱스
	public static long sum(long tree[], int node, int start, int end, int left, int right)
	{
		// 현재 구간이 쿼리 범위 밖인 경우
		if(left> end || right < start) 
			return 0;
		// 현재 구간이 쿼리 범위에 완전히 포함되는 경우
		if(left <= start && end <= right)
			return tree[node];
		// 부분적으로 포함되는 경우 자식 노드의 합을 구함
		int mid = (start + end) >> 1;
		return  sum(tree, node<<1, start, mid, left, right)
				+ sum(tree, (node<<1)+1, mid+1, end, left, right);
		
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N = (int)read();	// 수의 개수(1<=백만)
		int M = (int)read();	// 수의 변경이 일어나는 횟수(1<=만)
		int K = (int)read();	// 구간의 합을 구하는 횟수
		
		long[] arr = new long[N+1];
		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		int h			= (int) Math.ceil(Math.log(N) / Math.log(2));
		int treeSize	= (int)Math.pow(2, h + 1);	// 높이를 이용해 배열의 사이즈를 구한다.
		long tree[]		= new long[treeSize];		// 각 원소가 담길 일차원 배열(논리상 트리로 이용)
		
        // 세그먼트 트리 초기화, 루트 노드는 1번 인덱스
		init(tree, arr, 1, 1, N);
		
		for(int i=0; i<M+K; i++)
		{
			int cmd = (int)read();
			int a	= (int)read();
			long b	= read();
			if(cmd == 1)// b번째 수를 c로 바꾸기
			{
				update(tree, 1, 1, N, a, b-arr[a]);// 파라미터 : 세그먼트배열, 현재노드 , 배열의 시작, 배열의 끝, 변경된 데이터의 인덱스, 원래 데이터 값과 변경 데이터 값의 차이
				arr[a] = b;
			}
			else// b번째 수부터 c번째 수까지 합 구하기
				sb.append(sum(tree, 1,  1,  N,  a, (int)b)).append('\n');// 파라미터 : 세그먼트배열, 현재 노드, 배열의 시작, 배열의 끝, 원하는 누적합의 시작, 원하는 누적합의 끝
		}
		System.out.print(sb);
	}
}