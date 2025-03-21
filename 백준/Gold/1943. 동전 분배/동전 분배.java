//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1943
//2초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 3;
		while(T-->0)
		{
			int N = Integer.parseInt(br.readLine());// 동전의 종류(1<=100)
			int value[] = new int[N + 1];
			int count[] = new int[N + 1];
			int sum		= 0;
			for(int i=1; i<=N; i++)
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
			
			boolean dp[] = new boolean[sum + 1];// 동전을 순차적으로 사용해서 dp[i]원을 만들 수 있는지를 담는다.
			dp[0] = true;						// 0원은 무조건 만들 수 있음
			for(int i=1; i<=N; i++)				// 동전종류를 순차적으로 반복
			{
				for(int j=sum; j>=value[i]; j--)// 현재가 미래에 영향을 안줘야 하기 때문에 뒤에서부터 탐색
					if(dp[j - value[i]])		// 가지 치기를 위해 동전을 1개이상 사용해서 만들 수 있을 때만 이하 반복문 실행
					{
						int val = j;
						int c = 0;
						while(++c <= count[i] && val <= sum)
						{
							dp[val] = true;
							val += value[i];
						}
//						// 동전개수만큼 반복
//						for(int c=1; c<=count[i]; c++)
//							// j-value[i] 원이 만들 수 있기 때문에, 이제 코인을 사용해 그이상 만들 수 있는지 계속 탐색
//							if(j - value[i] + c * value[i] <= sum)
//								dp[j - value[i] + c * value[i]] = true;
					}
			}
			sb.append(dp[sum] ? 1 : 0)
				.append('\n');
		}
		System.out.print(sb);
	}

}