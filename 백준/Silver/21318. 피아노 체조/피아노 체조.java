//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/21318
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N		= read();	// 악보 개수(1<=십만)
		int psum[]	= new int[N+1];
		int arr[]	= new int[N+1];

		for(int i=1; i<=N; i++)
		{
			arr[i] = read();// 난이도(1<=십억)
			if(arr[i] < arr[i-1])
				++psum[i-1];
			
			psum[i] += psum[i-1];
		}
		
		int Q = read();// 질문개수(1<=십만)
		while(Q-->0)
		{
			int x = read() - 1;
			int y = read() - 1;
			sb.append(psum[y] - psum[x]).append('\n');
		}
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}