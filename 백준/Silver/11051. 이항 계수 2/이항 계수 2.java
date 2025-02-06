//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11051
//1초 / 256MB
class Main{
	
	static final int MOD = 10_007;
	static int dp[][];
	static int N, K;
	
	public static void main(String[] args)throws Exception{
		N	= read();//1<=천
		K	= read();//1<=N
		dp	= new int[N+1][K+1];
		//N개중 K개를 고르는 경우의 수
		System.out.print( comb(N, K) );
	}
	public static int comb(int n, int k) {
		if(dp[n][k] > 0)	// 이미 값을 구한 경우 그 값 리턴
			return dp[n][k];
		if(k==0 || n==k)	// 이미 다골라서 더 이상 고를게없거나(k==0), 남은 개수와(n)와 골라야하는 개수(k)가 같은 경우 1리턴
			return dp[n][k] = 1;
		// comb(n-1,k) : n번째를 고르지 않을 경우, n-1개 중 k개를 고르는 경우가 됨
		// comb(n-1,k-1): n번째를 고를 경우, 골랏기 때문에 k에 -1을 해서 다음에 고를 개수를 줄여준다. 그러므로 n-1, k-1이 됨.
		return dp[n][k] = (comb(n-1,k) + comb(n-1, k-1))%MOD;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}