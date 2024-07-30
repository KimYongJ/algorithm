// https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N = read(); // 초기 물병개수
		int K = read(); // 목표 물병 개수
		int bit = Integer.lowestOneBit(N);
		int res = 0;
		
		if(K >= N) // 특정 조건 조기종료
		{
			System.out.print(0);
			return;
		}

		while(Integer.bitCount(N) > K) 
		{
			if((N&bit)>0) 
			{
				N+=bit;
				res+=bit;
			}
			bit<<=1;
		}
		System.out.print(res);
	}
}