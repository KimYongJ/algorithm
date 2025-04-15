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
		int rest[]	= new int[N + 1];					// 멤버들 휴식 시간
		int choice[]= new int[T + 1];
		int dp[]	= new int[T + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			rest[i] = Integer.parseInt(st.nextToken());
			// 하나의 배낭에 휴식 시간을 빈틈 없이 가득 채울 수 있도록 꽉 채운다.
			// 이 때, 선택한 멤버의 번호도 입력해 놓는다.
			for(int j=T; j>=rest[i]; j--)
			{
				if(dp[j] < dp[j - rest[i]] + rest[i])
				{
					dp[j] = dp[j - rest[i]] + rest[i];
					choice[j] = i;
				}
			}
		}
		int result[]= new int[N + 1];
		int time = T;
		// 시간에 따른 선택된 멤버가 choice에 기록되어있으니, 이를 통해 역순으로 순회하며
		// 해당 멤버가 들어간 시간을 result에 입력한다.
		while(time > 0)
		{
			int idx = choice[time];	// 해당 시간에 선택된 
			
			result[idx] = time - rest[idx] + 1;
			time -= rest[idx];
		}
		////// 이 이하부터는, 나머지 선택되지 않은 멤버에 대해 채우는 것
		time = 1;
		// result에 값이 0인 것은 아직 선택되지 않은 멤버이므로 값이 0인 것들에 대해 
		// 순차적으로 순회하며, 그 멤버의 진입 시간을 넣는다. 이 진입시간을 위해 time을 별도로 선언했다. 
		for(int i=1; i<=N; i++)
		{
			if(result[i] == 0)
			{
				result[i] = time;	// 선택되지 않은 멤버의 진입시간 세팅
				time += rest[i];	// 진입시간을 이제 선택된 멤버의 시간과 더해서 다음 진입시간을 만듬
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			// 출력시에, 시간은 0 부터 시작이므로 -1을 하여 보정처리 한다.
			sb.append(result[i] - 1).append(' ');
		
		System.out.print(sb);
	}
}
//8 3		// 총시간(1<=5,000), 멤버수(1<=500)
//4 4 4		// 각 멤버들이 휴식을 취하는 시간, 휴식은 동시에 최대 2명까지만 갈 수 있음
//답 : 0 2 4 // 각멤버들이 공연 시작 후 몇 분이 지나면 휴식을 취하러 가도 되는지 구하라
