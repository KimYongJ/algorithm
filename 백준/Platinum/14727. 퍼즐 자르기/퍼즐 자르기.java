//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14727
//2초 / 256MB

class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 1<=십만
		long arr[]	= new long[N+1];
		long max	= 0;
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		int stack[] = new int[N];
		int stIdx	= -1;
		for(int i=0; i<=N; i++)
		{
			while(0<=stIdx && arr[i] < arr[stack[stIdx]])
			{
				long H = arr[stack[stIdx--]];
				long W = i;
				if(0<=stIdx)
					W = i - stack[stIdx] - 1;
				max = Math.max(max, H * W);
			}
			stack[++stIdx] = i;
		}
		System.out.print(max);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}