//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14921
//1초 / 512MB
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 2<=십만
		int arr[]	= new int[N];

		for(int i=0; i<N; i++)
			arr[i] = read();	// 절대값|억|
		
		int res = 1<<30;
		int s	= 0;
		int e	= N - 1;
		while(s < e)
		{
			int abs = arr[e]+arr[s];
			if(Math.abs(abs) < Math.abs(res))
				res = abs;
			
			if(abs < 0)
				++s;
			else
				--e;
		}
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