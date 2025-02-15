//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13156
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());// cpu개수 1<=100
		int M = Integer.parseInt(st.nextToken());// 상인수 1<=100
		int cost[][] = new int[M+1][C+1];// 각 상인당 cpu를 팔 때마다 얻는 금액
		
		int prv[] = new int[C + 1];
		for(int m=1; m<=M; m++)
		{
			st = new StringTokenizer(br.readLine());
			for(int c=1; c<=C; c++)
				cost[m][c] = Integer.parseInt(st.nextToken());
		}
		
		for(int m=1; m<=M; m++)
		{
			int dp[] = new int[C + 1];
			for(int i=0; i<=C; i++)
				for(int c=0; c<=i; c++)
					dp[i] = Math.max(dp[i],Math.max(prv[i], prv[i-c] + cost[m][c]));
				
			prv = dp;
		}

		System.out.print(prv[C]);
	}
}