//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12846
//1초 / 512MB

class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();
		long arr[]	= new long[N+1];
		long max	= 0;
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		int stack[]	 = new int[N+1];
		int stackIdx = -1;
		
		for(int i=0; i<=N; i++)
		{
			while(0<=stackIdx && arr[i] < arr[stack[stackIdx]])
			{
				long H = arr[stack[stackIdx--]];
				long W = i;
				if(0<=stackIdx)
					W = i - stack[stackIdx] - 1;
				max = Math.max(max, H * W);
			}
			stack[++stackIdx] = i;
		}
		
		System.out.print(max);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}