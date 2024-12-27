//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12847
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 근무 가능한 일(1<=십만)
		int M		= read();	// 일할날(0<=n)
		int arr[]	= new int[N];
		long sum	= 0;
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		for(int i=0; i<M; i++)
			sum += arr[i];
		
		long max = sum;
		
		for(int i=M,j = 0; i<N; i++)
		{
			sum += arr[i] - arr[j++];
			if(max < sum)
				max = sum;
		}
		System.out.print(max);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}