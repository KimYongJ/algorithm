//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17484
//1초 / 256mb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y		= Integer.parseInt(st.nextToken());
		int X		= Integer.parseInt(st.nextToken());
		int dp[][][]= new int[3][Y+1][X+1];

		for(int y=1; y<=Y; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++)
			{
				dp[0][y][x] = dp[1][y][x] = dp[2][y][x] = Integer.parseInt(st.nextToken());
				if(x == 1)
					dp[0][y][x] = 1000;
				if(x == X)
					dp[2][y][x] = 1000;
			}
		}
		
		// dp[0] = 왼쪽위, dp[1] = 위, dp[2] = 오른쪽위
		for(int y=2; y<=Y; y++)
			for(int x=1; x<=X; x++)
			{
				dp[0][y][x] += x != 1 ? Math.min(dp[1][y-1][x-1], dp[2][y-1][x-1]) : 0;
				dp[1][y][x] += Math.min(dp[0][y-1][x], dp[2][y-1][x]);
				dp[2][y][x] += x+1 <= X ? Math.min(dp[0][y-1][x+1], dp[1][y-1][x+1]) : 0;
			}
		
		int min = 1<<30;
		
		for(int x=1; x<=X; x++)
			for(int i=0; i<3; i++)
				min = Math.min(dp[i][Y][x], min);
		
		System.out.print(min);
	}
}