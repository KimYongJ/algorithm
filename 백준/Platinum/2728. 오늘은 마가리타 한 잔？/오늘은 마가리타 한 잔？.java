//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2728
//1초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void  main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());// 가판대 개수 1<=30
			int M = Integer.parseInt(st.nextToken());// 돈의 양 1<=1,000
			int price[] = new int[N];// 각 가판대에서 판매하는 마가리타의 가격을 저장할 배열
			int dp[][] = new int[N + 1][M + 1];// dp 배열: dp[i][j]는 i개의 가판대를 고려하여 정확히 j원을 소비하는 조합의 수

			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++)
			{
				price[i] = Integer.parseInt(st.nextToken());
			}
			// 마가리타 가격을 오름차순 정렬 (동일 가격 및 처리 순서를 일정하게 하기 위함)
			Arrays.sort(price);
			// 만약 가장 저렴한 마가리타의 가격이 M보다 크면, 어떤 조합도 만들 수 없으므로 결과 0 출력
			if(price[0] > M)
			{
				sb.append(0).append('\n');
				continue;
			}
			
			dp[0][0] = 1;// 아무것도 구매하지 않은 상태: 비용 0원을 만드는 조합은 1가지 (빈 집합)
			
            // dp 점화식을 이용하여 모든 가판대를 고려한 경우의 수를 구함
            // i번째 가판대를 고려하여, j원을 만드는 방법:
			for(int i=0; i<N; i++)
			{
				for(int j=0; j<=M; j++)// 현재까지의 각 돈(j)에 대해, 다음 가판대로 넘어가면서 경우의 수를 전파
				{
					dp[i+1][j] += dp[i][j];// i번째 가판대를 "선택하지 않는 경우": 기존의 경우 그대로 복사
					// i번째 가판대를 "선택하는 경우": j에 해당 가판대 가격을 더한 경우의 수 추가 (단, 총액이 M 이하일 경우)
					if(j + price[i] <= M)
						dp[i + 1][j + price[i]] += dp[i][j];
				}
			}
			
            // 아래 과정은 dp로 구한 전체 조합에서, 문제의 조건인
            // "남은 돈으로 추가 target(마가리타)를 구매할 수 없을 때만 카운팅"을 만족시키기 위한 보정 과정입니다.
            // 먼저, dp[N][*]에는 0원부터 M원까지 모든 경우의 수가 들어있습니다.
            // 여기서, target 마가리타(특정 가격)를 포함하지 않은 경우들과
            // 추가로 구매 가능한 경우(즉, 남은 돈이 target 가격 이상인 경우)들을 따로 처리합니다.
			
			int include[] = new int[M + 1];// target이 포함된 상태의 경우의 수를 저장할 배열
			int exclude[] = new int[M + 1];// 아직 target이 포함되지 않은 상태의 경우의 수를 저장할 배열
			
			int sum = 0;// 전체 경우의 수 합을 의미 / 추후 조건을 만족한 경우의 수만 남김
			
			// 모든 경우의 수를 sum에 누적하고, exclude배열에 복사, exclude는 타겟이 아직 포함되지 않은 기본 상태를 의미
			for(int m=0; m<=M; m++)
			{
				sum += dp[N][m];
				exclude[m] = dp[N][m];	// 초기 dp 배열에서 나온 모든 경우 수 복사
			}
			// 각 가판대를 순차적으로 보면서, 남은 돈으로 target(현재 고려 중인 마가리타)를 추가 구매할 수 있는 경우들을 확인
			for(int i=0; i<N; i++)				// 가격 반복
			{
				for(int m=0; m<=M; m++)			// 0원 부터m원까지 확인, 현재 상태에서 추가 구매가 가능한지 확인
				{
					// 만약 현재 금액 m에 i번째 마가리타의 가격을 더해도 총액이 M 이하면
					if(m + price[i] <= M)		// m원
					{
						// exclude[m]에 있는 조합에 target 마가리타(price[i])를 추가함으로써 해당 조합이 이제 target을 포함하게 된다.
						include[m + price[i]] += exclude[m];
						// 추가된 경우는 미포함 상태에서는 빼준다.
						exclude[m + price[i]] -= exclude[m];
						// 그리고 exclude[m]은 남은 돈으로 추가 구매가 가능한 상태이므로, 최종적으로 전체 합에서 빼준다.
						// 즉, 추가 구매 가능성이 남아있는 경우는 최종 카운팅에서 배제
						sum -= exclude[m];
					}
				}
				// 한 가판대에서 대한 처리가 끝나면, include 배열에 target 마가리타가 포함된 경우의 수가 쌓였으므로, 이 값들을 다음 단계로 넘기기 위해 
				// exclude 배열을 업데이트하고, include 배열은 다시 0으로 초기화(다음 가판대 처리를 위해 준비)
				for(int m=0; m<=M; m++)
				{
					exclude[m] = include[m];
					include[m] = 0;
				}
			}
			// 최종적으로 남은 sum은 남은 돈으로 target 마가리타를 추가 구매할 수 없는, 즉 이미 target이 포함되어 완성된 구매 조합의 수가 된다.
			sb.append(sum).append('\n');
		}
		System.out.print(sb);
	}
}
//2				// 테스트 케이스 개수 1<=1,000
//6 25			// 가판대 개수(1<=30), 돈의 양(1<=1,000)
//8 9 8 7 16 5	// 마가리타 한잔의 가격, 최대 한잔만 구매 가능
//30 250
//1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30
//맛 볼 수 있는 조합의 개수 출력, 입출력이 항상 INT형 내에서 표현될 수 있다.
//15
//16509438