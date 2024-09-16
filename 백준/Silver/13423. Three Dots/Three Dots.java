//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13423
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
		while(T-- > 0)
		{
			int res		= 0;
			int N		= read();		// 점의 개수(3<=천)
			int arr[]	= new int[N];	// 점을 담을 배열(-1억<=1억)
			
			for(int i=0; i<N; i++)
				arr[i] = read();
			
			Arrays.sort(arr);
			
			for(int i=1; i<N-1; i++)
			{
				int leftIdx	= i - 1;
				int rightIdx= i + 1;
				while(leftIdx >= 0 && rightIdx < N)
				{
					int leftDiff	= arr[i] - arr[leftIdx];
					int rightDiff	= arr[rightIdx] - arr[i];
					if(leftDiff == rightDiff)
					{
						res++;
						leftIdx--;
						rightIdx++;
					}else if(leftDiff < rightDiff)
						leftIdx--;
					else
						rightIdx++;
				}
				
			}
			sb.append(res).append('\n');
		}
		System.out.print(sb.toString());
	}
}