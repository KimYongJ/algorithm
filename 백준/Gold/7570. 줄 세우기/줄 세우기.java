//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7570
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();
		int res		= 0;
		int cnt		= 1;
		int arr[]	= new int[N+1];
		
		for(int i=1; i<=N; i++)
			arr[read()] = i;
		
		for(int i=2; i<=N; i++) 
		{
			if(arr[i-1] < arr[i])
				res = Math.max(res, ++cnt);
			else
				cnt = 1;
		}
		
		res = Math.max(res, 1);
		
		System.out.print(N-res);
	}
}