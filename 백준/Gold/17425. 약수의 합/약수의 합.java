//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17425
class Main{
	public static void main(String[] args)throws Exception{
		final int MAX = 1_000_000;
		long a[] = new long[MAX+1];
		
		for(int n=1; n<=MAX; n++)
		{
			for(int i=1; i*n<=MAX; i++)
				a[n*i] += n;
			
			a[n] += a[n-1];	// 누적합을 바로 함
		}
		
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0)
			sb.append(a[read()]).append('\n');

		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
