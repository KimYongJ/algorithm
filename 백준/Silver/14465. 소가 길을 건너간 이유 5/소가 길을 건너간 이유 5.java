//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14465
class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();	// 횡단보도(1<=십만)
		int K		= read();	// 연속한 신호등개수
		int B		= read();	// 고장난 신호등 개수
		int arr[]	= new int[N+1];
		int ksum	= 0;
		
		
		for(int i=1; i<=B; i++)
			arr[read()] = 1;
		
		for(int i=1; i<=K; i++)
			ksum += arr[i];
		
		int min = ksum;
		for(int i=K+1,j=1; i<=N; i++)
		{
			ksum += arr[i] - arr[j++];
			if(ksum < min && (min = ksum) == 0)
				break;
		}
		
		System.out.print(min);
	}
}