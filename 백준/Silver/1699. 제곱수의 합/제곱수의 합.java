//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1699
//2초 / 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 1<=십만
		int dp[] = new int[N+1];
		for(int i=1; i<=N; i++)
			dp[i] = i;
		
		for(int i=2; i<=N; i++)
			for(int j=2; j*j<=i; j++)
				dp[i] = Math.min(dp[i], dp[i-j*j] + 1);

		System.out.print(dp[N]);
	}
}