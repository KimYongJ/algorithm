//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/18353
//1초 / 256MB

class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();//1<=이천
		int arr[]	= new int[N+1];
		int idx		= 0;
		
		arr[0] = 10_000_001;

		for(int i=1; i<=N; i++)
		{
			int num = read();//1<=천만
			if(arr[idx] > num)
				arr[++idx] = num;
			else
				// arr에서 num보다 작은 가장 큰 수 인덱스
				arr[binarySearch(arr, num, 1, idx)] = num;
		}
		System.out.print(N - idx);
	}
	public static int binarySearch(int[] arr, int target, int s, int e) {
		int res = 0;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(arr[mid] < target)
			{
				res = mid;
				e = mid - 1;
			}
			else
				s = mid + 1;
		}
		return res;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}