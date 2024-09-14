//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/4158
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int N = read();
			int M = read();
			if(N==0 && M == 0)
				break;
			int res = 0;
			int arr[] = new int[N];
			for(int i=0; i<N; i++)
				arr[i] = read();
			
			for(int i=0; i<N; i++)
			{
				int g = read();
				int s = 0;
				int e = N-1;
				while(s <= e)
				{
					int mid = (s + e) / 2;
					if(arr[mid] == g)
					{
						res++;
						break;
					}
					if(arr[mid] < g)
						s = mid+1;
					else
						e = mid-1;
				}
			}
			sb.append(res).append('\n');
		}

		System.out.print(sb.toString());
	}
}