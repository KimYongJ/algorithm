//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13900
class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();
		long arr[]	= new long[N+1];
		long prev[]	= new long[N+1];
		long res	= 0;
		
		for(int i=1; i<=N; i++)
			prev[i] = (arr[i] = read()) + prev[i-1];
		
		for(int i=1; i<N; i++)
			res += arr[i] * (prev[N] - prev[i]);

		System.out.print(res);
	}
}