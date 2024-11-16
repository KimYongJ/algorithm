//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2502
class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int D		= read();	// 할머니가 넘어온 날(3<=30)
		int K		= read();	// 이 날 준 떡개수(10<=십만)
		int dp[]	= new int[D];
		dp[0]		= K;
		
		for(int i=1; i<K; i++)
		{
			dp[1] = i;
			
			for(int j=2; j<D; j++)
			{
				int diff = dp[j-2] - dp[j-1];
				if(0 < diff)
					dp[j] = diff;
				else 
					break;
			}
			if(dp[D-1] != 0 && dp[D-1] < dp[D-2])
			{
				System.out.println(new StringBuilder()
						.append(dp[D-1]).append('\n').append(dp[D-2]));
				break;
			}
		}
	}
}