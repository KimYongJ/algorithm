//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25947
import java.util.Arrays;
class Main{
	
	static int N, M, G, arr[];
	static long[] psum, half;
	
	public static void main(String[] args)throws Exception{
		N		= read();	// 선물개수(1<=십만)
		M		= read();	// 예산(1<=십억)
		G		= read();	// 반값 가능한 최대선물수(0<=N)
		arr		= new int[N+1];
		psum	= new long[N+1];
		half	= new long[N+1];

		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		
		for(int i=1; i<=N; i++)
		{
			psum[i] = arr[i] + psum[i-1];
			half[i] = psum[i]>>1;
		}
		
		int left	= 1;
		int right	= N;
		int res		= 0;
		
		while(left <= right)
		{
			int mid = (left + right) >> 1;
			long cost = validate(mid);
			if(cost <= M)
			{
				left = mid + 1;
				res = mid;
			}
			else right = mid - 1;
		}
		System.out.print(res);
	}
	public static long validate(int cnt) {
		return cnt <= G ? half[cnt] : half[cnt] - half[cnt-G] + psum[cnt-G];
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
