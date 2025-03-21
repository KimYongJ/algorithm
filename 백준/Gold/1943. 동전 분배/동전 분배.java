//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1943
//2초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 3;
		while(T-->0)
		{
			int N = Integer.parseInt(br.readLine());// 동전의 종류(1<=100)
			int value[] = new int[N];
			int count[] = new int[N];
			int sum		= 0;
			for(int i=0; i<N; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				value[i] = Integer.parseInt(st.nextToken());// 동전금액 , 금액의 총합은 십만원을 넘지 않음
				count[i] = Integer.parseInt(st.nextToken());// 동전개수
				sum += value[i] * count[i];
			}
			if(sum % 2 != 0) // 홀수면 정확히 반 나누는 것 불가능
			{
				sb.append(0).append('\n');
				continue;
			}
			
			sum /= 2;
			
			int dp[][] = new int[N + 1][sum+1]; // 특정 금액을 만들 때 사용한 동전의 최소개수를 담는 dp
			// 모든 갑을 큰수로 세팅
			for(int i=0; i<=N; i++)
				Arrays.fill(dp[i], 1<<30);
			
			dp[0][0] = 0;// 0원을 만드는데 사용된 동전의 개수 0개
			
			for(int i=0; i<N; i++)			// 종류별로 순회
			{
				for(int j=0; j<=sum; j++)	// j금액부터 sum금액까지 순회
				{
					if(dp[i][j] == 1<<30)	// 해당값을 만들지 못하는 경우 스킵, 현재를 통해 미래를 만들어나가는 것이기 때문에
						continue;
					// 만드려는 금액이 sum 이하고, 지금까지 사용한 동전이 쓸 수있는 count[i]개 미만이면
					if(j + value[i] <= sum && dp[i][j] < count[i])
						//i번째 코인을 사용해 j+value[i]금액을 만들 때 동전개수를 세팅
						dp[i][j + value[i]] = Math.min(dp[i][j + value[i]], dp[i][j] + 1);
					// 다음 연산을 위해 자기 바로 다음 인덱스를 0으로 세팅, 이 의미는 다음 동전이 j금액일 때 자기는 동전을 하나도 안써도 그금액을 만들 수 있음을 의미
					dp[i+1][j] = 0;
				}
			}
			
			sb.append(dp[N][sum] != 1<<30 ? 1 : 0).append('\n');
		}
		System.out.print(sb);
	}

}