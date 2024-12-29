//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/29700
class Main{
	public static void main(String[] args)throws Exception{
		int Y		= read();	// 세로1<=천
		int X		= read();	// 가로1<=오천
		int K		= read();	// 인원수 1<=10
		int res		= 0;
		for(int y=0; y<Y; y++)
		{
			int cnt = 0;
			for(int x=0; x<X; x++)
			{
				if(System.in.read()== '0')
				{
					if(K<= ++cnt)
						++res;
				}
				else cnt = 0;
			}
			System.in.read();// 개행 읽어없앰
		}
		
		System.out.print(res);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}