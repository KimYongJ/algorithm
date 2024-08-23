// https://github.com/kimyongj/algorithm
import java.util.Arrays;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int M		= read();
		int W		= read();
		int man[]	= new int[M];
		int woman[] = new int[W];
		int dp[][]	= new int[M+1][W+1];
		for(int i=0; i<M; i++) 
			man[i] = read();
		for(int i=0; i<W; i++) 
			woman[i] = read();
		
		Arrays.sort(man);
		Arrays.sort(woman);
		
		for(int i=1; i<=M; i++) 
		{
			for(int j=1; j<=W; j++) 
			{				
				dp[i][j] = dp[i-1][j-1] + Math.abs(man[i-1] - woman[j-1]);
				if(i > j) 
				{
					dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);
				}
				else if(i < j)
				{
					dp[i][j] = Math.min(dp[i][j], dp[i][j-1]);
				}
			}
		}
		
		System.out.print(dp[M][W]);
	}
}