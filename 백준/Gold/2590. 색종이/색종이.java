// https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int size1 = read();
		int size2 = read() * 4;
		int size3 = read();
		int size4 = read();
		int size5 = read();
		int res	  = read() + size5 + size4;
		size1 -= size5 * 11; // 5cm의 여백을 1에서 뺀다.
		size2 -= 20 * size4;// 4cm 종이의 여백을 구한다.

		res += Math.ceil(size3/4.0); // 3cm가 들어갈 곳 결과에 더함
		
		int remain = 4 - ((size3 % 4)); // 비어있는 3cm의 칸을 구함
		if(remain == 1) {
			size2 -= 4;
			size1 -= 5;
		}
		else if(remain == 2) {
			size2 -= 12;
			size1 -= 6;
		}
		else if(remain == 3) {
			size2 -= 20;
			size1 -= 7;
		}
			
		if(size2 < 0) {
			size1 += size2;
		}
		else if(size2 > 0){
			res += Math.ceil(size2 / 36.0);
			if(size2 % 36 != 0) {
				size1 -= 36 - (size2 % 36);
			}
		}
		
		if(size1 > 0) {
			res += Math.ceil(size1 / 36.0);
		}
		
		System.out.print(res);
	}
}