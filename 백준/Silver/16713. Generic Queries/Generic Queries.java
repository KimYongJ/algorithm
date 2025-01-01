//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16713
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 개수(1<=백만)
		int Q		= read();	// 쿼리개수(1<=삼백만)
		int arr[]	= new int[N+1];
		
		for(int i=1; i<=N; i++)
			arr[i] = read() ^ arr[i-1];
		
		int res = 0;
		while(Q-->0)
		{
			int l = read();
			int r = read();
			res ^= (arr[r] ^ arr[l-1]);
		}
		System.out.print(res);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}