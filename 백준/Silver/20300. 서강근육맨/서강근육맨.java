// https://github.com/kimyongj/algorithm
import java.util.Arrays;
class Main{
	static long read() throws Exception {// 빠른 입력을 위한 함수
		long c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= (int)read();
		long arr[]	= new long[N];
		int left	= 0;
		int right	= N-1;
		long max	= 0;
		for(int i=0; i<N; i++) 
		{
			arr[i] = read();
		}
		
		Arrays.sort(arr);
		
		if(N%2 == 1)	// 홀수인 경우 마지막을 max로 놓고, 나머지들을 비교연산한다.
		{
			right --;
			max = arr[N-1];
		}
		
		while(left < right) 
		{
			max = Math.max(max, arr[left++] + arr[right--]);
		}
		
		System.out.print(max);
	}
}