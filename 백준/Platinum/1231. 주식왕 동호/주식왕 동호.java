//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1231
//2초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N			= Integer.parseInt(st.nextToken());	// 주식수(1<=50)
		int D			= Integer.parseInt(st.nextToken());	// 주어진일자(2<=10)
		int M			= Integer.parseInt(st.nextToken());	// 초기자금(1<=이십만)
		int stock[][]	= new int[N + 1][D + 1];
		int dp[]		= new int[500_001];
		
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=D; j++)
			{
				stock[i][j] = Integer.parseInt(st.nextToken());// 해당 주식이 날짜에 따라 변하는 값 1<=1000
			}
		}
		
		for(int j=2; j<=D; j++)
		{
			for(int i=0; i<=M; i++)
				dp[i] = 0;
			
			for(int i=1; i<=N; i++)
			{
				int prev = stock[i][j - 1];
				int profit = stock[i][j] - prev;
				
				for(int k=prev; k<=M; k++)
					dp[k] = Math.max(dp[k], dp[k - prev] + profit);
				
			}
			
			M += dp[M];
		}
		
		System.out.print(M);		
	}
}
