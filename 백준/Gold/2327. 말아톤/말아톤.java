//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2327
//2초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken()); // 키 총합 7<=십만
		int N = Integer.parseInt(st.nextToken()); // 학생수 1<=350
		int height[] = new int[N + 1];
		int speed[] = new int[N + 1];
		int dp[] = new int[H + 1];

		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			height[i]	= Integer.parseInt(st.nextToken());// 학생키(1<=십만)
			speed[i]	= Integer.parseInt(st.nextToken());// 달리기 속도(1<=십만)
		}
		// 높이가 무조건 H가 되어야 하며, H에 이르기 까지, 최소가 최대로 되어야 한다.
		// dp[h] = 최대 도착시간이 되어야 함,
		// dp[h] = max( min(speed[i], dp[h-height[i]) , dp[h])
		dp[0] = 1000001;
		for(int i=1; i<=N; i++)
		{
			for(int j=H; j>=height[i]; j--)
			{
				dp[j] = Math.max(dp[j], Math.min(speed[i], dp[j-height[i]]));
			}
		}
		
		System.out.print(dp[H]);
	}
}

