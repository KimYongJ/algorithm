//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11170
class Main{
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		final int MAX	= 1_000_001;
		int dp[]		= new int[MAX];
		dp[0]			= 1;
		
		for(int i=1, c = 0; i<MAX; i++, c = 0)
		{
			int num = i;
			while(num != 0)
			{
				if(num % 10 == 0)
					++c;
				num /= 10;
			}
			dp[i] = c + dp[i-1];
		}
		
		
		int T = read();
		while(T-->0)
		{
			int n = read() - 1;
			int m = read();
			int p = 0;
			if(n<0)
			{
				n = 0;
				p = 1;
			}
			sb.append(p + dp[m] - dp[n]).append('\n');
		}
		System.out.print(sb);
	}
}