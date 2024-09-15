//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2776
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
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0)
		{
			int N		= read();
			int arr[]	= new int[N];
			
			for(int i=0; i<N; i++)
				arr[i] = read();
			
			Arrays.sort(arr);
			
			int M = read();
			while(M-->0)
			{
				int num	= read();
				int s	= 0;
				int e	= N-1;
				boolean flag = false;
				while(s <= e)
				{
					int mid = (s + e) / 2;
					if(arr[mid] == num)
					{
						flag = true;
						break;
					}
					if(arr[mid] < num)
						s = mid + 1;
					else 
						e = mid - 1;
				}
				sb.append(flag ? 1 : 0).append('\n');
			}
		}
		System.out.print(sb.toString());
	}
}