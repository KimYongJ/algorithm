//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11051
//1초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static final int MOD = 10_007;
	static int dp[][];
	static int N, K;
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N	= Integer.parseInt(st.nextToken());//1<=천
		K	= Integer.parseInt(st.nextToken());//1<=N
		dp	= new int[N+1][K+1];
		//N개중 K개를 고르는 경우의 수
		System.out.print( comb(N, K) );
	}
	public static int comb(int n, int k) {
		if(dp[n][k] > 0)
			return dp[n][k];
		
		if(k == 0 || n == k)
			return dp[n][k] = 1;
		
		return dp[n][k] = (comb(n-1, k) + comb(n-1,k-1)) % MOD;
	}
}
