//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2003
class Main{
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		int N		= read();	// 1<=만
		int M		= read();	// 1<=억
		int cnt		= 0;
		int arr[]	= new int[N+1];
		arr[1]		= read();
		
		for(int i=2; i<=N; i++)
			arr[i] = arr[i-1] + read();
		
		int L = 0;
		int R = 1;
		
		while(R<= N)
		{
			int sum = arr[R]- arr[L];
			if(sum == M)
				++cnt;
			
			if(sum <= M)
				++R;
			else
				++L;
		}
		System.out.print(cnt);
	}
}