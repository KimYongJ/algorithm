//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10986
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	//원소개수(1<=10의6승)
		int M		= read();	//나눌값(2<=천)
		long C[]	= new long[M];
		long sum	= 0;
		
		for(int i=1; i<=N; i++)
		{
			sum += read();
			++C[(int)(sum % M)];
		}
		
		long res = C[0];
		for(int i=0; i<M; i++)
			if(1 < C[i])
				res += C[i]*(C[i]-1) / 2 ;
		
		System.out.print(res);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}