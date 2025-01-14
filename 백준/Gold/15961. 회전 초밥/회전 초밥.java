//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15961
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();			// 접시수(1<=삼백만)
		int D		= read();			// 초밥 가지수(2<=삼천)
		int K		= read();			// 연속해서 먹는 접시 수(2<=삼천)
		int C		= read();			// 보너스 쿠폰 번호(1<=삼천)
		int len		= N + K - 1;
		int arr[]	= new int[len];		// 벨트 위 초밥 위치 표현
		int dp[]	= new int[3_001];	// k번동안 초밥이 몇번 등장했는지 카운팅
		int max		= 0;
		int chobab	= 0;
		
		for(int i=0; i<N; i++)
			arr[i] = read();	// 1<=삼천
		for(int i=N,j=0; i<len; i++,j++)
			arr[i] = arr[j];
		
		for(int i=0; i<K; i++)
			if(dp[arr[i]]++ == 0)
				++chobab;
		
		max = chobab + (dp[C] == 0 ? 1 : 0);
		
		int s = 0;
		int e = K-1;
		
		while(e+1<len)
		{
			if(--dp[arr[s++]] == 0)
				--chobab;
			if(dp[arr[++e]]++ == 0)
				++chobab;
			max = Math.max(max, chobab + (dp[C] == 0 ? 1 : 0));
		}
		
		System.out.print(max);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
