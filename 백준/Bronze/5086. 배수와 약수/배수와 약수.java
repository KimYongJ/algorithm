//https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String args[])throws Exception{
		StringBuilder sb = new StringBuilder();
		int a = read();
		int b = read();
		while(a!=0 && b!=0) {
			if(b%a == 0) {
				sb.append("factor");
			}else if(a%b == 0) {
				sb.append("multiple");
			}else {
				sb.append("neither");
			}
			sb.append('\n');
			a = read();
			b = read();
		}
		System.out.print(sb.toString());
	}
}