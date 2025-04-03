//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9327
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());//테스트 메이스 수(1<=100)
		
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N	= Integer.parseInt(st.nextToken());// 세트의 수N(1<=100)
			int E	= Integer.parseInt(st.nextToken());// 추가로 확보 해야하는 목표 용량 E(0<=10^9)이 주어짐
			int A[]	= new int[N + 1];
			int max = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++)
				max += A[i] = Integer.parseInt(st.nextToken());// 각 세트의 크기 1<=2000
			
			if(max * 2 < E)
			{
				sb.append("FULL").append('\n');
				continue;
			}
			
			int dp[] = new int[max + 1];
			
			for(int i=1; i<=N; i++)
				for(int j=max; j>=A[i]; j--)
					dp[j] = Math.max(dp[j],dp[j-A[i]] + A[i]);

			for(int i=0; i<=max; i++)
			{
				if(dp[i] * 2 >= E)
				{
					sb.append(i).append('\n');
					break;
				}
			}
		}
		System.out.print(sb);
	}
}
//3			// 테스트 케이스 수(1<=100)
//2 500		// 테스트 케이스 첫줄에는 RAID-1세트의 수N(1<=100)와 확보해야하는 목표 용량 E(0<=10^9)이 주어짐
//500 500		// 각 세트의 크기가 N개 주어짐(1<=2000)
//4 2400
//400 600 700 1000
//2 1000
//10 10
////답 : 500 , 1300 , FULL