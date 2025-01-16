//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14246
//2초 / 512MB
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 자연수 개수(1≤100,000)
		long arr[]	= new long[N];
		
		for(int i=0; i<N; i++)
			arr[i] = read();//1<= 100,000
		
		long K = read();	//(1<=십억)
		
		int s	= 0;
		int e	= -1;
		long sum= 0;
		
		while(e+1 < N && sum < K)
			sum += arr[++e];
		
		long cnt = 0;
		
		while(e<N)
		{
			if(K < sum)
			{
				cnt += N - e;
				sum -= arr[s++];
			}
			else if(e + 1 == N)
				break;
			else
				sum += arr[++e];
		}
		System.out.print(cnt);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}