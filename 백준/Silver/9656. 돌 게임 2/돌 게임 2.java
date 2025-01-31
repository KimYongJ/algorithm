//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9656
//1초 / 128mb

class Main{
	public static void main(String[] args)throws Exception{
		System.out.print(read() % 2 == 0 ? "SK" : "CY");
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
