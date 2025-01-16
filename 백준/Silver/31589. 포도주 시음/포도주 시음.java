//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/31589
//1초 / 512MB
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 포도주 수N (1≤300,000)
		int K		= read();	// 최대 선택 가능 수(1≤N)
		long arr[]	= new long[N];

		for(int i=0; i<N; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		
		long res	= arr[N-1];
		int s		= 0;
		int e		= N - 2;
		
		--K;
		
		while(K>1)
		{
			res += arr[e--] - arr[s++];
			K-=2;
		}
		System.out.print(res);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}