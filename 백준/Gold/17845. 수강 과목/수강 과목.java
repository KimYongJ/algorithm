//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/17845
//1초 / 512MB

class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();// 1<=만 최대공부시간
		int K		= read();// 1<=천 과목 수
		int dp[][]	= new int[K+1][N+1];// 과목 : 시간
		int value[] = new int[K+1];
		int time[]	= new int[K+1];
		
		for(int i=1; i<=K; i++)
		{
			value[i]= read();
			time[i]	= read();
		}
		
		//과목 순회
		for(int i=1; i<=K;i++)
		{
			// 시간 순회
			for(int j=1; j<=N; j++)
			{
				dp[i][j] = dp[i-1][j];
				if(0<=j-time[i])
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j - time[i]] + value[i]);
			}
		}
		System.out.print(dp[K][N]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}