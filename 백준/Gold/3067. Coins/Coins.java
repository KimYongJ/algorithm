//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3067
//1초 / 128mb

class Main{
	public static void main(String[] args)throws Exception{ 
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0)
		{
			int N	= read();
			int C[]	= new int[N+1];
			
			for(int i=1; i<=N; i++)
				C[i] = read();
			
			int M	= read();
			int dp[]= new int[M+1];
			
			dp[0] = 1;
			
			for(int c : C)
				for(int i=1; i<=M; i++)
					if(0<=i-c)
						dp[i] += dp[i-c];
			
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