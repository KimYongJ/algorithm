//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11969
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 소의 숫자(1<=십만)
		int Q		= read();	// 질의 개수(1<=십만)
		int arr[][]	= new int[4][N+1];
		
		for(int i=1; i<=N; i++)
		{
			arr[read()][i] = 1;
			arr[1][i] += arr[1][i-1];
			arr[2][i] += arr[2][i-1];
			arr[3][i] += arr[3][i-1];
		}
		
		StringBuilder sb = new StringBuilder();
		while(Q-->0)
		{
			int l = read();
			int r = read();
			sb.append(arr[1][r] - arr[1][l-1]).append(' ')
			.append(arr[2][r] - arr[2][l-1]).append(' ')
			.append(arr[3][r] - arr[3][l-1]).append('\n');
		}
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}