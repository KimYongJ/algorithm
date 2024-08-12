// https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();
		int coin	= 0;
		while(N >= 0)
			if(N % 5 == 0)
			{
				System.out.print(coin + N / 5);
				return;
			}
			else 
			{
				N-=2;
				coin++;
			}
		
		System.out.print(-1);
	}
}