// https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N = read(); // 끊어진 기타 개수
		int M = read(); // 기타 브랜드 수
		int one_min = Integer.MAX_VALUE;
		int six_min = Integer.MAX_VALUE;
		
		while(M-->0) 
		{
			int six = read();
			int one = read();
			
			six = Math.min(six, one * 6);
			
			if(one_min > one) {
				one_min = one;
			}
			if(six_min > six) {
				six_min = six;
			}
		}
		
		int res = (N/6 * six_min);
		int mod = N % 6;
		if(mod != 0) // N개보다 더 많이 살때(세트를구매할때)가 더 싼경우를 위한 분기
		{
			res += Math.min((mod * one_min), six_min);
		}
		
		System.out.print(res);
	}
}
