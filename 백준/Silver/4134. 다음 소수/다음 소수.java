//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/4134
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = (int)read();
		while(T-->0)
		{
			long n = read();
			while(!isPrime(n))
				++n;
			sb.append(n).append('\n');
		}
		System.out.print(sb.toString());
	}
	public static boolean isPrime(long n) {
		if(n < 2 || n == 4) return false;
		if(n < 4 || n == 5) return true;
		if(n % 2 == 0 || n % 3 == 0) return false;
		for(long i=5; i*i<=n; i+= 6)
			if(n % i == 0 || n % (i+2) == 0)
				return false;
		return true;
	}
	static long read() throws Exception {
		long c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}