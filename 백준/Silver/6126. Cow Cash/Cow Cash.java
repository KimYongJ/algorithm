//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/6126
//1초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());// 1<=25 동전개수
		int N = Integer.parseInt(st.nextToken());// 목표 금액 1<=10,000
		
		long dp[] = new long[N + 1];
		dp[0] = 1;
		
		while(V-->0)
		{
			int coin = Integer.parseInt(br.readLine());
			
			for(int i=coin; i<=N; i++)
				dp[i] += dp[i - coin];
		}
		
		System.out.print(dp[N]);
	}
}
//3 10	// 동전개수(1<=25) , 목표 금액 1<=10,000
//1
//2
//5
//답 : 10