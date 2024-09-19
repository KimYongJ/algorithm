//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2143
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
	public static int upper(long arr[], long target) {
		int idx = -1;
		int s = 0;
		int e = arr.length - 1;
		while(s <= e) {
			int mid = (s + e) >> 1;
			if(arr[mid] <= target)
			{
				
				s = mid + 1;
				if(arr[mid] == target)
					idx = mid;
			}
			else e = mid - 1;
		}
		return idx;
	}
	public static int lower(long arr[], long target) {
		int idx = -1;
		int s = 0;
		int e = arr.length - 1;
		while(s <= e) {
			int mid = (s + e) >> 1;
			if(arr[mid] >= target)
			{
				e = mid - 1;
				if(arr[mid] == target)
					idx = mid;
			}
			else
				s = mid + 1;
		}
		return idx;
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
		
		long list1[] = new long[N*(N+1) / 2];
		long list2[] = new long[M*(M+1) / 2];
		
		int idx = 0;
		for(int i=0; i<N; i++)
		{
			list1[idx++] = arr1[i];
			for(int j=i+1; j<N; j++)
				list1[idx++] = arr1[i] += arr1[j];
		}
		idx = 0;
		for(int i=0; i<M; i++)
		{
			list2[idx++] = arr2[i];
			for(int j=i+1; j<M; j++)
				list2[idx++] = arr2[i] += arr2[j];
		}
		
		Arrays.sort(list1);
		Arrays.sort(list2);
		
		for(long l : list1)
		{
			long target = T - l;
			int idx1 = upper(list2, target);
			int idx2 = lower(list2, target);
			if(idx1 != -1 && idx2 != -1)
				cnt += idx1 - idx2 + 1;
			else if(idx1 != -1 || idx2 != -1)
				cnt++;
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