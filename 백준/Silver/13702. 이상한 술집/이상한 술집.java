//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/13702
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N = read(); // 막걸리 주전자 개수 N( 만이하 )
		int K = read(); // 나눌 사람의 수 K( 백만이하 )
		int arr[] = new int[N]; // 막걸리 용량 ( 0 <= int형최대 )

		for(int i=0; i<N; i++)
			arr[i] = read();
		
		long s = 1,e = Integer.MAX_VALUE;
		long res = 0;
		while(s <= e)
		{
			long mid = (s + e) / 2;
			int k = K;
			
			for(int a : arr)
				k -= a / mid;
			
			if(k <= 0)
			{
				res = mid;
				s = mid + 1;
			}
			else
				e = mid - 1;
		}
		System.out.print(res);
	}
}