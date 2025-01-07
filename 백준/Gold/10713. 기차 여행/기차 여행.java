//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10713
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	//철도수(2<=십만)
		int M		= read();	//여행일(2<=십만)
		int cnt[]	= new int[N+1];
		int start = read();
		while(M-->1)
		{
			int next = read();
			if(start < next)
			{
				cnt[start]++;
				cnt[next]--;
			}
			else
			{
				cnt[start]--;
				cnt[next]++;
			}
			start = next;
		}
		
		for(int i=1; i<=N; i++)
			cnt[i] += cnt[i-1];
		
		long res = 0;
		for(int i=1; i<N; i++)
		{
			long t = read();	// 그냥 타는 티켓비
			long p = read();	// 카드사용시 통과하는 가격
			long g = read();	// IC카드 구매비
			
			res += Math.min(t*cnt[i], g + p*cnt[i]);
		}
		
		System.out.print(res);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
