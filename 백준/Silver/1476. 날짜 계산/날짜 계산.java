//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1476
class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int x1		= read();
		int y1		= read();
		int z1		= read();
		int x		= 1;
		int y		= 1;
		int z		= 1;
		int year	= 1;
		
		while(x != x1 || y != y1 || z != z1)
		{
			++x;
			++y;
			++z;
			++year;
			if(x == 16) x = 1;
			if(y == 29) y = 1;
			if(z == 20) z = 1;
		}
		System.out.println(year);
	}
}