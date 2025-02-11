//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15991
//1초 / 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int MOD = 1_000_000_009;
		
		int N		= 100_001;
		long dp[]	= new long[N];
		
		dp[1] = 1;
		dp[2] = dp[3] = 2;
		dp[4] = dp[5] = 3;
		dp[6] = 6;
		
		for(int i=7; i<N; i++)
			dp[i] = (dp[i-2] + dp[i-4] + dp[i-6])%MOD;
		
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0)
		{
			int n = Integer.parseInt(br.readLine());//0<=십만
			sb.append(dp[n]).append('\n');
		}
		System.out.print(sb);
	}
}
