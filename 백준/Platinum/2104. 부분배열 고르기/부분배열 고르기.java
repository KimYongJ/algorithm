//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2104
//2초 / 128MB
class Main{
	
	public static void main(String[] args)throws Exception{
		int N		= read();// 1<=십만
		long[] arr	= new long[N+2];
		long[] psum = new long[N+1];
		long res	= 0;
		
		for(int i=1; i<=N; i++)
		{
			psum[i] = arr[i] = read();//0<=백만
			psum[i] += psum[i-1];
		}
		
		int stack[] = new int[N+3];
		int stIdx	= 1;
		for(int i=1; i<=N+1; i++)
		{
			while(1<=stIdx && arr[stack[stIdx]] > arr[i])
			{
				long H = arr[stack[stIdx--]];				
				long W = psum[i-1] - psum[stack[stIdx]];
				
				res = Math.max(res, H*W);
			}
			stack[++stIdx] = i;
		}
		System.out.print(res);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}