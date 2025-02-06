//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15988
//1초 / 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		final long MOD = 1_000_000_009;
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			int N = Integer.parseInt(br.readLine());	// 1<=백만
			long dp[] = new long[N+4];
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;
			
			for(int i=4; i<=N; i++)
				dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % MOD;
			
			sb.append(dp[N]).append('\n');
		}
		System.out.print(sb);
	}
}
