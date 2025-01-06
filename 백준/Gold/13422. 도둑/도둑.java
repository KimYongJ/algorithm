//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13422
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0)
		{
			int N		= read();	// 집개수(1<=십만)
			int M		= read();	// 연속된집개수(1<=N)
			int K		= read();	// 가능한 최대 돈의양(1<=10억)
			int len		= N + M;
			int arr[]	= new int[len];
			int total	= 0;

			for(int i=1; i<=N; i++)
				total += arr[i] = read();
			
			for(int i=N+1; i<len; i++)
				arr[i] = arr[i-N];
			
			for(int i=1; i<len; i++)
				arr[i] += arr[i-1];
			
			if(N == M)
			{
				sb.append(total < K ? 1 : 0).append('\n');
				continue;
			}
			
			int cnt = 0, r = M-1;
			
			while(++r < len)
				if(arr[r] - arr[r-M] < K)
					++cnt;

			sb.append(cnt).append('\n');
		}
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
