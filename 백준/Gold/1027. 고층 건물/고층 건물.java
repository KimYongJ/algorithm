//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1027
class Main{

	public static void main(String[] args)throws Exception{
		int N		= read();
		double[]arr	= new double[N + 1];

		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		int ans = 0;
		
		for(int n=1, cnt = 0; n<=N; n++, cnt = 0)
		{
			double incl = 0.0;
			// 왼쪽
			for(int i=n - 1; 1<=i; i--)
			{
				double nextIncl = (arr[n] - arr[i]) / (n - i);
				if(i == n - 1 || incl > nextIncl)
				{
					incl = nextIncl;
					++cnt;
				}
			}
			// 오른쪽
			for(int i=n + 1; i<=N; i++)
			{
				double nextIncl = (arr[n] - arr[i]) / (n - i);
				if(i == n + 1 || incl < nextIncl)
				{
					incl = nextIncl;
					++cnt;
				}
			}

			ans = Math.max(ans, cnt);
		}
		
		System.out.print(ans);
	}

	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}