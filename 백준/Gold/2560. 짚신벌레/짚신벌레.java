//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2560
class Main{
	public static void main(String[] args)throws Exception{
		int a		= read();		// 성체가 되는 날
		int b		= read();		// 더이상 새로운 객체를 만들지 않는 날
		int d		= read();		// 없어지는날
		int N		= read();		// 총 일수
		int dp[]	= new int[N+1];	// i일자에 있는 개체수
		
		dp[0] = 1;	// 0일째 1마리를 넣는다.
		
		for(int i=1; i<=N; i++)
		{
			dp[i] = dp[i-1];						// 이전날을 이어 받음
			
			if(0 <= i-a) dp[i] += dp[i-a];			// i-a날 개체들이 i날 성체가 되므로 플러스
			// 음수를 없애기 위해 -천을한다.
			if(0 <= i-b) dp[i] -= dp[i-b] - 1000;	// i-b날 개체들이 더 이상 생성 못하므로 마이너스 
			
			dp[i] %= 1000;							// 천으로 나눈 나머지 저작
		}
		
		if(0 <= N-d)								// N일 빼기 d일에 있던 개체들은 N일에 없어지므로 마이너스
			dp[N] -= dp[N-d] - 1000;
		
		System.out.print(dp[N] % 1000);				// 최종결과
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}