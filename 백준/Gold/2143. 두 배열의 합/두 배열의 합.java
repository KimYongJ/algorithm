//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2143
import java.util.HashMap;
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
		long cnt		= 0;
		long T			= read();		// -십억<=십억
		int N			= read();		// 1<=천
		long arr1[]		= new long[N];	// -백만<=백만
		for(int i=0; i<N; i++)
			arr1[i] = read();
		
		int M			= read();
		long arr2[]		= new long[M];	// -백만<=백만
		for(int i=0; i<M; i++)
			arr2[i] = read();
		
		HashMap<Long, Long> hm = new HashMap<>();
		for(int i=0; i<N; i++)
		{

			hm.put(arr1[i], hm.getOrDefault(arr1[i], 0L) + 1);
			for(int j=i+1; j<N; j++)
			{
				arr1[i] += arr1[j];
				hm.put(arr1[i], hm.getOrDefault(arr1[i], 0L) + 1);
			}
		}
		for(int i=0; i<M; i++)
		{
			cnt += hm.getOrDefault(T - arr2[i], 0L);
			for(int j=i+1; j<M; j++) {
				arr2[i] += arr2[j];
				cnt += hm.getOrDefault(T - arr2[i], 0L);
			}
		}
		
		System.out.print(cnt);
	}
}
/*
10
1
9
5
1 -1 -2 3 5
출 3
 * */