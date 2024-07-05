// https://github.com/kimyongj/algorithm
class Main{
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		int N		= read();
		int result	= 0;
		int cnt		= 0;
		int num1	= 0;
		int num2	= 0;
		for(int i=0; i<N; i++) 
		{
			num2 = read();
			if(num1 > num2) 
			{
				cnt++;
			}
			else 
			{
				result	= Math.max(result, cnt);
				num1	= num2;
				cnt		= 0;
			}
		}
		System.out.print(Math.max(result, cnt));
	}
}