//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11561
class Main{
	static long read() throws Exception {// 빠른 입력을 위한 함수
		long c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = (int)read();
		while(T-- > 0)
			sb.append((long)((-1 + Math.sqrt(1 + 8 * read())) / 2)).append('\n');
		System.out.print(sb.toString());
	}
}