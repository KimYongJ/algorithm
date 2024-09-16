//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16564
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static boolean check(int[] arr, long mid, int K) {
		for(int a : arr)
			if(mid >= a)
			{
				if(K >= mid - a) 
					K -= mid - a;
				else
					return false;
			}
		return true;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();		// 캐릭터의 개수 N(1<=백만)
		int K		= read();		// 레벨 총합 K(1<=십업)
		int arr[]	= new int[N];	// 현재 각 캐릭터의 레벨(1<=십억)
		long s		= 1;
		long e		= Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++)
			arr[i] = read();

		long T = 0;// 최대 올릴 수 있는 레벨
		while(s <= e)
		{
			long mid = (s + e) >> 1;
			if(check(arr, mid, K))
			{
				T = mid;
				s = mid + 1;
			}else
				e = mid -1;
		}
		System.out.print(T);
	}
}