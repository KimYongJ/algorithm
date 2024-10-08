//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2878
import java.util.PriorityQueue;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
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
		final long MOD = (long)Math.pow(2, 64);
		int M		= read();		// 사탕개수(1<=20억)
		int N		= read();		// 친구들 수(1<=십만)
		int arr[]	= new int[N];	// 친구들이 받고자하는 사탕의 수
		int s		= 0;
		int e		= 0;
		
		for(int i=0; i<N; i++)
		{
			arr[i]	= read();
			e		= Math.max(e, arr[i]);
		}
		
		int max = 0;
		
		while(s <= e)
		{
			int mid = (s + e) >> 1;	// arr배열의 최대 값
			if(check(arr, mid, M))
			{
				max = mid;
				e	= mid - 1;
			}
			else
				s	= mid + 1;
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
		
		while(!pq.isEmpty())
		{
			long num = pq.poll();
			res += (num * num) % MOD;
		}
		
		System.out.print(res);
	}
}