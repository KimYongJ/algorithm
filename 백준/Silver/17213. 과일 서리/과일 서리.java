//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17213
//1초 / 256MB
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 과일 종류1<=10
		int M		= read();	// 훔칠 수N<=30
		int P		= M - N + 1;// 가지수
		int dp[][]	= new int[P+1][N+1];// P개를, N개에 담는 방법
		
		dp[0][1] = 1;
		
		// p개를 n개에 나눠 담는 것 : p-1개를 n개에 나눠담을 경우의 수 + p개를 n-1개에 나눠 담을 경우의 수
		for(int p=1; p<=P; p++)
			for(int n=1; n<=N; n++)
				dp[p][n] = dp[p-1][n] + dp[p][n-1];

		System.out.print(dp[P][N]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
