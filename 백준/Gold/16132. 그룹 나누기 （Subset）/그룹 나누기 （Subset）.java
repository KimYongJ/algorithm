//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16132
//1초 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 1<=50
		int sum = N *(N + 1) / 2;
		if(sum % 2 != 0)
		{
			System.out.print(0);
			return;
		}
		
		sum /= 2;
		
		long dp[] = new long[sum + 1];// dp[i]를 만들기 위해 가능한 숫자의 개수
		dp[0] = 1; // 0은 1번만들 수 있음
		
		for(int i=1; i<=N; i++) {
			for(int j=sum; j>=i; j--) {
				dp[j] += dp[j-i];
			}
		}
		System.out.print(dp[sum] / 2);
	}
}