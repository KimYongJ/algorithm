//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2096
class Main{
	public static void main(String[] args)throws Exception{
		int N = read();
		int a,b,c;
		int a1,b1,c1;
		int a2,b2,c2;
		int a3,b3,c3;
		int x,y,z;
		a = a1 = read();
		b = b1 = read();
		c = c1 = read();
		
		while(N-->1)
		{
			x = read();
			y = read();
			z = read();
			
			a2 = Math.max(a, b) + x;
			b2 = Math.max(a, Math.max(b, c)) + y;
			c2 = Math.max(b, c) + z;
			
			a3 = Math.min(a1, b1) + x;
			b3 = Math.min(a1, Math.min(b1, c1)) + y;
			c3 = Math.min(b1, c1) + z;
			
			a = a2;
			b = b2;
			c = c2;
			a1= a3;
			b1= b3;
			c1= c3;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(Math.max(a, Math.max(b, c))).append(' ')
			.append(Math.min(a1, Math.min(b1, c1)));
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}