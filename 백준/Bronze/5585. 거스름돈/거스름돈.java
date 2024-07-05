// https://github.com/kimyongj/algorithm

class Main{

	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		final int arr[]		= {500, 100, 50, 10, 5, 1};
		int num				= 1000 - read();
		int result 			= 0;
		
		for(int i=0; i<arr.length && num != 0; i++) 
		{
			result += num / arr[i];
			num %= arr[i];
		}
		
		System.out.print(result);
	}
}