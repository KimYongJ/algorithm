//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5540
//1MB 128초
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 야시장개수N(1≤삼천)
		int T = Integer.parseInt(st.nextToken());// T(여름축제끝나는 시간 1≤삼천)
		int S = Integer.parseInt(st.nextToken());// S(가장큰 불꽃이 터지는 시간 0<=T
		int V[] = new int[N + 1]; // 즐거움 0<=십만
		int C[] = new int[N + 1]; // 걸리는 시간 1<=삼천
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			V[i] = Integer.parseInt(st.nextToken());// 즐거움
			C[i] = Integer.parseInt(st.nextToken());// 걸리는시간
		}
		int dp[][] = new int[2][T + 1];
		
		int idx = 0;
		
		for(int i=0; i<N; i++)
		{
			for(int t=0; t<=T; t++)
			{
				if(t - C[i] < 0)
					dp[idx][t] = dp[1 - idx][t];
				else if(t > S && t - C[i] < S)
					dp[idx][t] = Math.max(dp[idx][t - 1], dp[1 - idx][t]);
				else
					dp[idx][t] = Math.max(dp[idx][t - 1], Math.max(dp[1 - idx][t], dp[1 - idx][t - C[i]] + V[i]));
			}
			idx = 1 - idx;
		}

		System.out.print(dp[1 - idx][T]);
	}
}
