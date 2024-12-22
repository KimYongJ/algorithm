//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2435
class Main{

    static int read() throws Exception {
	    int c, n = System.in.read();
	    while (n <= ' ') {n = System.in.read();}
	    boolean minus = true;
	    if (n > 47) {minus = false;
	    n &= 15;} else n = 0;
	    while ((c = System.in.read()) > 47) n = (n << 3) + (n << 1) + (c & 15);
	    return minus? -1 * n: n;
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