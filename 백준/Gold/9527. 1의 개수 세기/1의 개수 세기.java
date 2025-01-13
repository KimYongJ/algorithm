//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9527
class Main{
	
	static long dp[] = new long[55];
	
	public static void main(String[] args)throws Exception{
		long a = read();
		long b = read();
		
		dp[0] = 1;
		for(int i=1; i<55; i++)				// dp[i]는 오른쪽에서 i번째 비트까지 총 누적합
			dp[i] = (dp[i-1] << 1L) + (1L<<i);// dp[i] = dp[i-1]의 2배 + 2의i승
			
		System.out.print(cal(b) - cal(a - 1L));
	}
	public static long cal(long x) {
		long ans = x & 1;
		for(int i=54; i>0; i--)
			if((x & (1L<<i)) != 0)	// x의 i번째 비트가 1인지 탐색
			{
				x -= 1L <<i;		// x의 최상위 비트 값을 0으로 만든다.
			    // dp[i-1]은 i-1번째 비트까지의 숫자들의 모든 1의 개수를 의미한다.
			    // x + 1은 최상위 비트를 제거하기 전, x 이하에서 i번째 비트가 1인 숫자들의 개수를 의미한다.
			    // 여기서 +1은 자기 자신을 포함하기 위함이다.
				ans += dp[i-1] + (x + 1);
			}
		
		return ans;
	}
	static long read() throws Exception {
		long c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}