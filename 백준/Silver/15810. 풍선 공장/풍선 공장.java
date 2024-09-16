//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15810
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static boolean check(int arr[], long maxTime, long targetNumber){
		for(int a : arr) {
			targetNumber -= maxTime / a;
			if(targetNumber <= 0)
				return true;
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();		// 스테프 수(1<=백만)
		int M		= read();		// 목표 풍선 개수(1<=백만)
		int arr[]	= new int[N];	// 각 스태프들이 풍선을 만드는데 걸리는 시간(1<=백만)
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		long s			= 1;
		long e			= 1_000_000_000_001L; // 1명이 백만개의 풍선을 만드는데, 하나만들 때 마다 백만시간이 걸린다는 가정하에
		long minTime	= 0;
		
		while(s <= e)
		{
			long mid = (s + e) >> 1;// 풍선을 만들 시간
 			if(check(arr, mid, M))
			{
				e = mid -1;
				minTime = mid;
			}else
				s = mid + 1;
		}
		System.out.print(minTime);
	}
}