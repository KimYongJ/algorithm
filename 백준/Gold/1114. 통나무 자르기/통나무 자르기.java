// https://github.com/kimyongj/algorithm
// https://www.acmicpc.net/problem/1114
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken()); // 최대 길이
		int K = Integer.parseInt(st.nextToken()); // 위치개수
		int C = Integer.parseInt(st.nextToken()); // 자르는 횟수
		
		st = new StringTokenizer(br.readLine());
		HashSet<Integer> set = new HashSet<>();
		for(int i=0; i<K; i++)
			set.add(Integer.parseInt(st.nextToken()));
		
		K = set.size()+2;
		int arr[] = new int[K];
		int idx = 1;
		for(int s : set)
			arr[idx++] = s;
		
		arr[K-1] = L;
		
		C = Math.min(C, K); // 자라는 횟수와 위치 개수 중 작은것을 선택.  
		
		Arrays.sort(arr);
		
		int res = L;
		int start = 0;
		int end = L+1;
		int mid;
		while(start <= end) {
			mid = (start + end) / 2;
			if(check(mid,C,L,arr)) {
				res = mid;
				end = mid-1;
			}else {
				start = mid+1;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(res).append(' ').append(getIdx(res,C-1,L,arr));
		System.out.print(sb.toString());
	}
	public static int getIdx(int mid,int cnt,int L,int[] arr) {
		int s = 1;
		int len = arr.length;
		while(true) 
		{
			boolean flag = true;
			int start = arr[s];
			int count = cnt;
			for(int i=s+1; i<len; i++)
			{
				if(arr[i] - start > mid) 
				{
					start = arr[i-1];
					if(arr[i] - arr[i-1] > mid || --count <0)
					{
						flag = false;
						break;
					}
				}
			}
			
			if(flag)
				return arr[s];
			
			s++;
		}
	}
	public static boolean check(int mid, int cnt,int L,int[] arr) {
		if(arr[0] > mid) 
			return false;
		int len = arr.length;
		int start = 0;
		for(int i=1; i<len; i++) 
		{
			if(arr[i] - start > mid) 
			{
				if(arr[i] - arr[i-1] > mid)
					return false;
				
				start = arr[i-1];
				
				if(--cnt<0)
					return false;
			}
		}
		return true;
				
	}
}