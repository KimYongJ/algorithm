//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/23827
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();
		long arr[]	= new long[N+1];
		long psum[] = new long[N+1];
		long res	= 0;
		
		for(int i=1; i<=N; i++)
			psum[i] += psum[i-1] + (arr[i] = read());
		
		for(int i=1; i<N; i++)
		{
			res += (psum[N] - psum[i]) * arr[i];
			res = res % 1_000_000_007;
		}
		System.out.print(res);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}