//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3483
//1초 128MB
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0)
		{
			int diff	= read();
			int weight	= read() - diff;
			int N		= read();
			int dp[]	= new int[10_001];
			
			Arrays.fill(dp, 1<<30);
			
			dp[0] = 0;
			for(int i=1; i<=N; i++)
			{
				int m = read();// 금액(1<=50,000)
				int w = read();// 해당금액의 무게(1<=10,000)
				
				for(int j=w; j<=10_000; j++)
					dp[j] = Math.min(dp[j], dp[j - w] + m);
			}
			
			if(dp[weight] == 1<<30)
				sb.append("This is impossible.").append('\n');
			else
				sb.append("The minimum amount of money in the piggy-bank is ")
					.append(dp[weight]).append(".\n");
		}
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}