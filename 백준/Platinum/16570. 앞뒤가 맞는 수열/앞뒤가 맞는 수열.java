//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16570
class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();	// 2<=백만
		int max		= 0;
		int cnt		= 0;
		int arr[]	= new int[N];
		int fail[]	= new int[N];

		for(int i=N-1; i>=0; i--)
			arr[i] = read();	// int범위 안 숫자
		
		for(int i=1,j=0; i<N; i++)
		{
			while(0<j && arr[i] != arr[j])
				j = fail[j - 1];
			
			if(arr[i] == arr[j])
				max = Math.max(max, fail[i] = ++j);
		}
		if(max == 0)
		{
			System.out.print(-1);
			return;
		}

		for(int i=0; i<N; i++)
			if(fail[i] == max)
				++cnt;
		
		System.out.printf("%d %d", max, cnt);
	}
}