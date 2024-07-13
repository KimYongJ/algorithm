//https://github.com/KimYongJ/algorithm
class Main{
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		int cnt = 0, before = 0, now = 0;
		int N = read();
		for(int i=0; i<N; i++)
		{
			now = read();
			if(now >= before) 
			{
				cnt++;
			}
			before = now;
		}
		System.out.print(cnt);
	}
}