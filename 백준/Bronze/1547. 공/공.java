// https://github.com/kimyongj/algorithm

class Main{
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		int T = read();
		int first = 1;
		int a,b;
		while(T-- > 0) 
		{
			a = read();
			b = read()	;
			if(a == first) 
			{
				first = b;
			}
			else if(b == first) 
			{
				first = a;
			}
		}
		System.out.print(first);
	}
}