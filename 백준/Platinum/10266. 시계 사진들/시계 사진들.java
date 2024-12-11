//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10266
class Main{
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		final int MAX	= 360_000;
		final int LEN	= MAX<<1;
		int fail[]		= new int[MAX];
		boolean patn[]	= new boolean[MAX];
		boolean text[]	= new boolean[LEN];
		int N			= read();// 바늘수(2 ≤ 200 000)
		
		// (0 ≤ n < 360,000)
		for(int i=0; i<N; i++)text[read()] = true;
		for(int i=0; i<N; i++)patn[read()] = true;
		
		for(int i=0, j=MAX; i<MAX;)
			text[j++] = text[i++];
		
		for(int i=1,j=0; i<MAX; i++)
		{
			while(0<j && patn[i] != patn[j])
				j = fail[j - 1];
			
			if(patn[i] == patn[j])
				fail[i] = ++j;
		}
		
		for(int i=0, j=0; i<LEN; i++)
		{
			while(0<j && text[i] != patn[j])
				j = fail[j - 1];
			
			if(text[i] == patn[j])
			{
				if(++j== MAX)
				{
					System.out.print("possible");
					return;
				}
			}
		}
		System.out.print("impossible");
	}
}
