// https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N = read(); // 박스 개수
		if(N == 0) 
		{
			System.out.print(0);
		}
		else 
		{
			int M	= read(); // 박스의 무게 최대치
			int idx	= 1;
			int m	= 0;
			int num;
			for(int i=0; i<N; i++) 
			{
				num = read();
				if(m + num <= M) 
				{
					m += num;
				}
				else 
				{
					idx ++;
					m = num;
				}
			}
			System.out.print(idx);
		}
	}
}