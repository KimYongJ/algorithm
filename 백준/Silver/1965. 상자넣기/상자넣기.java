//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1965
//2초 / 128MB
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();// 1<=천
		int arr[]	= new int[N + 1];
		int dp[]	= new int[N + 1];
		int result	= 0;

		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		for(int i=1; i<=N; i++)
		{
			dp[i] = 1;
			for(int j=0; j<i; j++)
			{
				if(arr[i] > arr[j])
					dp[i] = Math.max(dp[i], dp[j] + 1); 
			}
			result = Math.max(result, dp[i]);
		}
		System.out.print(result);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}