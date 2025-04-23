//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/10023
//1초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int INF = 1<<30;
		int N = Integer.parseInt(st.nextToken());// 들판 수(1<=100)
		int B = Integer.parseInt(st.nextToken());// 품종 수(1<=20)
		
		
		int sound[] = new int[B + 1];	// 품종에 따른 소의 음량
		for(int i=1; i<=B; i++)
			sound[i] = Integer.parseInt(br.readLine());	// 1<=100
		
		
		int total[] = new int[N + 1];	// 각 들판마다 얻은 음량
		for(int i=1; i<=N; i++)
			total[i] = Integer.parseInt(br.readLine());	// 0<=100,000
		
		
		int maxNeed = 0;
		int need[] = new int[N + 1];
		for(int i=1; i<=N; i++)
		{
			if(total[i] + 1 == total[i-1])	// 해당 들판에 소가 없을 경우
				continue;
			// 추가된 음량
			int nowValue = total[i];
			// 이전 들판 값이 0이 아닌 경우 해당 값의 -1 만큼 빼준다.
			if(total[i-1] != 0)
				 nowValue -= (total[i-1] - 1);

			need[i] = nowValue;	// 결과적으로 구해야 하는 값을 저장해 나감
			// 구해야하는 가장 큰 값 저장
			maxNeed = Math.max(maxNeed, nowValue);
		}
		
		int dp[] = new int[maxNeed + 1];
		
		Arrays.fill(dp, INF);
		
		dp[0] = 0;
		
		for(int s : sound)
			for(int i=s; i<=maxNeed; i++)
				dp[i] = Math.min(dp[i], dp[i - s] + 1);
		
		int ans = 0;
		
		for(int i=1; i<=N; i++)
		{
			if(dp[need[i]] == INF)
			{
				System.out.print(-1);
				return;
			}
			ans += dp[need[i]];
		}
		
		System.out.print(ans);
	}
}
//5 2	// 들판 수(1<=100), 품종 수(1<=20)
//5	// 품종에 따른 음량 수
//7	// 품종에 따른 음량 수
//0	// 이하 들판에서의 녹음된 소리 
//17
//16
//20
//19
//답 : 4