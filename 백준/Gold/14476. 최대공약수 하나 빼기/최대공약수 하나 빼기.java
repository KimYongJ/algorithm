//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14476
class Main{
	
	static int maxGcd, idx;
	
	public static void main(String[] args)throws Exception{
		int N			= read();
		int arr[]		= new int[N];
		int forward[]	= new int[N];
		int backward[]	= new int[N];
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		forward[0] = arr[0];
		backward[N-1] = arr[N-1];
		
		for(int i=1; i<N; i++)
			forward[i] = gcd(forward[i-1], arr[i]);
		for(int i=N-2; i>=0; i--)
			backward[i] = gcd(backward[i+1], arr[i]);
		
		setMax(backward[1], 0);		// 없애는 숫자가 가장 앞 숫자인 경우
		setMax(forward[N-2], N-1);	// 없애는 숫자가 가장 뒤 숫자인 경우
		
		for(int i=1; i<N - 1; i++)	// 없애는 숫자 범위는 1부터 N-2까지만 탐색함
			setMax(gcd(forward[i-1], backward[i+1]), i);
		
		if(arr[idx] % maxGcd == 0)
			System.out.print(-1);
		else
			System.out.print(new StringBuilder().append(maxGcd).append(' ').append(arr[idx]));
	}
	public static void setMax(int gcd, int i) {
		if(maxGcd < gcd)
		{
			maxGcd = gcd;
			idx = i;
		}
	}
	public static int gcd(int a, int b) {
		int tmp;
		while(b!=0)
		{
			tmp = b;
			b = a%b;
			a = tmp;
		}
		return a;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}