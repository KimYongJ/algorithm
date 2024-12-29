//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20116
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 상자개수(1<=이십만)
		int L		= read();	// 상자 사이즈(1<=십억)
		double psum[] = new double[N+1];
		double arr[][]= new double[N+1][2];

		for(int i=1; i<=N; i++)
		{
			psum[i]		= read();
			arr[i][0]	= psum[i] - L;
			arr[i][1]	= psum[i] + L;
		}
		
		for(int i=N-1; i>0; i--)
			psum[i] += psum[i + 1];

		for(int i=N-1, j=1; i>0; i--,j++)
		{
			double mid = psum[i+1] / j;
			if(mid <= arr[i][0] || arr[i][1] <= mid)
			{
				System.out.print("unstable");
				return;
			}
		}
		System.out.print("stable");
	}
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
}