//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25916
//1초 / 1024MB
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 배열개수(1≤오십만)
		int M		= read();	// 사용가능한 최대 값(1≤10의9승)
		int arr[]	= new int[N];

		for(int i=0; i<N; i++)
			arr[i] = read();
		
		int s	= 0;
		int e	= 0;
		int sum = 0;
		int res = 0;
		
		while(e<N)
		{
			sum += arr[e++];
			
			while(M < sum)
				sum -= arr[s++];
			
			res = Math.max(res, sum);
		}
		
		System.out.print(res);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}