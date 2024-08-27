// https://github.com/kimyongj/algorithm
// https://www.acmicpc.net/problem/1114
import java.util.Arrays;
import java.util.HashSet;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int L = read(); // 최대 길이
		int K = read(); // 위치개수
		int C = read(); // 자르는 횟수
		
		HashSet<Integer> set = new HashSet<>();
		for(int i=0; i<K; i++)
			set.add(read());
		
		K = set.size()+2;
		int arr[] = new int[K];
		int idx = 1;
		for(int s : set)
			arr[idx++] = s;
		
		arr[K-1] = L;
		
		C = Math.min(C, K); // 자르는 횟수와 위치 개수 중 작은것을 선택.  
		
		Arrays.sort(arr);
		
		int res1 = L;
		int res2= 0;
		int start = 0;
		int end = L+1;
		int mid;
		while(start <= end) 
		{
			mid = (start + end) / 2;
			int IDX = check(mid,C,L,arr);
			if(IDX > 0)
			{
				res1 = mid;
				res2 = IDX;
				end = mid-1;
			}else {
				start = mid+1;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(res1).append(' ').append(res2);
		System.out.print(sb.toString());
	}
	public static int check(int mid, int cnt,int L,int[] arr) {
		int len = arr.length;
		int start = arr[len-1];
		for(int i=len-2; i>=0; i--) // 역순으로 탐색하여 마지막 start가 가장 먼저 자른 것이 되도록한다. 
		{
			if(start - arr[i] > mid) 
			{
				if(arr[i+1] - arr[i] > mid || --cnt<0)
					return 0;
				
				start = arr[i+1];

			}
		}
		// cnt가 0보다 크면 즉, 아직 더 자를것이 있다면 첫번째를 자를 수 있으므로 arr[1]반환
		return cnt > 0 ? arr[1] : start;
				
	}
}