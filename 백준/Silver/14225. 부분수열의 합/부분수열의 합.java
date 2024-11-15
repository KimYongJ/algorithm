//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/14225
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();
		int arr[]	= new int[N];

		for(int i=0; i<N; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		
		int res = 1;
		
		for(int i=0; i<N; i++)
		{
			if(res < arr[i])
				break;
			res += arr[i];
		}
		
		System.out.print(res);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}