//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20922
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();			// 수열개수(1<=이십만)
		int K		= read();			// 기준값(1<=백)
		int arr[]	= new int[N];		// 1<=십만
		int dp[]	= new int[100_001];
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		int s = 0;
		int e = 0;
		int M = 1;
		while(e < N)
		{
			int cnt = dp[arr[e]] + 1;
			if(K < cnt)
			{
				// arr[s]를 arr[e]와 같은 값일 때 까지 앞으로 땡긴다.
				while(arr[s] != arr[e])
					dp[arr[s++]]--;
				
				dp[arr[s++]]--;
			}
			else
			{
				M = Math.max(M, e - s + 1);
				dp[arr[e++]] = cnt;
			}
		}
		System.out.print(M);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}