//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17291
//1초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[]args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int dp[]	= new int[N+2];
		int odd[]	= new int[N+2];
		int even[]	= new int[N+2];
		
		dp[1] = odd[1] = 1;
		
		for(int i=2; i<=N; i++)
		{
			dp[i] += dp[i-1] << 1;
			
			if(i%2 == 0)// 짝수 년도
				even[i] = dp[i-1];
			else		// 홀수 년도
				odd[i] = dp[i-1];
			
			if(1<= i-3)
				dp[i] -= odd[i-3];
			if(1<= i-4)
				dp[i] -= even[i-4];
		}
		System.out.print(dp[N]);
	}
}