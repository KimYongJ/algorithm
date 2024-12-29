//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27968
class Main{
	
	static int N, M;
	static long psum[];
	
	public static void main(String[] args)throws Exception{
		N		= (int)read();	// 아이 수(1<=삼십만)
		M		= (int)read();	// 최대 횟수(1<=삼십만)
		psum	= new long[M + 1];
		
		for(int i=1; i<=M; i++)
			psum[i] = psum[i-1] + read();
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++)
		{
			int res = binarySearch(read());
			sb.append(res == -1 ? "Go away!" : res)
				.append('\n');
		}
		System.out.print(sb);
	}
	public static int binarySearch(long target) {
		int s = 1;
		int e = M;
		int r = -1;
		while(s <= e)
		{
			int idx = (s + e) >> 1;
			if(target <= psum[idx])
			{
				e = idx - 1;
				r = idx;
			}
			else s = idx + 1;
		}
		return r;
	}
	static long read() throws Exception {
		long c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}