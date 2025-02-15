//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27134
//1초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N	= Integer.parseInt(br.readLine());
		int max = N*(N + 1) / 2;
		
		if(max%2 != 0)
		{
			System.out.print(0);
			return;
		}
		
		max /= 2;
		
		long dp[] = new long[max + 1];
		
		dp[0] = 1;
		
		for(int i=1; i<=N; i++)
			for(int j=max; j>=i; j--)
				dp[j] += dp[j - i];

		System.out.print(dp[max] / 2);
	}
}