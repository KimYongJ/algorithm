// https://github.com/kimyongj/algorithm
class Main{
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		int result	= 0;
		int arr[]	= new int[10001];
		int N		= read();
				
		for(int i=0; i<N; i++) 
		{
			arr[read()]++;
		}
		
		for(int i=10000, j = 0; i>=0; i--) 
		{
			j += arr[i];
			result = Math.max(result, i * j);
		}
		System.out.print(result);
	}
}