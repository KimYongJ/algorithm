//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1912
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();
		int curSum	= 0;
		int maxSum	= ~(1<<30);
		while(N-->0)
		{
			int n	= read();
			curSum	= Math.max(curSum + n, n);
			maxSum	= Math.max(curSum, maxSum);
		}
		System.out.print(maxSum);
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
