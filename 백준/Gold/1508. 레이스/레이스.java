// https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int end = read();	// 최대길이
		int M	= read();	// 놓을 심판 개수
		int K	= read();	// 심판이 자리잡을 수 있는 곳
		int arr[]	= new int[K];// 심판이 들어갈 수 있는 위치
		
		for(int i=0; i<K; i++)
			arr[i] = read();
		
		int start = 0;
		int mid, res = 0;
		while(start <= end)
		{
			mid = (start + end) / 2;
			int m = M-1;
			int idx = 0;
			for(int i=1; i<K; i++)
			{
				if(arr[i] - arr[idx] >= mid) 
				{
					idx = i;
					if(--m==0) 
					{
						res = mid;
						start = mid+1;
						break;
					}
				}
			}
			if(m != 0)
				end = mid-1;
		}
		
		StringBuilder sb = new StringBuilder("1");
		int idx = 0;
		M--;
		for(int i=1; i<K; i++) 
		{
			if(M > 0 && arr[i] - arr[idx] >= res) 
			{
				idx = i;
				sb.append('1');
				M--;
			}
			else sb.append('0');
		}
		System.out.print(sb.toString());
	}
}