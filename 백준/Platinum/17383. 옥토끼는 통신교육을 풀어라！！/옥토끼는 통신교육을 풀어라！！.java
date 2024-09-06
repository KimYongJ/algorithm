//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17383
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static boolean check(long mid, int arr[]) {
		int one		= 0;	// 1의 개수
		boolean two = false;// 2가있는지 체크
		for(int a : arr) 
		{
			int flag = (int)Math.ceil((double)a/mid);
			if(flag == 1) 
				one++;
			else if(flag == 2)
				two = true;
			else
				one -= flag - 2;// 1사용 개수
		}
		if(one < 0)				return false;// 1을 초과한 경우 
		if(one == 0 && two)		return false;// 2가 1개 이상인 경우는 반드시 1이 필요하다.
		return true;
	}
	public static void main(String[] args)throws Exception{
		int N		= read(); // N(1<=십만)
		int arr[]	= new int[N];
		
		for(int i=0; i<N; i++)
			arr[i] = read(); // 원소 범위(1<=1억)
		
		long start	= 1;
		long end	= 1_000_000_001;
		long res	= 0;
		long mid;
		while(start <= end) 
		{
			mid = (start + end) / 2;
			if(check(mid, arr))
			{
				res = mid;
				end = mid -1;
			}
			else 
			{
				start = mid + 1;
			}
		}
		System.out.print(res);
	}
}
/*
7
35 4 4 4 4 4 4
출력 : 5
7
1000000000 1000000000 1000000000 1000000000 1000000000 1000000000 1000000000
출력 : 1000000000
7
150000000 200000000 250000000 450000000 500000000 700000000 750000000
출력 : 250000000
 * */
