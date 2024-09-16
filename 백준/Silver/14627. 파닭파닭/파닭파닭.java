//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14627
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int S		= read();		// 시장에서 사온 파의 개수(1<=백만)
		int C		= read();		// 파닭의 수
		int arr[]	= new int[S];	// 파의길이 (1<=십억)
		long sum	= 0;
		
		for(int i=0; i<S; i++)
			sum += arr[i] = read();

		int maxlen	= 0;
		int s		= 1;
		int e		= 1_000_000_001;
		
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			int cnt = 0;
			for(int a : arr)
				cnt += a / mid;

			if(cnt < C)
				e = mid - 1;
			else
				s = (maxlen = mid) + 1;
		}

		System.out.print(sum - ((long)maxlen * C) );
	}
}