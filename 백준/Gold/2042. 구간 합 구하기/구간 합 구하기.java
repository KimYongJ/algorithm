//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2042
class Main{
	
	static long[] arr, tree;

	// 파라미터 : 현재노드, 현재구간 배열시작, 현재구간 배열 끝
	public static long init(int node, int start, int end) {
		if(start == end)	// 배열의 시작과 끝이 같다면 leaf노드이므로 원소 배열 값 그대로 담는다.
			return tree[node] = arr[start];
		else				// leaf 노드가 아닐 경우 왼쪽, 오른쪽 자식노드의 합을 담는다.
			return tree[node] = init(node<<1, start, (start + end) >> 1)
							+	init((node<<1) + 1, ((start+end) >> 1) + 1, end);
	}
	// 파라미터 : 현재노드 , 배열의 시작, 배열의 끝, 변경된 데이터의 인덱스, 원래 데이터 값과 변경 데이터 값의 차이
	public static void update(int node, int start, int end, int idx, long diff) {
		if(idx < start || end < idx) // 변경할 idx 값이 범위 밖이면 종료
			return;
		// 차를 저장
		tree[node] += diff;
		
		// 리프노드가 아닐 경우 아래 자식노드들(왼쪽, 오른쪽)도 확인
		if(start != end)
		{
			update(node<<1, start, (start+end) >> 1, idx, diff);
			update((node<<1)+1, ((start+end)>>1)+1, end, idx, diff);
		}
	}
	// 파라미터 : 현재 노드, 배열의 시작, 배열의 끝, 원하는 누적합의 시작, 원하는 누적합의 끝
	public static long sum(int node, int start, int end, int left, int right)
	{
		if(left> end || right < start) 
			return 0;
		// 범위내 완전 포함시 바로 리턴
		if(left <= start && end <= right)
			return tree[node];
		
		return sum(node<<1, start, (start+end)>>1, left, right)
				+ sum((node<<1)+1, ((start+end)>>1)+1, end, left, right);
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb	= new StringBuilder();
		Reader in			= new Reader();
		
		int N	= in.nextInt();							// 수의 개수(1<=백만)
		int M	= in.nextInt() + in.nextInt();			// 수의 변경이 일어나는 횟수(1<=만) + 구간의 합을 구하는 횟수
		int h	= (int)Math.ceil(Math.log(N) / Math.log(2));
		arr 	= new long[N+1];
		tree	= new long[1 << (h + 1)];				// 높이를 이용해 세그먼트 트리로 논리상 사용될 배열의 사이즈를 세팅
		
		for(int i=1; i<=N; i++)
			arr[i] = in.nextLong();

		init(1, 1, N);									// 파라미터 : 현재노드, 현재구간 배열시작, 현재구간 배열 끝
		
		while(M-->0)
		{
			int cmd = in.nextInt();
			int a	= in.nextInt();
			long b	= in.nextLong();
			
			if(cmd == 1)								// b번째 수를 c로 바꾸기
			{
				update(1, 1, N, a, b-arr[a]);			// 파라미터 : 현재노드 , 배열의 시작, 배열의 끝, 변경된 데이터의 인덱스, 원래 데이터 값과 변경 데이터 값의 차이
				arr[a] = b;
			}
			else										// b번째 수부터 c번째 수까지 합 구하기
				sb.append(sum(1,  1,  N,  a, (int)b))
                    .append('\n');// 파라미터 : 현재 노드, 배열의 시작, 배열의 끝, 원하는 누적합의 시작, 원하는 누적합의 끝
		}
		System.out.print(sb);
	}
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32) { if (size < 0) return -1; }
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    long nextLong() throws Exception {
        long n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32);
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}

