//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1300
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();	// 배열크기 N (1<=십만)
		int K		= read();	// k(십억 <= 백억)
		int res		= 0;		// 결과
		int s		= 1;		// 탐색 시작값
		int e		= K;		// 탐색 종료값, 특정 수 X는 K를 넘을 수 없다.
		
		while(s <= e)
		{
			int mid = (s + e) >> 1;	// mid보다 작은 숫자의 개수를 찾는다.
			int cnt = 0;
			for(int i=1; i<=N; i++)	// 1부터 N단까지 mid로 나누었을 때 개수
				cnt += Math.min(mid / i, N);
			
			if(K <= cnt)
			{
				res = mid;
				e = mid - 1;
			}
			else
				s = mid + 1;
		}
		
		System.out.print(res);
	}
}