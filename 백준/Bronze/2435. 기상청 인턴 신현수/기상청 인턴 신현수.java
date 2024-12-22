//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2435
class Main{
    static int read() throws Exception{
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {c = System.in.read();}
        boolean minus = false;
        if (c == '-') {
            minus = true;
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        if (minus) return -val;
        return val;
    }
	public static void main(String[] args)throws Exception{
		int N		= read();	// 전체 날짜(2<=100)
		int K		= read();	// 합을 구하기 위한 연속적 날자 수(1<=N)
		int arr[]	= new int[N+1];
		
		for(int i=1; i<=N; i++)
			arr[i] = read() + arr[i-1];
		
		int max = ~(1<<30);
		for(int i=K; i<=N; i++)
			if(max < arr[i] - arr[i - K])
				max = arr[i] - arr[i - K];
		System.out.print(max);
	}
}