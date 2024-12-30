//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14929
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();
		int arr[]	= new int[N+1];
		int psum[]	= new int[N+1];
		long res	= 0;
		
		for(int i=1; i<=N; i++)
			psum[i] = (arr[i] = read()) + psum[i-1];
		
		for(int i=1; i<=N; i++)
			res += arr[i] *(psum[N]-psum[i]);
		
		System.out.print(res);
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