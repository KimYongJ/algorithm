//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2428
import java.util.Arrays;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();		// 솔루션 개수 (1<=십만)
		int arr[]	= new int[N];	// 각 솔루션 파일 크기(1<=억)
		long cnt	= 0;
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		
		for(int i=0; i<N; i++)
		{
			int target	= (arr[i] * 10) / 9;
			int s		= i + 1;
			int e		= N - 1;
			int r		= i;
			
			while(s <= e)
			{
				int mid = (s + e) >> 1;
				if(arr[mid] <= target)
				{
					r = mid;
					s = mid + 1;
				}
				else
					e = mid - 1;					
			}
			
			cnt += r - i;
		}
		
		System.out.print(cnt);
	}
}