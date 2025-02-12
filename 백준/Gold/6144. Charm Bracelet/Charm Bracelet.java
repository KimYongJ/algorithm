//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6144
//1초 / 128MB
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();// 팔찌개수1<=3402
		int M		= read();// 착용가능최대무게 1<=12880
		int dp[]	= new int[M + 1];
		
		for(int i=1; i<=N; i++)
		{
			int W = read();// 무게
			int V = read();// 매력값
			
			for(int m=M; m>=W; m--)
				dp[m] = Math.max(dp[m], dp[m-W] + V);
		}
		
		System.out.print(dp[M]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
