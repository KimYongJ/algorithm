// https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int s	= read();
		int e	= read();
		int n	= read();
		int min = Math.abs(s-e);
		int next;
		while(n-->0) 
		{
			next = Math.abs(e - read()) + 1;
			if(min > next) 
			{
				min = next;
			}
		}
		System.out.print(min);
	}
}