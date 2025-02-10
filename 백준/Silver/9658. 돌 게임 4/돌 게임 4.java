//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9658
//1초 / 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int dp[] = new int[N+6];
		dp[2] = dp[4] = dp[5] = 1;// 상근이가 이긴것
		
		for(int i = 6; i<=N; i ++)
		{
			if(dp[i-1] == 1 && dp[i-3] == 1 && dp[i-4] == 1)
				dp[i] = 0;
			else
				dp[i] = 1;
		}
			
		System.out.print(dp[N] == 1 ? "SK" : "CY");
	}
}