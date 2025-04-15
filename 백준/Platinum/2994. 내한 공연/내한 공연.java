//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2994
//1초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T		= Integer.parseInt(st.nextToken());	// 총시간(1<=5,000)
		int N		= Integer.parseInt(st.nextToken());	// 멤버수(1<=500)
		int t[]		= new int[N + 1];					// 멤버들 휴식 시간
		int choice[]= new int[T + 1];
		int dp[]	= new int[T + 1];
		int result[]= new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			t[i] = Integer.parseInt(st.nextToken());
			for(int j=T; j>=t[i]; j--)
			{
				if(dp[j] < dp[j - t[i]] + t[i])
				{
					dp[j] = dp[j - t[i]] + t[i];
					choice[j] = i;
				}
			}
		}
		
		int time = T;
		while(time > 0)
		{
			int idx = choice[time];
			result[idx] = time - t[idx] + 1;
			time -= t[idx];
		}
		
		time = 1;
		
		for(int i=1; i<=N; i++)
		{
			if(result[i] == 0)
			{
				result[i] = time;
				time += t[i];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(result[i] - 1).append(' ');
		
		System.out.print(sb);
	}
}
//8 3		// 총시간(1<=5,000), 멤버수(1<=500)
//4 4 4		// 각 멤버들이 휴식을 취하는 시간, 휴식은 동시에 최대 2명까지만 갈 수 있음
//답 : 0 2 4 // 각멤버들이 공연 시작 후 몇 분이 지나면 휴식을 취하러 가도 되는지 구하라
