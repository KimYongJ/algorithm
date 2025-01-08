//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3673
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();// 테스트 케이스 수 (1<=200)
		while(T-->0)
		{
			int M		= read(); // 나눌 수(1<=백만)
			int N		= read(); // 수열의크기(1<=오만)
			long arr[]	= new long[N+1];
			long mod[]	= new long[M];
			
			for(int i=1; i<=N; i++)
			{
				arr[i] = read() + arr[i-1];
				mod[(int)(arr[i] % M)]++;
			}
			
			long res = mod[0];
			
			for(int i=0; i<M; i++)
				res += mod[i] * (mod[i]-1) / 2;
			
			sb.append(res).append('\n');
			
		}
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}