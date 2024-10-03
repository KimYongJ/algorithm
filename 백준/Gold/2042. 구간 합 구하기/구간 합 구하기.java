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
	// 파라미터 : 원소배열, 현재노드, 현재구간 배열시작, 현재구간 배열 끝
	public static long init(long tree[], long[] arr, int node, int start, int end) {
		if(start == end)	// 배열의 시작과 끝이 같다면 leaf노드이므로 원소 배열 값 그대로 담는다.
			return tree[node] = arr[start];
		else				// leaf 노드가 아닐 경우 왼쪽, 오른쪽 자식노드의 합을 담는다.
			return tree[node] = init(tree, arr, node<<1, start, (start + end) >> 1)
							+	init(tree, arr, (node<<1) + 1, ((start+end) >> 1) + 1, end);
	}
	// 파라미터 : 현재노드 , 배열의 시작, 배열의 끝, 변경된 데이터의 인덱스, 원래 데이터 값과 변경 데이터 값의 차이
	public static void update(long tree[], int node, int start, int end, int idx, long diff) {
		if(idx < start || end < idx) // 변경할 idx 값이 범위 밖이면 종료
			return;
		// 차를 저장
		tree[node] += diff;
		
		// 리프노드가 아닐 경우 아래 자식노드들(왼쪽, 오른쪽)도 확인
		if(start != end) {
			update(tree, node<<1, start, (start+end) >> 1, idx, diff);
			update(tree, (node<<1)+1, ((start+end)>>1)+1, end, idx, diff);
		}
	}
	// 파라미터 : 현재 노드, 배열의 시작, 배열의 끝, 원하는 누적합의 시작, 원하는 누적합의 끝
	public static long sum(long tree[], int node, int start, int end, int left, int right)
	{
		if(left> end || right < start) 
			return 0;
		// 범위내 완전 포함시 바로 리턴
		if(left <= start && end <= right)
			return tree[node];
		
		return sum(tree, node<<1, start, (start+end)>>1, left, right)
				+ sum(tree, (node<<1)+1, ((start+end)>>1)+1, end, left, right);
	}
	public static void main(String[] args)throws Exception{
		StringBuilder 	sb = new StringBuilder();
		int N = (int)read();							// 수의 개수(1<=백만)
		int M = (int)(read() + read());					// 수의 변경이 일어나는 횟수(1<=만) + 구간의 합을 구하는 횟수
		
		long[] arr = new long[N+1];
		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		int h			= (int) Math.ceil(Math.log(N) / Math.log(2));
		int treeSize	= (int)Math.pow(2, h + 1);		// 높이를 이용해 배열의 사이즈를 구한다.
		long tree[]		= new long[treeSize];			// 각 원소가 담길 일차원 배열(논리상 트리로 이용)
		
		init(tree, arr, 1, 1, N);						// 파라미터 : 원소배열, 현재노드, 현재구간 배열시작, 현재구간 배열 끝
		
		while(M-->0)
		{
			int cmd = (int)read();
			int a	= (int)read();
			long b	= read();
			if(cmd == 1) {								// b번째 수를 c로 바꾸기
				update(tree, 1, 1, N, a, b-arr[a]);		// 파라미터 : 현재노드 , 배열의 시작, 배열의 끝, 변경된 데이터의 인덱스, 원래 데이터 값과 변경 데이터 값의 차이
				arr[a] = b;
			}
			else										// b번째 수부터 c번째 수까지 합 구하기
				sb.append(sum(tree, 1,  1,  N,  a, (int)b)).append('\n');// 파라미터 : 현재 노드, 배열의 시작, 배열의 끝, 원하는 누적합의 시작, 원하는 누적합의 끝
		}
		System.out.print(sb);
	}
}