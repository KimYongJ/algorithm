//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2531
class Main{
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		int N		= read();	// 초밥 벨트에 놓인 접시의 수(2<=삼만)
		int D		= read();	// 초밥의 적힌 최대 숫자(2<=삼천)
		int K		= read();	// 연속해서 먹는 접시의 수(1<=C<=D)
		int C		= read();	// 쿠폰번호(1<=C<=D)
		int len		= N + K;
		int B[]		= new int[len];
		int cnt		= 0;
		int res		= 0;
		int visit[]	= new int[D+1];
		
		for(int i=0; i<N; i++)
			B[i] = read();
		
		for(int i=N; i<len; i++)
			B[i] = B[i-N];
		
		for(int i=0; i<K; i++)
			if(visit[B[i]]++ == 0)
				++cnt;

		res = cnt + (visit[C] == 0 ? 1 : 0);
		
		int L = -1;
		while(++L < N)
		{
			if(--visit[B[L]]==0)
				--cnt;

			if(++visit[B[L + K]] == 1)
				++cnt;
			
            res = Math.max(res, cnt + (visit[C] == 0 ? 1 : 0));
		}
		
		System.out.print(res);
	}

}