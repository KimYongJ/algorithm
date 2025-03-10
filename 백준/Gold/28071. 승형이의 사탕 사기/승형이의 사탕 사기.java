//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/28071
//2초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N	= Integer.parseInt(st.nextToken());// N(사탕종류1<=300)
		int M	= Integer.parseInt(st.nextToken());// M(가져갈사탕상자개수1<=300)
		int K	= Integer.parseInt(st.nextToken());// K(동생 수1<=300)
		int candy[]	= new int[N + 1];
		int max	= 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			max = Math.max(max,candy[i] = Integer.parseInt(st.nextToken()));
		
		int candyMax = max * M; // 나올 수 있는 가장큰 사탕개수 = 특정 상자안에 있는 가장 큰 사탕개수 * 가져갈 사탕상자 개수
		
		int dp[] = new int[candyMax + 1]; // dp[x]안에는 x개의 사탕을 만들기 위한 최소 상자 개수가 들어감
		
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		dp[0] = 0;
		// 상자를 하나씩 순회
		for(int i=1; i<=N; i++)
			// 가능한 사탱개수 부터 최대 사탕개수까지 순회
			for(int j=candy[i]; j<=candyMax; j++)
				// (현재 가능한 사탕 - 탐색중인 사탕개수)가 도달 가능하면, 최솟값 갱신
				if(dp[j - candy[i]] != Integer.MAX_VALUE)
					dp[j] = Math.min(dp[j], dp[j - candy[i]] + 1);

		int result = 0;
		
		for(int i=candyMax; i>=0; i--)
		{
			if(i % K == 0 && dp[i] <= M)
			{
				result = i;
				break;
			}
		}
		System.out.print(result);
	}
}
