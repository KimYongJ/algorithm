//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/19532
class Main{
	public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int a=read(),b=read(),c=read(),d=read(),e=read(),f=read(),x=-1000,y;

		while(++x < 1000) 
		{
			y = -1000;
			while(++y < 1000)
				if((a*x + b*y == c) &&( d*x + e*y == f))
				{
					sb.append(x).append(' ').append(y);
					System.out.print(sb.toString());
					return;
				}
		}
	}
}