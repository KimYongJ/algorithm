//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/19621
//1초 / 256MB

class Main{
	
	public static void main(String[]args)throws Exception{
		int N		= read();	//회의 수(1<=25)
		int[]dp		= new int[N+1];
		int[]arr	= new int[N+1];

		for(int i=1; i<=N; i++)
		{
			read();	// 0<=int최대값, 회의시간은 모두 다름
			read();	// 0<=int최대값, 회의시간은 모두 다름
			arr[i] = read();	// 회의 인원(1<=천)
		}
		
		dp[1] = arr[1];

		for(int i=2; i<=N; i++)
			dp[i] = Math.max(dp[i-1], dp[i-2] + arr[i]);

		System.out.print(dp[N]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
