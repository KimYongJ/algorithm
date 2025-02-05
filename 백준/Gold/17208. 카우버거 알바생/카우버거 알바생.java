//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17208
//3초 / 512mb

class Main{
	public static void main(String[] args)throws Exception{ 
		int N		= read();	// 주문 수1<=100
		int M		= read();	// 남은 치즈버거  1<=300
		int K		= read();	// 남은 감자튀김  1<=300
		int dp[][][]= new int[N+1][M+1][K+1];
		
		for(int i=1; i<=N; i++)
		{
			int chs = read();//1<=300
			int frs = read();//1<=300
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
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}