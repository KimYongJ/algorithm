//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2473
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
		int N		= read();		// 용액의 수 (3<=오천)
		long arr[]	= new long[N];	// 특성 값(-십억<=십억)
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		
		long resMin = Long.MAX_VALUE;
		long res[] = new long[3];
		
		for(int i=0; i<N-2; i++)
		{
			int s = i + 1;
			int e = N - 1;
			while(s < e)
			{
				long sum = arr[i] + arr[s] + arr[e];
				
				if(Math.abs(sum) < resMin)
				{
					res[0] = arr[i];
					res[1] = arr[s];
					res[2] = arr[e];
					resMin = Math.abs(sum);
				}
				
				if(sum < 0) s++;
				else e--;
				
			}
		}

		System.out.print(
				new StringBuilder().append(res[0]).append(' ').append(res[1]).append(' ').append(res[2])
				);
	}
}