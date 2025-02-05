//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17208
//3초 / 512mb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{ 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 주문 수1<=100
		int M		= Integer.parseInt(st.nextToken());	// 남은 치즈버거  1<=300
		int K		= Integer.parseInt(st.nextToken());	// 남은 감자튀김  1<=300
		int cheese[]= new int[N+1];
		int fries[]	= new int[N+1];
		int dp[][][]= new int[N+1][M+1][K+1];
		
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			cheese[i]	= Integer.parseInt(st.nextToken());//1<=300
			fries[i]	= Integer.parseInt(st.nextToken());//1<=300
		}
		
		for(int i=1; i<=N; i++)
		{
			int chs = cheese[i];
			int frs = fries[i];
			for(int c=1; c<=M; c++)
			{
				for(int f=1; f<=K; f++)
				{
					dp[i][c][f] = dp[i-1][c][f];
					if(0<=c-chs && 0<=f-frs)
						dp[i][c][f] = Math.max(dp[i][c][f], dp[i-1][c-chs][f-frs] + 1);
				}
			}
		}
		
		System.out.print(dp[N][M][K]);
	}
}