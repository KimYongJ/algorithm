//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/24499
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 배열 개수
		int K		= read();	// 연속된 수
		int len		= N + K;
		int arr[]	= new int[(N+1)<<2];
		
		for(int i=1,j=N+1; i<=N; i++,j++)
			arr[i] = arr[j] = read();
		
		for(int i=1; i<len; i++)
			arr[i] += arr[i-1];
		
		int max = arr[K];
		
		for(int i=K+1,j=1; i<len; i++,j++)
			max = Math.max(max, arr[i] - arr[j]);
		
		System.out.print(max);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}

}