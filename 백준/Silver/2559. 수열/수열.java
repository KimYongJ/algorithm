//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2559
class Main{
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
	public static void main(String[] args)throws Exception{
		int N		= read();	// 2<=십만
		int K		= read();	// 일자
		int arr[]	= new int[N];
		int sum		= 0;
		int max		= 0;

		for(int i=0; i<N; i++)
			arr[i] = read();
		
		for(int i=0; i<K; i++)
			sum += arr[i];
		
		max = sum;
		
		for(int i=0,j=K; j<N; i++,j++)
		{
			sum += arr[j] - arr[i];
			max = Math.max(sum, max);
		}
		System.out.print(max);
	}
}
