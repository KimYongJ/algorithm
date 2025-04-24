//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/31265
//1초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());// 훈련상황 가지 수(1<=1,000)
		int M		= Integer.parseInt(st.nextToken());// 훈련 최대시간(1<=10,000)
		int cnt[]	= new int[N + 1];
		int time[][]= new int[N + 1][];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			cnt[i] = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=N; i++)
		{
			time[i] = new int[cnt[i]];
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<cnt[i]; j++)
				time[i][j] = Integer.parseInt(st.nextToken());
		}
		// dp[i][j] = i번째 그룹까지 고려했을 때, 합계가 j가 가능한가?
		boolean[][] dp = new boolean[N + 1][M + 1];
		dp[0][0] = true;
		
		for(int i=1; i<=N; i++)
		{
			for(int j=0; j<cnt[i]; j++)
			{
				int t = time[i][j];
				for(int m = M; m>= t; m--)
				{
					if(dp[i - 1][m - t] || dp[i][m - t])
						dp[i][m] = true;
				}
			}
		}
		
		for(int i=M; i>=0; i--)
		{
			if(dp[N][i])
			{
				System.out.print(i);
				return;
			}
		}
		
		System.out.print(-1);
	}
}
//5 24			// 훈련상황 가지 수(1<=1,000), 훈련 최대시간(1<=10,000)
//5 2 4 1 3		// 각 훈련 상황에 속한 훈련의 개수(1<=1,000)
//23 7 11 3 2	// n줄에걸쳐 각 훈련 상황에 속한 훈련의 소요 시간을 나타내는 정수가 주어짐(1<=1,000)
//8 10
//5 17 20 9
//13
//1 24 3
//훈련 시간의 총합이 최대가 되도록 출력, 불가시 -1출력
// 답 : -1

