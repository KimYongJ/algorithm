//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1590
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N	= read();	// 버스의 개수(1<=50)
		int T	= read();	// 터미널에 도착하는시간 (1<=백만)
		int min = Integer.MAX_VALUE;
		for(int i=0; i<N; i++)
		{
			int S = read();	// 버스 시작 시각(1<=백만)
			int I = read();	// 간격(1<=만)
			int C = read();	// 대수(1<=백)
			while(C-->0)
			{
				if(T <= S)
				{
					min = Math.min(min,  S - T);
					break;
				}
				S += I;
			}
		}
		
		if(min == Integer.MAX_VALUE)
			min = -1;
		
		System.out.print(min);
	}
}