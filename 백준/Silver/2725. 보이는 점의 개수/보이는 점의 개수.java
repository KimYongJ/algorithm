//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2725
class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int T	= read();	// 1<=1000
		int o[] = new int[T];
		
		for(int i=0; i<T; i++)
			o[i] = read();	// 1<=1000
		
		int psum[] = new int[1001];
		psum[1] = 3;
		
		for(int i=2; i<=1000; i++)
		{
			int cnt = 0;
			for(int j=1; j<i; j++)
				if(gcd(i,j) == 1)
					++cnt;
			psum[i] += psum[i-1] + (cnt<<1);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<T; i++)
			sb.append(psum[o[i]]).append('\n');
		
		System.out.print(sb);
	}
	public static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a%b);
	}
}
