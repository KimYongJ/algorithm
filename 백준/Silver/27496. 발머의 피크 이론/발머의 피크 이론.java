//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27496
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 남은시간 1<=백만
		int L		= read();	// 지속시간 1<=만<=N
		int arr[]	= new int[N+1];
		int psum[]	= new int[N+1];
		int cnt		= 0;
		
		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		for(int i=1; i<=L; i++)
		{
			psum[i] = psum[i-1] + arr[i];
			if(129 <= psum[i] && psum[i] <= 138)
				++cnt;
		}
		
		for(int i=L+1,j=1; i<=N; i++,j++)
		{
			psum[i] = psum[i-1] + arr[i] - arr[j];
			if(129 <= psum[i] && psum[i] <= 138)
				++cnt;
		}
		
		System.out.print(cnt);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}