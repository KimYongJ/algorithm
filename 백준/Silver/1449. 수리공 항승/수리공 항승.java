// https://github.com/kimyongj/algorithm
import java.util.Arrays;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N = read();
		int L = read();
		int R = 1;
		int arr[] = new int[N];

		for(int i=0; i<N; i++) 
			arr[i] = read();
		
		Arrays.sort(arr);
		
		double start	= arr[0] - 0.5;
		double end		= start + L;
		
		for(int i=1; i<N; i++)
			if(arr[i] + 0.5 > end) 
			{
				start	= arr[i] - 0.5;
				end		= start + L;
				R++;
			}

		System.out.print(R);
	}
}