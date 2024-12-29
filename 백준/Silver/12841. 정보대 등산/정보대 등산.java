//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12841
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();
		long len[]	= new long[N+1];	// 횡단보도 길이(1<=십만)
		long left[] = new long[N+1];	// 왼쪽 길 거리(1<=십만)
		long right[]= new long[N+1];	// 왼쪽 길 거리(1<=십만)

		for(int i=1; i<=N; i++)
			len[i] = read();

		for(int i=2; i<=N; i++)
			left[i] = read() + left[i-1];
		
		for(int i=2; i<=N; i++)
			right[i] = read() + right[i-1];
		
		long min = 1L<<60;
		int idx = 0;
		
		for(int i=1; i<=N; i++)
		{
			long sum = left[i] + len[i] + right[N] - right[i];
			if(sum < min)
			{
				min = sum;
				idx = i;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(idx).append(' ').append(min);
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}