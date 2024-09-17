//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9024
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
		while(T-- >0)
		{
			int N		= read();	// 2<=백만
			int K		= read();	// -억<=억
			int arr[]	= new int[N];
			
			for(int i=0; i<N; i++)
				arr[i] = read();
			
			Arrays.sort(arr);
			
			int diff	= Integer.MAX_VALUE;
			int cnt		= 0;
			int s		= 0;
			int e		= N-1;

			while(s < e)
			{
				int sum = arr[s] + arr[e];
				int abs = Math.abs(K - sum);
				if(diff >= abs)
				{
					if(diff == abs)
						cnt++;
					else
					{
						cnt = 1;
						diff = abs;
					}
				}
				if(sum < K)
					s++;
				else
					e--;
			}
			
			sb.append(cnt).append('\n');
		}
		System.out.print(sb.toString());
	}
}