//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17271
//1초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int MOD = 1_000_000_007;
		int N		= Integer.parseInt(st.nextToken());	// 최대시간 1<=만
		int M		= Integer.parseInt(st.nextToken());	// B스킬 시간 2<=100
		int dp[]	= new int[10_001];
		if(M == 1)
		{
			dp[1] = 2;
			for(int i=2; i<=N; i++)
			{
				dp[i] = dp[i-1] << 1;
				if(dp[i] >= MOD)
					dp[i] -=MOD;
			}
			
			System.out.print(dp[N]);
			return;
		}
		for(int i=1; i<M; i++)
			dp[i] = 1;
		
		dp[M] = 2;
		
		for(int i=M + 1; i<=N; i++)
		{
			dp[i] = dp[i-1] + dp[i-M];
			if(dp[i] >= MOD)
				dp[i] -=MOD;
		}
		System.out.print(dp[N]);
	}
}