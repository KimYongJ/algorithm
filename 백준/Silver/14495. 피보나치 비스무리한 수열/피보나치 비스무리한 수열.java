//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14495
//2초 / 512mb

class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();
		long arr[]	= new long[N+3];
		
		arr[1] = arr[2] = arr[3] = 1;
		
		for(int i=4; i<=N; i++)
			arr[i] = arr[i-1] + arr[i-3];
		
		System.out.print(arr[N]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}