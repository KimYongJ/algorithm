//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/28082
//1초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N	= Integer.parseInt(st.nextToken());		// 연구실에 있는 배터리 개수 N(1<=500)
		int K	= Integer.parseInt(st.nextToken());		// 장착가능한 최대 배터리 개수K(1<=min(N,100))
		int B[] = new int[N + 1];// 배터리 전력량을 담을 배열
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			B[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(B);
		
		int sum = 0;
		for(int i=N-K+1; i<=N; i++)
			sum += B[i];
		
		int dp[] = new int[sum + 1];
		
		Arrays.fill(dp, 1<<30);
		// [나올 수 있는 전력량 최대] = 사용한 배터리 개수(k보다 작아야 함)
		dp[0] = 0;
		
		for(int i=1; i<=N; i++)
		{
			for(int j=sum; j >= B[i]; j--)
			{
				// 복사한 값이 유효한 값인 경우, 현재를 통해 미래를 계산해 나간다.
				if(dp[j-B[i]] < K )
				{
					dp[j] = Math.min(dp[j-B[i]] + 1, dp[j]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for(int j=1; j<=sum; j++)
		{
			if(dp[j] <= K)
			{
				++cnt;
				sb.append(j).append(' ');
			}
		}
		
		System.out.println(cnt);
		System.out.println(sb);
	}
}
//3 2		// 연구실에 있는 배터리 개수 N(1<=500), 장착 가능한 최대 배터리 개수K(1<=min(N,100))
//1 100 10// 각 배터리의 전력량(1<=500)
////답
//6// N개의 배터리들을 적당히 조합해 장착시 기계 오리가 작동한 전력량의 종류 수 출력
//1 10 11 100 101 110// 두번째 줄에 오리가 작동한 전력량들을 공백을 사이에두고 오름차순으로 출력