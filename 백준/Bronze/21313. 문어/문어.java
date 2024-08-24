//https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder	sb	= new StringBuilder();
		String str			= "1 2 ";
		int N				= read();
		boolean flag		= false;
		
		if(N % 2 != 0)
		{
			N--;
			flag = true;
		}
		
		N /= 2;
		
		for(int i=0; i<N; i++)
			sb.append(str);
	
		if(flag)
			sb.append(3);

		System.out.print(sb.toString());
	}
}