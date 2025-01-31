//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25644
//1초 / 512mb

class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();
		int min		= 1<<30;
		int result	= 0;

		for(int i=0; i<N; i++)
		{
			int n	= read();
			result	= Math.max(result, n - min);
			min		= Math.min(min, n);
		}

		System.out.print(result);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
