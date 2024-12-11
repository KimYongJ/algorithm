//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13220
class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String args[]) throws Exception{
		int N			= read();	// 1<=100000
		int len			= N<<1;
		int text[]		= new int[len];
		int pattern[]	= new int[N];
		int fail[]	 	= new int[N];
		
		for(int i=0,j=N; i<N; i++,j++)
			text[j] = text[i] = read();
		
		for(int i=0; i<N; i++)
			pattern[i] = read();
		
		for(int i=1, j=0; i<N; i++)
		{
			while(0<j && pattern[i] != pattern[j])
				j = fail[j - 1];
			if(pattern[i] == pattern[j])
				fail[i] = ++j;
		}
		for(int i=0, j=0; i<len - 1; i++)
		{
			while(0<j && text[i] != pattern[j])
				j = fail[j - 1];
			
			if(text[i] == pattern[j])
			{
				if(j + 1 == N)
				{
					System.out.print("YES");
					return;
				}else ++j;
			}
		}
		System.out.print("NO");
	}
}