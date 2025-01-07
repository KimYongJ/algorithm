//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20159
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();// 2<=십만
		int len		= N/2;
		int card[]	= new int[N];
		int odd[]	= new int[len];
		int even[]	= new int[len];
		
		for(int i=0; i<N; i+=2)
		{
			odd[i/2]	= card[i]	= read();// 1<=만
			even[i/2]	= card[i+1]	= read();// 1<=만
		}
		
		for(int i=1; i<len; i++)
		{
			odd[i] += odd[i-1];
			even[i]+= even[i-1];
		}
		
		int max = Math.max(odd[len - 1], even[len - 1]);

		for(int i=2; i<N; i+=2) {
			max = Math.max(max, odd[i/2-1] + even[len-1] - even[i/2-1]);
			max = Math.max(max, odd[i/2] + even[len-1] - even[i/2-1]-card[N-1]);
		}
		
		System.out.print(max);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}