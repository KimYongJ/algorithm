//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/6066
//1초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 업제개수(1<=100)
		int M = Integer.parseInt(st.nextToken());// 목표 건초무게(1<=50,000)
		int dp[] = new int[M + 1];	// 건초 더미 무게 마다 드는 최소 비용
		
		Arrays.fill(dp, 1<<30);
		
		dp[0] = 0;
		
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());// 건초 무게(1<=5,000)
			int c = Integer.parseInt(st.nextToken());// 비용(1<=5,000)
			
			for(int j=0; j<=M; j++)
			{
				int nextWeight = Math.min(j + w, M);
				
				dp[nextWeight] = Math.min(dp[nextWeight], dp[j] + c);
			}
				
		}
		System.out.print(dp[M]);
	}
}
//2 15	// 업제개수(1<=100), 목표 건초무게(1<=50,000)
//3 2	// 건초 무게(1<=5,000), 비용(1<=5,000)
//5 3
////9 목표 건초무게를 달성할 최소 비용(무게는 초과달성 가능)