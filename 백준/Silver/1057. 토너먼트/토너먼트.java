//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1057
class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
				  read();
		int A	= read();
		int B	= read();
		int res	= 0;

		while(A != B)
		{
			A = A/2 + A%2;
			B = B/2 + B%2;
			++res;
		}
		System.out.print(res);
	}
}