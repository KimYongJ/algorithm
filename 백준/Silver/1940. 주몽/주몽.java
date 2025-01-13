//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1940
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 1<=만오천
		int M		= read();	// 1<=천만
		int cnt		= 0;
		int arr[]	= new int[N];
		boolean v[] = new boolean[100_001];

		for(int i=0; i<N; i++)
			v[arr[i] = read()] = true;
		
		for(int a : arr)
		{
			int target = M - a;
			if(0 <= target && target <= 100_001 && v[target])
					++cnt;
		}
		
		System.out.print(cnt / 2);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}