//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17213
//1초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());// 과일 종류1<=10
		int M		= Integer.parseInt(br.readLine());// 훔칠 수N<=30
		int P		= M - N + 1;	// 가지수
		int dp[][]	= new int[P+1][N+1];// P개를, N개에 담는 방법
		
		dp[0][1] = 1;
		
		for(int p=1; p<=P; p++)
			for(int n=1; n<=N; n++)
				dp[p][n] = dp[p-1][n] + dp[p][n-1];

		System.out.print(dp[P][N]);
	}
}