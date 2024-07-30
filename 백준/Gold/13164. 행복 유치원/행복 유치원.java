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
		int K		= read();
		int arr[]	= new int[N];
		int diff[]	= new int[N-1];
		int sum	= 0;
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		for(int i=0; i<N-1; i++)
			diff[i] = arr[i+1] - arr[i];

		Arrays.sort(diff);

		for(int i=0; i<N - K; i++)
			sum += diff[i];
		
		System.out.print(sum);
	}
}