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
			int C = 0;//칼로리(1<=오천)
			int P = 0;//가격(0.01<=100.00)
			int dp[] = new int[M + 1];// 최대 칼로리를 담음
			
			if(N == 0 && M == 0)
				break;
			
			for(int i=1; i<=N; i++)
			{
				st = new StringTokenizer(br.readLine());
				C = Integer.parseInt(st.nextToken());//칼로리(1<=오천)
				P = (int)Math.round((Double.parseDouble(st.nextToken()) * 100));//가격(0.01<=100.00)
				
				for(int j=1; j<=M; j++)
					if(j>=P)
						dp[j] = Math.max(dp[j], dp[j-P] + C);
			}
			
			sb.append(dp[M]).append('\n');
		}
		System.out.print(sb);
	}
}
