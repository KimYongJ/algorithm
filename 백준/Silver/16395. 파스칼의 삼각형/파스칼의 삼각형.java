//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16395
//1초 / 256mb
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 1<=30
		int K		= read();	// 1<=30
		int arr[][] = new int[N+1][N+1];
		
		arr[1][1] = 1;
		
		for(int i=2; i<=N; i++)
			for(int j=1; j<=i; j++)
				arr[i][j] = arr[i-1][j] + arr[i-1][j-1];
		
		System.out.print(arr[N][K]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}