//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12003
//2초 / 512MB

import java.util.Arrays;

class Main{
	
	public static void main(String[] args)throws Exception{
		int N		= read();		// 배열
		int K		= read();		// 최대 차이
		int arr[]	= new int[N];
		int count[]	= new int[N];	// i번째 인덱스에서 시작해서 K차이 이하인 가장 큰 개수
		int dp[]	= new int[N+1];	// i번째를 포함한 이후 가장 큰 개수
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		
		for(int i=0; i<N; i++)
			count[i] = binarySearch(i, N-1, arr, arr[i] + K) - i + 1;
		
		for(int i=N-1; i>=0; i--)
			dp[i] = Math.max(dp[i+1], count[i]);
		
		int res =  0;
		
		for(int i=0; i<N; i++)
			res = Math.max(res, count[i] + dp[i + count[i]]);
		
		System.out.print(res);
	}
	public static int binarySearch(int s, int e, int[] arr, int target) {
		int res = 0;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(arr[mid] <= target)
			{
				res = mid;
				s = mid + 1;
			}
			else 
				e = mid - 1;
		}
		return res;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}