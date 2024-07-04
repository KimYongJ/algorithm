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
			int num = read() * 100;
			sb.append(num / 2500).append(' ');
			num %= 2500;
			sb.append(num / 1000).append(' ');
			num %= 1000;
			sb.append(num / 500).append(' ');
			sb.append((num % 500)/100).append('\n');
		}
		System.out.print(sb.toString());
	}
}