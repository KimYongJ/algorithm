//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2143
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long cnt		= 0;
		long T			= Integer.parseInt(br.readLine());	// -십억<=십억
		int N			= Integer.parseInt(br.readLine());	// 1<=천
		Long arr1[]		= new Long[N];						// -백만<=백만
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr1[i] = Long.parseLong(st.nextToken());
		
		int M			= Integer.parseInt(br.readLine());
		Long arr2[]		= new Long[M];						// -백만<=백만
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++)
			arr2[i] = Long.parseLong(st.nextToken());
		
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
