//https://www.acmicpc.net/problem/14536
//2초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		while(T-->0)
		{
			int N = Integer.parseInt(br.readLine().trim());
			int tsum = 0;
			int wsum = 0;
			int tw[][] = new int[N + 1][3];
			
			for(int i=1; i<=N; i++)
			{
				st = new StringTokenizer(br.readLine().trim());
				tsum += tw[i][0] = Integer.parseInt(st.nextToken());
				wsum += tw[i][1] = Integer.parseInt(st.nextToken());
				tw[i][2] = tw[i][0] + tw[i][1];
			}
			
			int dp[] = new int[tsum + 1];
			
			for(int i=1; i<=N; i++)
				for(int j = tsum; j>=tw[i][0]; j--)
					dp[j] = Math.max(dp[j],dp[j - tw[i][0]] + tw[i][2]);

			
			for(int i=0; i<=tsum; i++)
			{
				if(dp[i] >= wsum)
				{
					sb.append(i).append('\n');
					break;
				}
			}
			
		}
		System.out.print(sb);
	}
}