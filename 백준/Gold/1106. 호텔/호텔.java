//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1106
//2초 / 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C		= Integer.parseInt(st.nextToken());// 늘려야할 고객 수1<=천
		int L		= C + 100;
		int N		= Integer.parseInt(st.nextToken());// 홍보 도시개수 1<=20
		int M[]		= new int[N + 1];		// 홍보할 때 드는 비용 1<=100
		int P[]		= new int[N + 1];		// 홍보로 얻을 수 있는 고객 수 1<=100
		int dp[][]	= new int[N+1][L + 1];	// 해당 고객을 만들기 위해 드는 최소 비용
		
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			M[i] = Integer.parseInt(st.nextToken());// 홍보시 드는 비용
			P[i] = Integer.parseInt(st.nextToken());// 얻을 고객 수
		}
		
		int min = 1_000_000_000;
		

		for(int c=1; c<=L; c++)
			dp[0][c] = 1_000_000_000;
				
		for(int i=1; i<=N; i++)
		{
			for(int c=0; c<=L; c++)
			{
				// 이전에 드는 최소 비용 대입
				dp[i][c] = dp[i-1][c];
			
				if(c>=P[i])
					dp[i][c] = Math.min(dp[i][c], dp[i][c-P[i]] + M[i]);

				// 목표 값보다 크다면
				if(c >= C)
					min = Math.min(min, dp[i][c]);
			}
		}
		System.out.print(min);
	}
}
