// https://github.com/kimyongj/algorithm
class Main{
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		int N		= read();
		long W		= read();
		long coin	= 0;
		long arr[]	= new long[N+1];
		
		arr[0] = read();
		
		for(int i=1; i<N; i++) 
		{
			arr[i] = read();
			if(arr[i-1] < arr[i]) // 내일이 오를 경우 
			{
				if(W / arr[i-1] > 0) 
				{
					coin = W / arr[i-1];// 오늘 가격으로 코인을 산다.
					W %= arr[i-1];
				}
			}
			else // 내일이 떨어지거나 오늘과 같은 경우 
			{
				W += coin * arr[i-1];
				coin = 0;
			}
		}
		System.out.print(W + coin * arr[N-1]); // 모든 코인을 판다.
	}
}