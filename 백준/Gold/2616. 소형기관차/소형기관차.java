//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2616
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 객차수 3?<=오만
		int psum[]	= new int[N+1];
		
		for(int i=1; i<=N; i++)
			psum[i] += read() + psum[i-1];// 손님의수 0<=백
		
		int max = read();	// 최대로 끌 수 있는 객차 수
		
		int dp[][] = new int[4][N+1];
		
		for(int i=1; i<=3; i++)
			for(int j=i*max; j<=N; j++)
				dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-max] + psum[j] - psum[j-max]);
		
		System.out.print(dp[3][N]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}