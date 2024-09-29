//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2042
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class SegmentTree{
	long tree[];	// 각 원소가 담길 일차원 배열(논리상 트리로 이용)
	int treeSize;	// 트리의 크기
	public SegmentTree(int arrSize) {
		// 트리의 높이
		int h		= (int) Math.ceil(Math.log(arrSize) / Math.log(2));
		treeSize	= (int)Math.pow(2, h + 1);	// 높이를 이용해 배열의 사이즈를 구한다.
		tree		= new long[treeSize];		// 배열 초기화(논리상 트리로 이용)
	}
	// 파라미터 : 원소배열, 현재노드, 현재구간 배열시작, 현재구간 배열 끝
	public long init(long[] arr, int node, int start, int end) {
		if(start == end)	// 배열의 시작과 끝이 같다면 leaf노드이므로 원소 배열 값 그대로 담는다.
			return tree[node] = arr[start];
		else				// leaf 노드가 아닐 경우 왼쪽, 오른쪽 자식노드의 합을 담는다.
			return tree[node] = init(arr, node<<1, start, (start + end) >> 1)
							+	init(arr, (node<<1) + 1, ((start+end) >> 1) + 1, end);
	}
	// 파라미터 : 현재노드 , 배열의 시작, 배열의 끝, 변경된 데이터의 인덱스, 원래 데이터 값과 변경 데이터 값의 차이
	public void update(int node, int start, int end, int idx, long diff) {
		if(idx < start || end < idx) // 변경할 idx 값이 범위 밖이면 종료
			return;
		// 차를 저장
		tree[node] += diff;
		
		// 리프노드가 아닐 경우 아래 자식노드들(왼쪽, 오른쪽)도 확인
		if(start != end) {
			update(node<<1, start, (start+end) >> 1, idx, diff);
			update((node<<1)+1, ((start+end)>>1)+1, end, idx, diff);
		}
	}
	// 파라미터 : 현재 노드, 배열의 시작,배열의 끝, 원하는 누적합의 시작, 원하는 누적합의 끝
	public long sum(int node, int start, int end, int left, int right) {
		if(left> end || right < start) 
			return 0;
		// 범위내 완전 포함시 바로 리턴
		if(left <= start && end <= right)
			return tree[node];
		
		return sum(node<<1, start, (start+end)>>1, left, right)
				+ sum((node<<1)+1, ((start+end)>>1)+1, end, left, right);
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder 	sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());	// 수의 개수(1<=백만)
		int M = Integer.parseInt(st.nextToken());	// 수의 변경이 일어나는 횟수(1<=만)
		int K = Integer.parseInt(st.nextToken());	// 구간의 합을 구하는 횟수
		
		long[] arr = new long[N+1];
		for(int i=1; i<=N; i++)
			arr[i] = Long.parseLong(br.readLine());
		
		SegmentTree sgt = new SegmentTree(N);
		
		sgt.init(arr, 1, 1, N);
		
		for(int i=0; i<M+K; i++)
		{
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a	= Integer.parseInt(st.nextToken());
			long b	= Long.parseLong(st.nextToken());
			if(cmd == 1)// b번째 수를 c로 바꾸기
			{
				sgt.update(1, 1, N, a, b-arr[a]);// 파라미터 : 현재노드 , 배열의 시작, 배열의 끝, 변경된 데이터의 인덱스, 원래 데이터 값과 변경 데이터 값의 차이
				arr[a] = b;
			}
			else// b번째 수부터 c번째 수까지 합 구하기
				sb.append(sgt.sum(1,  1,  N,  a, (int)b)).append('\n');// 파라미터 : 현재 노드, 배열의 시작, 배열의 끝, 원하는 누적합의 시작, 원하는 누적합의 끝
		}
		System.out.print(sb);
	}
}