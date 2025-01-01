//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17203
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 노래 개수(1<=천)
		int Q		= read();	// 질의개수(1<=천)
		int arr[]	= new int[N+1];
		int diff[]	= new int[N+1];
		
		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		for(int i=2; i<=N; i++)
			diff[i] = Math.abs((arr[i] - arr[i-1])) + diff[i-1];
		
		StringBuilder sb = new StringBuilder();
		while(Q-->0)
		{
			int l = read();
			int r = read();
			sb.append(diff[r] - diff[l]).append('\n');
		}
		System.out.print(sb);
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