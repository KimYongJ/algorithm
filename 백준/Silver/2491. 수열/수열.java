//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2491
//1초 / 128mb

class Main{
	public static void main(String[] args)throws Exception{
		int N	= read();
		int res = 1;
		int inc = 1;
		int dec = 1;
		int prv = read();
		for(int i=1; i<N; i++)
		{
			int now = read();
			if(prv < now)
			{
				inc++;
				dec = 1;
			}
			else if(prv == now)
			{
				++inc;
				++dec;
			}
			else
			{
				++dec;
				inc = 1;
			}
			res = Math.max(res, Math.max(inc, dec));
			prv = now;
		}
		System.out.print(res);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}