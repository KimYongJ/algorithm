//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/4781
//3초 / 512MB
//무한 배낭 문제
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());//사탕의 종류 수(1<=오천)
			int M = (int)Math.round((Double.parseDouble(st.nextToken()) * 100));//돈의 양(0.01 <= 100.00)
			int C[] = new int[N + 1];//칼로리(1<=오천)
			int P[] = new int[N + 1];//가격(0.01<=100.00)
			int dp[][] = new int[N + 1][M + 1];// 최대 칼로리를 담음
			if(N == 0 && M == 0)
				break;
			
			for(int i=1; i<=N; i++)
			{
				st = new StringTokenizer(br.readLine());
				C[i] = Integer.parseInt(st.nextToken());//칼로리(1<=오천)
				P[i] = (int)Math.round((Double.parseDouble(st.nextToken()) * 100));//가격(0.01<=100.00)
			}
			
			for(int i=1;i <=N; i++)
			{
				for(int j=1; j<=M; j++)
				{
					dp[i][j] = dp[i-1][j];
					if(j>=P[i])
						dp[i][j] = Math.max(dp[i][j], dp[i][j-P[i]] + C[i]);
					
				}
			}
			
			int max = 0;
			for(int j=1; j<=M; j++)
				max = Math.max(max, dp[N][j]);
			
			sb.append(max).append('\n');
		}
		System.out.print(sb);
	}
}
