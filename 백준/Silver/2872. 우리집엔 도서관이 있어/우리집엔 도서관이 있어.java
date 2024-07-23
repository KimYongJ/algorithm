// https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();
		int arr[]	= new int[N];
		int idx		= 0;
		for(int i=0; i<N; i++) 
		{
			arr[i] = read();
			if(arr[i] == N) 
			{
				idx = i;
			}
		}
		if(arr[0] == N) 
			System.out.print(N-1);
		else 
		{
			int cnt	 = 1;
			for(int i=idx-1; i>=0; i--) 
			{
				if(arr[idx]-1 == arr[i]) 
				{
					--arr[idx];
					++cnt;
				}
			}
			System.out.print(N - cnt);
		}
	}
}