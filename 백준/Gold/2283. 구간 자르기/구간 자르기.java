//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2283
class Main{
	public static void main(String[] args)throws Exception{
		final int MAX = 1_000_001;
		int N		= read();
		int K		= read();
		int psum[]	= new int[MAX+1];
		
		while(N-->0)
		
		{
			int s = read();
			int e = read();
			psum[s]++;
			psum[e]--;
		}
		
		for(int i=1; i<MAX; i++)
			psum[i] += psum[i-1];
		
		int sum = psum[0];
		int s	= 0;
		int e	= 0;
		while(e<MAX)
		{
			if(sum == K)
			{
				System.out.print(new StringBuilder().append(s).append(' ').append(e + 1));
				return;
			}
			if(sum < K)
				sum += psum[++e];
			else
				sum -=psum[s++];
		}
		System.out.print("0 0");
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}