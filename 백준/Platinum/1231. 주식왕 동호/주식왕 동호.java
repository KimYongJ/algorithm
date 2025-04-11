//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1231
//2초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N			= Integer.parseInt(st.nextToken());	// 주식수(1<=50)
		int D			= Integer.parseInt(st.nextToken());	// 주어진일자(2<=10)
		int M			= Integer.parseInt(st.nextToken());	// 초기자금(1<=이십만)
		int stock[][]	= new int[N + 1][D + 1];
		
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=D; j++)
			{
				stock[i][j] = Integer.parseInt(st.nextToken());// 해당 주식이 날짜에 따라 변하는 값 1<=1000
			}
		}

        // DP를 위한 배열: dp[k]는 'k'라는 자금으로 거래했을 때, 얻을 수 있는 최대 추가 수익을 저장
        // 문제에서 최종 자금 M이 500,000을 넘지 않는다고 가정하여 크기를 500_001로 잡음.
		int dp[] = new int[500_001];

		for(int j=2; j<=D; j++)			// 일자 반복, 이익이 나는 시점인 2일부터 시작
		{
			Arrays.fill(dp, 0);			// dp값 0으로 초기화
			
			for(int i=1; i<=N; i++)		// 주식 종류 반복
			{
                // 'prev'는 어제(j-1일)의 가격, 'profit'은 오늘의 가격과 어제의 가격 차이
                // profit이 양수면 이익, 음수면 손실을 의미
				int prev	= stock[i][j - 1];
				int profit	= stock[i][j] - prev;
				
                /*
                 * k : 투자 가능한 자금
                 * 반복 범위 : 어제 주식가격 -> 현재 갖고 있는 자금 M까지 
                 * dp[k] = max(dp[k], dp[k - prev] + profit) 의미
                 *
                 * 현재 k원 자금이 있다면, k원 중 prev원을 사용해 주식을 구입(어제 가격에 구매)하고
                 * 매도하여 profit 만큼 이익을 얻을 수 있다.
                 * dp[k - prev]는 prev원을 투자하기 전 남은 자금으로 이미 얻은 최대 이익을 의미하므로,
                 * dp[k - prev] + profit는 현재 prev원을 선택해 얻을 수 있는 이익
                 *
                 * 이 과정을 모든 가능한 자금 에 대해 반복하면,
                 * "현재 가지고 있는 전체 자금 M으로 얻을 수 있는 최대 추가 이익" 즉, dp[M]이 계산됨
                 */ 
				// k가 작은 값부터 M까지 증가하는 이유는, 현재 가능 자금이 10일때, 주식 값이 3원이라면, 3개를 살 수 있는데,
				// 이렇게 여러개 사는 것을 표현하기 위해 작은 값부터 증가하며 최대 이익 값을 구함
				for(int k=prev; k<=M; k++)
					dp[k] = Math.max(dp[k], dp[k - prev] + profit);
				
			}
			
			M += dp[M];
		}
		
		// 최종적으로 가질 수 있는 돈의 최댓값 출력, 출력값은 500,000을 넘지않음
		System.out.print(M);		
	}
}
//2 3 10		// 주식수(1<=50), 주어진일자D(2<=10), 초기자금M(1<=이십만)
//10 15 15	// 해당 주식이 날짜에 따라 변하는 값이 주어짐, 1<=1000
//13 11 20
////24
