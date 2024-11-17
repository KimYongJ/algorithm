//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1027
class Main{
	
	static int N;
	static double arr[];
	
	public static void main(String[] args)throws Exception{
		N	= read();
		arr = new double[N + 1];

		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		int ans = 0;
		
		for(int i=1; i<=N; i++)
			ans = Math.max(ans, get(i));
		
		System.out.print(ans);
	}
	public static int get(int idx) {
		int cnt = 0;
		double incl = 0.0;
		for(int i=idx - 1; 1<=i; i--)
		{
			double nextIncl = (arr[idx] - arr[i]) / (idx - i);
			if(i == idx -1 || incl > nextIncl)
			{
				incl = nextIncl;
				++cnt;
			}
		}
		for(int i=idx + 1; i<=N; i++) {
			double nextIncl = (arr[idx] - arr[i]) / (idx - i);
			if(i == idx + 1 || incl < nextIncl)
			{
				incl = nextIncl;
				++cnt;
			}
		}
		
		return cnt;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}