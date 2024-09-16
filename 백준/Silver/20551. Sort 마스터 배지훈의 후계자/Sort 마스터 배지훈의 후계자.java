//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20551
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
		int N		= read();		// 배열 A의 원소 개수(1<=이십만)
		int M		= read();		// 질문의 개수(1<=이십만)
		int arr[]	= new int[N];	// 원소를 담을 배열(-십억<=+십억)
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		
		while(M-->0)
		{
			int targetNumber = read();
			int s	= 0;
			int e	= N-1;
			int idx = -1;
			
			while(s <= e)
			{
				int mid = (s + e) >> 1;
				if(arr[mid] >= targetNumber)
				{
					e = mid -1;
					if(arr[mid] == targetNumber)
						idx = mid;
				}else
					s = mid + 1;
			}
			sb.append(idx)
				.append('\n');
		}
		System.out.print(sb.toString());
	}
}