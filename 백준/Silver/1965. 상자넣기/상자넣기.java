//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1965
//2초 / 128MB
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();// 1<=천
		int LIS[]	= new int[N + 1];
		int idx		= 0;

		for(int i=1; i<=N; i++)
		{
			int n = read();
			if(LIS[idx] < n)
				LIS[++idx] = n;
			else
			{
				// target보다 큰것 중 제일 작은것
				int j = binarySearch(LIS, n,1, idx);
				LIS[j] = n;
			}
		}
		System.out.print(idx);
	}
	public static int binarySearch(int[] arr, int target,int s, int e) {
		int result = -1;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(arr[mid] >= target)
			{
				result = mid;
				e = mid - 1;
			}
			else
				s = mid + 1;
		}
		return result;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
