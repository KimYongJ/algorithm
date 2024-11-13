//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2304
class Main{
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		int N	= read();
		int H[] = new int[1002];
		int L[] = new int[1002];
		int R[] = new int[1002];
		int res	= 0;
		while(N-->0)
			H[read()] = read();			// 높이
		
		for(int i=1; i<=1000; i++)
			L[i] = Math.max(H[i], L[i-1]);
		
		for(int i=1000; i>0; i--)
		{
			R[i]= Math.max(H[i], R[i+1]);
			res	+= Math.min(R[i], L[i]);
		}

		System.out.print(res);
	}
}