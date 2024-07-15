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
		int arr[]	= new int[N];
		int res		= 0;
		
		for(int i=0; i<N; i++) 
		{
			arr[i] = read();
		}
		
		Arrays.sort(arr);
		
		for(int i=0; i<N/2; i++) 
		{
			int tmp = arr[i];
			arr[i] = arr[N-i-1];
			arr[N-i-1] = tmp;
		}
		
		for(int i=0; i<N; i++) 
		{
			if((i+1)%3 == 0) 
			{
				continue;
			}
			res += arr[i];
		}
		System.out.print(res);
	}
}