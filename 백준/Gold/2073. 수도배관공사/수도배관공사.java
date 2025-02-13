//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2073
//2초 / 128MB
class Main{
	public static void main(String[] args)throws Exception{
		int D = read();//목표 거리(7<=십만)
		int P = read();//파이프개수(1<=350)
		int dp[] = new int[D + 1];
		int l[] = new int[P + 1];
		int c[] = new int[P + 1];
		
		for(int i=1; i<=P; i++)
		{
			l[i] = read();
			c[i] = read();
		}
		
		dp[0] = 1<<30;
		
		for(int i=1; i<=P; i++)
			for(int j=D; j>=l[i]; j --)
				dp[j] = Math.max(dp[j], Math.min(dp[j-l[i]], c[i]));
		
		System.out.print(dp[D]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}