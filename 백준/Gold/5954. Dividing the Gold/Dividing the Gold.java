//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5954
//1초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 금화더미(1<=250)
		int arr[]	= new int[N + 1];
		int sum		= 0;
		int dummy	= 0;
		
		for(int i=1; i<=N; i++)
			sum += arr[i] = Integer.parseInt(br.readLine());// 1<=이천
		
		dummy = sum / 2;// 한쪽에서 나올 수 있는 가장 큰 더미의 가치
		
		boolean dp[] = new boolean[dummy + 1];
		int DP[] = new int[dummy + 1];			// 해당 max까지의 경우의 수를 구할 배열
		
		DP[0] = 1;
		dp[0] = true;
		
		for(int i=1; i<=N; i++)					// 최대 250번
			for(int j=dummy; j>=arr[i]; j--)	// 최대 2000번
			{
				dp[j] |= dp[j-arr[i]];			// 해당 가치가 나올 수 있는지 체크
				DP[j] = (DP[j] + DP[j - arr[i]]) % 1_000_000;
			}

		StringBuilder sb = new StringBuilder();
		
		for(int i=dummy; i>=0; i--)
			if(dp[i])							// 나올 수 있는 가치의 max 확인
			{
				sb.append(Math.abs(sum - i*2))
				.append('\n')
				.append(DP[i]);
				break;
			}
		
		System.out.print(sb);
	}
}

