//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17179
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static boolean check(int arr[], int mid , int cut, int len) {
		int start	= 0;
		int cutCnt	= 0;
		for(int i=0; i<arr.length && cutCnt < cut; i++)
			if(arr[i] - start >= mid)
			{
				cutCnt++;
				start = arr[i];
			}
		return cutCnt == cut && len - start >= mid;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N		= read();		// 자르는 횟수(1<=천)
		int M		= read();		// 자르는 지점(1<=천)
		int L		= read();		// 롤케익길이(1<x<=사백만)
		int arr[]	= new int[M];	// 자를 수 있는 지점
		
		for(int i=0; i<M; i++)
			arr[i] = read();
		
		while(N-->0)
		{
			int cut = read();// 자르는 횟수
			int s	= 1;
			int e	= L / 2;
			int res	= 0;
			
			while(s <= e)
			{
				int mid = (s + e) >> 1;	// 가장 작은 조각의 최대 길이
				if(check(arr, mid, cut, L))
				{
					s = mid + 1;
					res = mid;
				}
				else e = mid - 1;
			}
			sb.append(res).append('\n');
		}
		System.out.print(sb.toString());
	}
}