//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/23891
//1초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int INF = -(int)2e9;
		int N	= Integer.parseInt(st.nextToken());	// 타이어개수N(1<=100)
		int M	= Integer.parseInt(st.nextToken());	// 학생수M(1<=100,000)
		int S[] = new int[N + 1];					// 타이어 점수si(1<=100,000)
		int C[] = new int[N + 1];					// 상대팀이 배정한 학생 수pi(1<=100,000)
		
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
		}
		
		int dp[][] = new int[N + 1][M + 1];
		
		for(int i=0; i<=N; i++)
			for(int j=0; j<=M; j++)
				dp[i][j] = INF;
		
		dp[0][0] = 0;
		
		for(int i=1; i<=N; i++)				// 타이어 반복
		{
			for(int j=M; j>=0; j--)			// 학생 수 반복
			{
				// 이기는 경우
				if(j >= C[i] + 1 && dp[i-1][j-(C[i]+1)] != INF)
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-(C[i]+1)] + S[i]);
				// 비기는 경우
				if(j >= C[i] && dp[i-1][j-C[i]] != INF)
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-C[i]]);
				// 지는 경우
				if(dp[i-1][j] != INF)
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j] - S[i]);
			}
		}
		
		int ans = INF;
		
		for(int i=0; i<=M; i++)
			ans = Math.max(ans, dp[N][i]);
		
		if(ans == 0)
			System.out.print('D');
		else
			System.out.print(ans > 0 ? 'W' : 'L');
	}
}
//2 3	// 타이어개수N(1<=100), 학생수M(1<=100,000), 학생 수 배정시 상대와 같으면 서로 점수가 없다
//10 2	// 타이어 점수si, 상대팀이 배정한 학생 수pi(1<=100,000)
//5 2
//이기면 W, 비기는게 최대면 D, 무조건지면 L 출력