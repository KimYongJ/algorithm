//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2878
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main{
	
	public static boolean check(int arr[], int maxValue, int total) {
		for(int a : arr)
		{
			int diff = a - maxValue;	// 얼만큼 줄여야 하는지
			if(0 < diff)				// 줄일 크기가 있어야만 이하 연산
			{
				if(total < diff)		// 줄일 수 없다면 false
					return false;
				total -= diff;			// 가능하면 줄인다.
			}
		}
		return true;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		final long MOD = (long)Math.pow(2, 64);
		int M		= Integer.parseInt(st.nextToken());	// 사탕개수(1<=20억)
		int N		= Integer.parseInt(st.nextToken());	// 친구들 수(1<=십만)
		int arr[]	= new int[N];						// 친구들이 받고자하는 사탕의 수
		int s		= 0;
		int e		= 0;
		
		for(int i=0; i<N; i++)
		{
			arr[i]	= Integer.parseInt(br.readLine());
			e		= Math.max(e, arr[i]);
		}
		
		int max = 0;
		while(s <= e)
		{
			int mid = (s + e) >> 1;	// arr배열의 최대 값
			if(check(arr, mid, M))
			{
				max = mid;
				e = mid - 1;
			}
			else
				s = mid + 1;
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);
		
		for(int a : arr)
		{
			if(max < a)
			{
				M -= (a - max);
				a = max;
			}
			pq.add(a);
		}
		
		while(M-->0)
			pq.add(pq.poll() - 1);
		
		long res = 0;
		while(!pq.isEmpty()) {
			long num = pq.poll();
			res += (num * num) % MOD;
		}
		System.out.print(res);
	}
}