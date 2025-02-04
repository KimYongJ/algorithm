//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16493
//2초 / 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	//남은기간(1<=200)
		int M		= Integer.parseInt(st.nextToken());	//챕터수(1<=20)
		int dp[][]	= new int[M+1][N+1];
		int day[]	= new int[M+1];
		int page[]	= new int[M+1];
		
		for(int i=1; i<=M; i++)
		{
			st = new StringTokenizer(br.readLine());
			day[i] = Integer.parseInt(st.nextToken());	//소요일수1<=20
			page[i] = Integer.parseInt(st.nextToken());	//페이지수1<=300
		}
		// 챕터 반복
		for(int i=1; i<=M; i++)
		{
			// 남은 기간 반복
			for(int j=1; j<=N; j++)
			{
				dp[i][j] = dp[i-1][j];
				
				if(0<=j-day[i])
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-day[i]] + page[i]);
			}
		}
		System.out.print(dp[M][N]);
	}
}
