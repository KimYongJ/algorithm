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
		int N		= read();	// 솔루션개수 (1<=십만)
		int arr[]	= new int[N];	// 각 솔루션 파일 크기(1<=억)
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		
		long cnt = 0;
		for(int i=N-1; i>=0; i--)
		{
			int target	= (int)Math.ceil(arr[i] * 0.9);
			int s		= 0;
			int e		= i - 1;
			
			while(s <= e)
			{
				int mid = (s + e) >> 1;
				if(target <= arr[mid])
					e = mid - 1;
				else
					s = mid + 1;
			}
			cnt += i - s;
		}
		
		System.out.print(cnt);
	}
}