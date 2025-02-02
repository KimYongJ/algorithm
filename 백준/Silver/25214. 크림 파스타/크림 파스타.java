//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25214
//1초 / 1024mb

class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append(0).append(' ');
		
		int N		= read();
		int arr[]	= new int[N+2];
		int dp[]	= new int[N+2];
		
		int min = arr[1] = read();
		for(int i=2; i<=N; i++)
		{
			arr[i] = read();
			if(arr[i-1] >= arr[i])
				dp[i] = dp[i-1];
			else
				dp[i] = Math.max(dp[i-1], arr[i] - min);
			
			min = Math.min(arr[i], min);
			
			sb.append(dp[i]).append(' ');
		}
		
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}