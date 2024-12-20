//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11441
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
		StringBuilder sb = new StringBuilder();
		int N		= read();
		int arr[]	= new int[N+1];
		
		for(int i=1; i<=N; i++)
			arr[i] = read() + arr[i-1];
		
		int T = read();
		while(T-->0)
		{
			int s = read();
			int e = read();
			sb.append(arr[e] - arr[s-1]).append('\n');
		}
		System.out.print(sb);
	}
}