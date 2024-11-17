//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1535
class Main{
	
	public static void main(String[] args)throws Exception{
		int N		= read();
		int[]E		= new int[N+1];		// 잃는 체력
		int[]P		= new int[N+1];		// 얻는 기쁨
		int[][]dp	= new int[101][N+1];// 체력 당 얻는 기쁨을 담을 배열

		for(int i=1; i<=N; i++)
			E[i] = read();

		for(int i=1; i<=N; i++)
			P[i] = read();
		
		for(int e=1; e<=100; e++)
			for(int n=1; n<=N; n++)
				if(E[n] < e)
					dp[e][n] = Math.max(dp[e][n-1], dp[e-E[n]][n-1] + P[n]);
				else
					dp[e][n] = dp[e][n-1];

		System.out.print(dp[100][N]);
	}

	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}