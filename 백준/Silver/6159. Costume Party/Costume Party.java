//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6159
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 소의 수(2<=이만)
		int S		= read();	// 매칭값(1<=백만)
		int arr[]	= new int[N];
		int res		= 0;
		
		for(int i=0; i<N; i++)
			arr[i] = read();// 매칭값(1<=백만)
		
		Arrays.sort(arr);
		
		int s = 0;
		int e = N - 1;
		while(s<e)
		{
			int sum = arr[s] + arr[e];
			if(sum <= S)
			{
				res += e - s;
				++s;
			}
			else --e;
		}
		System.out.print(res);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}