//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1106
//2초 / 128MB
class Main{
	public static void main(String[] args)throws Exception{
		int C		= read();// 늘려야할 고객 수1<=천
		int N		= read();// 홍보 도시개수 1<=20
		int M		= 0;				// 홍보할 때 드는 비용 1<=100
		int P		= 0;				// 홍보로 얻을 수 있는 고객 수 1<=100
		int L		= C + 100;
		int dp[]	= new int[L + 1];	// 해당 고객을 만들기 위해 드는 최소 비용
		int min = 1_000_000_000;

		for(int c=1; c<=L; c++)
			dp[c] = min;
		
		for(int i=1; i<=N; i++)
		{
			M = read();// 홍보시 드는 비용
			P = read();// 얻을 고객 수
			
			for(int c=P; c<=L; c++)
				dp[c] = Math.min(dp[c], dp[c-P] + M);
		}
		
		for(int c=C; c<=L; c++)
			min = Math.min(min, dp[c]);
		
		System.out.print(min);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}