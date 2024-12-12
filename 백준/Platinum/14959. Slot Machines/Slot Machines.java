//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14959
class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int K		= 0;
		int P		= 0;
		int N		= read();// 1<=백만
		int arr1[]	= new int[N];
		int fail[]	= new int[N];
		int total	= 1<<30;
		
		for(int i=N-1; i>=0; i--)
			arr1[i] = read();

		for(int i=1,j=0; i<N; i++)
		{
			while(0<j && arr1[i] != arr1[j])
				j = fail[j - 1];
			
			if(arr1[i] == arr1[j])
				fail[i] = ++j;
		}
		for(int i=0; i<N; i++)
		{
			int kk = N - (i+1);
			int pp = (i+1) - fail[i];
			if(kk+pp < total)
			{
				total = kk+pp;
				K = kk;
				P = pp;
			}
		}
		System.out.printf("%d %d",K,P);
	}
}