//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/18427
//1초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		final int MOD = 10_007;
		int N = Integer.parseInt(st.nextToken());//학생 수(1≤50)
		int M = Integer.parseInt(st.nextToken());//인당 갖을 수 있는 최대 블록 수 (1<=10)
		int H = Integer.parseInt(st.nextToken());//목표 높이 H(1 ≤1000)
		ArrayList<Integer>[] list = new ArrayList[N + 1];
		for(int i=1; i<=N; i++)
		{
			list[i] = new ArrayList<>();
			st =  new StringTokenizer(br.readLine());
			while(st.hasMoreTokens())
				list[i].add(Integer.parseInt(st.nextToken()));
		}
		
		int dp[][] = new int[N + 1][H + 1];
		
		for(int myH : list[1])
			dp[1][myH]++;
		
		for(int i=2; i<=N; i++)
		{
			for(int myH : list[i])
			{
				dp[i][myH]++;
				for(int h=0; h<=H; h++)
				{
					if(h >= myH && dp[i-1][h-myH] != 0)
					{
						dp[i][h] += dp[i-1][h-myH];
					}
				}
			}
			for(int h=0; h<=H; h++)
				dp[i][h] = (dp[i][h] + dp[i-1][h]) % MOD;
		}
		
		System.out.print(dp[N][H]);
	}
}
