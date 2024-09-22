//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1365
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static int getIdx(int LIS[], int target, int len) {
		int idx = 0;
		int s	= 0;
		int e	= len - 1;
		
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(LIS[mid] == target)
				return mid;
			if(LIS[mid] < target)
				s = mid + 1;
			else
			{
				idx = mid;
				e = mid - 1;
			}
		}
		return idx;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();	// 1 ≤ N ≤ 100,000
		int arr[]	= new int[N];
		int LIS[]	= new int[N];
		int len		= 1;
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		LIS[0] = arr[0];
		
		for(int i=1; i<N; i++)
			if(LIS[len-1] < arr[i])
				LIS[len++] = arr[i];
			else
				LIS[getIdx(LIS, arr[i], len)] = arr[i];
		
		System.out.print(N - len);
	}
}