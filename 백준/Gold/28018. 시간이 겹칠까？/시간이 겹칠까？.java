//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/28018
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N		= read();
		int arr[]	= new int[1_000_002];
		
		while(N-->0)
		{
			arr[read()]++;
			arr[read()+1]--;
		}
		
		for(int i=2; i<=1_000_000; i++)
			arr[i] += arr[i-1];
		
		int Q = read();
		
		while(Q-->0)
			sb.append(arr[read()]).append('\n');
		
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}