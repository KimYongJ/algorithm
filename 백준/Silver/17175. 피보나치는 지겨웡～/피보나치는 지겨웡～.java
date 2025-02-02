//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17175
//1초 / 512mb
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 1<=50
		int arr[]	= new int[N+2];
		
		arr[0] = arr[1] = 1;
		
		for(int i=2; i<=N; i++)
		{
			arr[i] = arr[i-1] + arr[i-2] + 1;
			if(arr[i] >= 1_000_000_007)
				arr[i] -= 1_000_000_007;
		}
		
		System.out.print(arr[N]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}