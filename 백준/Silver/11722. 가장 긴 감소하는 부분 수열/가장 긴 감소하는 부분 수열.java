//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11722
//1초 / 256MB
//가장 긴 감소하는 부분수열의 길이 출력
class Main{

	static int len = 1;
	
	public static void main(String[]args)throws Exception{
		int N		= read();
		int arr[]	= new int[N+1];// 1<=천
		int desc[]	= new int[N+1];
		
		for(int i=1; i<=N; i++)
			arr[i] = read();// 1<=천

		desc[1] = arr[1];
		
		for(int i=2; i<=N; i++)
		{
			if(desc[len] > arr[i])		// 자기보다 작은 수를 찾을 수 없는 경우 뒤에 값 삽입
				desc[++len] = arr[i];
			else
				// arr[i]보다 작은 숫자의 인덱스를 가져온다.
				desc[binarySearch(desc, arr[i])] = arr[i];
		}
		System.out.print(len);
	}
	public static int binarySearch(int[] desc, int target) {
		int s	= 1;
		int e	= len;
		int res = -1;
		
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(desc[mid] > target)
				s = mid + 1;
			else
			{
				res = mid;
				e = mid - 1;
			}
		}
		return res;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
