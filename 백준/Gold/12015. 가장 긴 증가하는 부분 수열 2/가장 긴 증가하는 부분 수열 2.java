//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12015
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static int getIdx(int[] LIS, int target, int e)
	{
		int s	= 0;
		int res = 1;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(target <= LIS[mid])
			{
				res = mid;
				e = mid - 1;
			}
			else
				s = mid + 1;
		}
		return res;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();		// 1<=백만
		int arr[]	= new int[N];	// 1<=백만

		for(int i=0; i<N; i++)
			arr[i] = read();
		
		int len		= 1;
		int LIS[]	= new int[N];
		LIS[0]		= arr[0];		// 첫번 째 값을 넣는다.
		for(int i=1; i<N; i++)
		{
			if(LIS[len-1] < arr[i])
				LIS[len++] = arr[i];
			else
			{
				int idx = getIdx(LIS, arr[i], len - 1);
				if(LIS[idx] > arr[i])
					LIS[idx] = arr[i];
			}
		}
		System.out.print(len);
	}
}