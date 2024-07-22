// https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder	sb = new StringBuilder();
		int T = read();
		while(T-->0) 
		{
			int day		= read();
			int price[]	= new int[day];
			
			for(int i=0; i<day; i++) 
				price[i] = read();
			
			long res = 0;
			int start = price[day-1];
			
			for(int i=day-1; i>=0; i--) 
				if(price[i] > start)
					start = price[i];
				else
					res += start - price[i];
			
			sb.append(res).append('\n');
		}
		System.out.print(sb.toString());
	}
}