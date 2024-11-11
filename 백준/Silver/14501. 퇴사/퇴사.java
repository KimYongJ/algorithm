//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14501
class Main{
	
	static int max;
	static int N, T[], P[];
	
	public static void main(String[] args)throws Exception{
		N = read();
		T = new int[N + 1];
		P = new int[N + 1];
		
		for(int i=1; i<=N; i++)
		{
			T[i] = read();	// 걸리는 기간
			P[i] = read();	// 보상
		}
		
		bruteforce(1, 0);
		
		System.out.print(max);
	}
	public static void bruteforce(int day, int sum) {
		if(N < day)
		{
			max = Math.max(max, sum);
			return;
		}
		bruteforce(day+1, sum);			// 해당 날짜에 일을 안하는 경우
		if(day + T[day] - 1 <= N)		// 일을 하는경우는 무조건 마치는 날이 N일 이하여야 한다.
			bruteforce(day + T[day], sum + P[day]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}