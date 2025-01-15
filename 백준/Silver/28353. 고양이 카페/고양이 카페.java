//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/28353
//2초 / 1024MB
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 고양이수(1≤N≤오천)
		int K		= read();	// 최대무게K(1≤K≤10의9승)
		int C		= 0;
		int s		= 0;
		int e		= N-1;
		int arr[]	= new int[N];
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		
		while(s < e)
		{
			if(arr[e] + arr[s] <= K)
			{
				--e;
				++s;
				++C;
			}
			else
				--e;
		}
		System.out.print(C);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}