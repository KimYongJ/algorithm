//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17245
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();
		int s		= 1;
		int e		= 0;
		int res		= 0;
		int arr[][] = new int[N][N];
		long total	= 0;
		
		
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
			{
				int n = read();
				if(n > 0)
				{
					e = Math.max(e, n);
					arr[i][j] = n;
					total += n;
				}
			}

		
		total = (long)Math.ceil(total / 2.0);
		
		while(s <= e)
		{
			int mid		= (s + e) >> 1;
			long sum	= 0;
			
			for(int i=0; i<N; i++)
				for(int j=0; j<N; j++)
					if(arr[i][j] != 0)
						sum += Math.min(mid, arr[i][j]);
			
			if(total <= sum)
			{
				res = mid;
				e = mid - 1;
			}else
				s = mid + 1;
		}
		System.out.print(res);
	}
}