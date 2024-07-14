// https://github.com/kimyongj/algorithm
import java.util.Arrays;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();
		long sum	= 0;
		int arr[]	= new int[N];
		
		for(int i=0; i<N; i++) 
		{
			arr[i] = read() + 1;
		}
		
		Arrays.sort(arr);
		
		for(int i=N-1, j = 1; i>=0; i--, j++) 
		{
			if(arr[i] - j > 0)
			{
				sum += arr[i] - j;
			}
			else break;
		}
		
		System.out.print(sum);
	}
}