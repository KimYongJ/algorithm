//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2702
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder	sb = new StringBuilder();
		int T = read();
		while(T-->0)
		{
			int a	= read();
			int b	= read();
			int GCD = GCD(a, b);
			int LCM	= a*b/GCD;
			
			sb.append(LCM).append(' ').append(GCD).append('\n');
		}
		System.out.print(sb.toString());
	}
	
	public static int GCD(int a, int b) {
		return b == 0 ? a : GCD(b, a%b);
	}
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}