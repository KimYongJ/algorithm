//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/29704
//1초 / 1024MB

class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();// 문제 개수1<=천
		int T		= read();// 남은 제출 기한 1<=천
		int costSum = 0;
		int dp[]	= new int[T+1];

		for(int i=1; i<=N; i++)
		{
			int day = read();//1<=천
			int cost= read();//1<=오천
			
			for(int j=T; j>=day; j--)
				dp[j] = Math.max(dp[j], dp[j-day] + cost);
			
			costSum += cost;
		}
		System.out.print(costSum - dp[T]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}