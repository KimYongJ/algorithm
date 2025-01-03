//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/19951
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	//구덩이개수(1≤100,000)
		int M		= read();	//조교수(1≤100,000)
		int arr[]	= new int[N+2];
		int psum[]	= new int[N+2];
		
		for(int i=1; i<=N; i++)
			arr[i] = read();
		
		while(M-->0)
		{
			int a = read();
			int b = read();
			int k = read();// k가 양수인경우 a,b범위내 흙 추가, 음수는 흙 감소
			psum[a] += k;
			psum[b+1] += -k;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++)
			sb.append(arr[i] + (psum[i] += psum[i-1])).append(' ');

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