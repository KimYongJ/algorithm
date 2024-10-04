//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1637
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static long getSum(int N, int[] A, int[] B, int[] C, long target) {
		long sum = 0;
		for(int i=0; i<N; i++)
			if(A[i] <= target)
				sum += ((Math.min(target, B[i]) - A[i]) / C[i]) + 1;
		return sum;
	}
	public static void main(String[] args)throws Exception{
		int N	= read();	// 1<=이만
		int A[] = new int[N];
		int B[] = new int[N];
		int C[] = new int[N];
		long s	= 1;
		long e	= 0;
		
		for(int i=0; i<N; i++)
		{
			A[i] = read();
			B[i] = read();
			C[i] = read();
			e = Math.max(B[i], e);
		}
		
		long res = -1;
		
		while(s <= e)
		{
			long mid = (s + e) >> 1;
			// mid값 이하의 수들의 총 개수를 구해온다. 
			long sum = getSum(N, A, B, C, mid);
			if(sum % 2 == 0)
				s = mid + 1;
			else
			{
				e	= mid - 1;
				res = mid;
			}
		}
		
		if(res == -1)
		{
			System.out.print("NOTHING");
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(res).append(' ');
		sb.append(getSum(N, A, B, C, res) - getSum(N, A, B, C, res - 1));
		
		System.out.print(sb.toString());
	}
}