// https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N = read(); // 참가자들의 음의 개수 
		int A = read(); // 고음의 첫항
		int D = read(); // 공차
		int X = 0;
		int num;
		for(int i=0; i<N; i++) 
		{
			num = read();
			if(num == A)
			{
				A+=D;
				X++;
			}
		}
		System.out.print(X);
	}
}