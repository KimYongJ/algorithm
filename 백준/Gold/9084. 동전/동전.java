//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9084
//1초 / 128mb

class Main{
	public static void main(String[] args)throws Exception{ 
		StringBuilder sb = new StringBuilder();
		int T = read();//1<=10
		while(T-->0)
		{
			int N		= read();// 동전 가지 수 1<=20
			int arr[]	= new int[N+1];
			
			for(int i=1; i<=N; i++)
				arr[i] = read();
			
			int M = read();	// 목표 값 1<=만
			
			int dp[] = new int[M+1];
			
			dp[0] = 1;
			
			// 동전 가지수
			for(int coin : arr)
				// 금액
				for(int money=1; money<=M; money++)
					if(0 <= money-coin)
						dp[money] += dp[money-coin];
			
			sb.append(dp[M]).append('\n');
		}
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}