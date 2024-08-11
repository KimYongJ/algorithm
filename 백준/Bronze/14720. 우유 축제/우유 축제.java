//https://github.com/KimYongJ/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int cnt = 0;
		int flag= 0;
		int N	= read();
		while(N-->0) 
			if(read()==flag) 
			{
				cnt++;
				flag = (flag+1)%3;
			}
		
		System.out.print(cnt);
	}
}