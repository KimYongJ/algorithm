//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2118
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();
		int arr[]	= new int[N+1];
		int max		= 0;
		int s		= 0;
		int e		= 1;
		
		for(int i=1; i<=N; i++)
			arr[i] = read() + arr[i-1];

		int half = arr[N]>>1;
		int full = arr[N];
		// 기본아이디어 : 정답은 총둘레의 절반을 초과 할 수 없다.
		while(s<e && e<=N)				// s와e가 겹치지않고, e가 최대값 안일 때만 반복
		{
			int diff = arr[e] - arr[s];	// 현재 포인터들의 차이를 구함
			if(diff < half)				// 절반 보다 작다면 e를 더해 더 넓은 범위 탐색
			{
				max = Math.max(max, diff);
				++e;
			}
			else if(half < diff)		// 절반보다 크다면 full에서 diff를 빼서 다른방향의 길이를 구한다.
			{
				max = Math.max(max, full - diff);
				++s;					// s를 추가해 범위를 줄인다.
			}
			else						// half와 diff가 딱 맞다면 최대값이므로 종결
			{
				max = half;
				break;
			}
		}
		System.out.print(max);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}