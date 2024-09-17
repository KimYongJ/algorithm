//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2467
import java.util.Arrays;
class Main{
	public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();
		int arr[]	= new int[N];
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		
		int s		= 0;
		int e		= N-1;
		int diff	= Integer.MAX_VALUE;
		int res1	= 0;
		int res2	= 0;
		
		while(s<e)
		{
			int sum = arr[s] + arr[e];
			if(Math.abs(sum) < diff)
			{
				diff = Math.abs(sum);
				res1 = arr[s];
				res2 = arr[e];
			}
			if(sum < 0)
				s++;
			else
				e--;
		}
		System.out.print( new StringBuilder().append(res1).append(' ').append(res2) );
	}
}