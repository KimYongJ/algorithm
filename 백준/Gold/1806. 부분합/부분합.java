//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1806
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 수열길이 (10 ≤ N < 100,000)
		int S		= read();	// 타겟(0 < S ≤ 100,000,000)
		int left	= 0;
		int right	= 1;
		int min		= 1<<30;
		int psum[]	= new int[N+1];
		
		for(int i=1; i<=N; i++)
			psum[i] = psum[i-1] + read();
		
		while(right <= N)
		{
			int sum = psum[right] - psum[left];
			 if(sum < S)
				++right;
			else if(S <= sum)
			{
				min = Math.min(min, right - left);
				++left;
			}
		}
		System.out.print(min == 1<<30 ? 0 : min);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}