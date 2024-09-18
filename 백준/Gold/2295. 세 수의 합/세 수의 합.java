//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2295
import java.util.Arrays;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();		// 5<=1000
		int arr[]	= new int[N];	// 1<=이억
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		
		for(int i=N-1; i>=0; i--)
			for(int j=i; j>=0; j--)
				for(int k=j; k>=0; k--)
				{
					int target = arr[i] - (arr[j] + arr[k]);
					if(target <= 0) break;
					
					if(Arrays.binarySearch(arr, target) >= 0)
					{
						System.out.print(arr[i]);
						return;
					}
				}
		
	}
}