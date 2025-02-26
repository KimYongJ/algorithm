//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7579
//1초 128MB
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();//실행앱개수(1<=100)
		int M		= read();// 확보할 메모리 양(1<=천만)
		int byt[]	= new int[N + 1];
		int cost[]	= new int[N + 1];
		int total	= 0;

		for(int i=1; i<=N; i++)
			byt[i] = read();// 사용중인 메모리의 바이트 수(1<=천만)
		for(int i=1; i<=N; i++)
			total += cost[i] = read();// 비활성화시 드는 비용(0<=100)
		
		int dp[] = new int[total + 1];
		
		for(int i=1; i<=N; i++)
			for(int j=total; j>=cost[i]; j--)
				dp[j] = Math.max(dp[j], dp[j-cost[i]] + byt[i]);

		for(int i=0; i<=total; i++)
			if(dp[i] >= M)
			{
				System.out.print(i);
				return;
			}
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
