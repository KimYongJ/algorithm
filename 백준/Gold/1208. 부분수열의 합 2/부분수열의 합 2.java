//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1208
class Main{
	
	static final int MAX	= 4_000_001;
	static final int PLUS	= 2_000_000;
	static int N, S, arr[], count[];
	static long cnt;
	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
    
	public static void getSum(int idx, int sum) {
		if(idx == N>>1)
		{
			count[sum + PLUS]++;// 배열을 반으로나눴을 때 최소로 나올 수 있는 값이 -이백만이기에 +이백만을해줌
			return;
		}
		getSum(idx + 1, sum);
		getSum(idx + 1, sum + arr[idx]);
	}
	public static void getResult(int idx, int sum) {
		if(idx == N)
		{
			int target = S - sum + PLUS;// 구하고자 하는 값 도출
			if(0<=target && target <= MAX)
				cnt += count[target];
			return;
		}
		getResult(idx + 1, sum);
		getResult(idx + 1, sum + arr[idx]);
	}
	public static void main(String[] args)throws Exception{
		N		= read();		// 1<=40
		S		= read();		// -백만 <= +백만
		arr		= new int[N];	// -십만 <= +십만
		count	= new int[MAX];	// 2개로나눴을 때 나올 수 있는 가장 큰 합계는 |2,000,000|이기에 음수를 없애야 해서 사백만이 MAX임

		for(int i=0; i<N; i++)
			arr[i] = read();
		
		getSum(0, 0);			// 배열을 절반으로 나눠 절반에 대한 모든 부분수열의 합을 count에 넣는다.
		getResult(N>>1, 0);		// 남은 배열 반의 모든 부분수열의 합을 구하고, 최종적으로 count에서 타겟한 값이 있는지 확인한다.
		
		if(S == 0)				// getSum과 결과를 구할 때 0이 한번 들어가기 때문에 한번들어간 0을 연산결과에서 빼준다.
			--cnt;
		System.out.print(cnt);
	}
}
