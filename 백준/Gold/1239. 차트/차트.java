//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1239
class Main{
	
	static int CNT;
	static int N;
	static int [] origin, order;
	
	public static void main(String[] args)throws Exception{
		N		= read();
		origin	= new int[N];
		order	= new int[N];

		int max = 0;
		for(int i=0; i<N; i++)
			max = Math.max(max, origin[i] = read());
		
		if(max == 50)
			System.out.print(1);
		else if(50 < max)
			System.out.print(0);
		else
		{
			bruteforce(0,0);
			System.out.print(CNT / 2);
		}
	}
	public static void calc() {
		int cnt = 0;
		for(int i=0; i<N; i++)
		{
			int sum = 0;
			int idx = i;
			while(sum <= 50)
			{
				sum += order[idx];				
				idx = (idx + 1) % N;
				if(sum == 50)
					++cnt;
			}
		}
		CNT = Math.max(CNT, cnt);
	}
	public static void bruteforce(int depth, int bitmask) {
		if(depth == N)
		{
			calc();
			return;
		}
		for(int i=0; i<N; i++)
			if((bitmask & (1<<i)) == 0)
			{
				order[depth] = origin[i];
				bruteforce(depth + 1, bitmask | (1<<i));
			}
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}