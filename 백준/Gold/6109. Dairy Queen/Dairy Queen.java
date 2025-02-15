//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6109
//1초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N	= Integer.parseInt(st.nextToken());// 만들어야 하는 돈 1<=300
		int C	= Integer.parseInt(st.nextToken());// 동전 개수 1<=200
		int dp[]= new int[N + 1];
		
		dp[0]	= 1;

		while(C-->0)
		{
			int coin = Integer.parseInt(br.readLine());
			for(int i=coin; i<=N; i++)
				dp[i] += dp[i-coin];
		}
		System.out.print(dp[N]);
	}
}