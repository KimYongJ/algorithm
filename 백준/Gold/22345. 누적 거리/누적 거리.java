//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/22345
import java.util.Arrays;
class Node{
	long cnt, pos;Node(long cnt, long pos){this.cnt=cnt; this.pos=pos;}
}
class Main{
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
	public static int binarySearch(long arr[], long target, int s, int e) {
		int res = e;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(arr[mid] <= target)
				s = mid + 1;
			else {
				e = mid - 1;
				res = mid;
			}
		}
		return res;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder	sb = new StringBuilder();
		int N		= read();			// 마을의개수(1<=20만)
		int Q		= read();			// 후보 좌표의 수(1<=20만)
		Node node[]	= new Node[N + 2];
		long L[]	= new long[N + 2];	// 왼쪽 누적비용
		long R[]	= new long[N + 2];	// 오른쪽 누적비용
		long S[]	= new long[N + 2];	// 인구 누적합
		long X[]	= new long[N + 2];	// 위치 정보
		
		// 마을 데이터 입력
		for(int i=1; i<=N; i++)
		{
			long cnt = read();
			long pos = read();
			node[i] = new Node(cnt, pos);
		}
		// 마을 위치기준 오름차순 정렬
		Arrays.sort(node, 1, N + 1, (a,b) -> Long.compare(a.pos ,b.pos));
		
		// 누적합 계산
		for(int i=1; i<=N; i++)
		{
			X[i] = node[i].pos;												// 위치 정보 삽입
			S[i] = S[i - 1] + node[i].cnt;									// 인구 누적합
			L[i] = L[i - 1] + node[i].cnt * node[i].pos;					// 왼쪽 비용 누적합
			
			int rIdx = N - i + 1;
			R[rIdx] = R[rIdx + 1] + node[rIdx].cnt * node[rIdx].pos;		// 오른쪽 누적 비용
		}
		
		while(Q-- > 0)
		{
			long pos = read();	// 후보 좌표
			
			// idx는 pos보다 큰 첫번 째 마을 인덱스
			int idx = binarySearch(X, pos, 1, N+1);
			
			// pos * S[idx - 1] = idx 이전의 모든 마을의 인구수 합계에 후보 좌표를 곱한것
			// - L[idx - 1] = idx 이전 마을까지의 모든 위치에 대한 누적비용
			// R[idx] = idx 이후 마을들의 누적합을 의미
			// pos * (S[N] - S[idx - 1]) = idx 이후 마을들의 인구수 합을 곱한 것으로 발생하는 인구 비용의 합 계산
			long result = pos * S[idx - 1] - L[idx - 1] + R[idx] - pos * (S[N] - S[idx - 1]);
			
			sb.append(result).append('\n');
		}
		System.out.print(sb);
	}
}
