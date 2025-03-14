//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2662
//1초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 투자금액N(1<=300)
		int M = Integer.parseInt(st.nextToken());// 투자가능 기업들의 개수M(1<=20)
		int value[][] = new int[N + 1][M + 1];
		
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			for(int j=1; j<=M; j++)
				value[i][j] = Integer.parseInt(st.nextToken());// 투자가능 기업들의 개수M(1<=20)
		}
		// dp[현재투자액][기업종류] = 최대 가치
		int dp[][] = new int[N + 1][M + 1];
		// n만큼 자금을 m기업에 투자했을 때 투자한 최적해 기록
		int choice[][] = new int[N + 1][M + 1];
		// 기업들 개수
		for(int m=1; m<=M; m++)
		{
			// 투자금액
			for(int n=1; n<=N; n++)
			{
				dp[n][m] = dp[n][m-1];	// 아무것도 선택하지 않았을 때
				
				// 현재 투자할 돈
				for(int k=n; k>=1; k--)
				{
					int nextValue = dp[n-k][m-1] + value[k][m];
					if(dp[n][m] < nextValue)
					{
						dp[n][m] = nextValue;
						choice[n][m] = k; // m번 기업에 k를 투자
					}
				}
			}
		}
		
		// 역추적을 통해 각 기업에 투자한 금액 알아내기
		int[] investAmt = new int[M + 1];
		int remain = N;
		for(int m=M; m>=1; m--)
		{
			investAmt[m] = choice[remain][m];
			remain -= investAmt[m];
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(dp[N][M]).append('\n');
		
		for(int m=1; m<=M; m++)
			sb.append(investAmt[m]).append(' ');
		
		System.out.print(sb);
	}
}

