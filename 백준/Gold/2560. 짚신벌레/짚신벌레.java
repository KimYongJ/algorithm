//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2560
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int dp[] = new int[N+1];
		
		dp[0] = 1;
		
		for(int i=1; i<=N; i++)
		{
			dp[i] = dp[i-1];
			if(0 <= i-a)
				dp[i] += dp[i-a];
			if(0 <= i-b)
				dp[i] -= dp[i-b] - 1000;
			
			dp[i] %= 1000;
		}
		
		if(0 <= N-d)
			dp[N] -= dp[N-d] - 1000;
		
		System.out.print(dp[N] % 1000);
	}
}