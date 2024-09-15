//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1166
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N = read();//1<=십억
		int L = read();//1<=십억
		int W = read();//1<=십억
		int H = read();//1<=십억

		double s = 0;
		double e = Math.min(Math.min(L,W),H);
		while(s < e)
		{
			double mid = (s + e) / 2;
			if(e == mid || s == mid)
				break;
			
			if((long)(L / mid) * (long)(W / mid) * (long)(H / mid) < N)
				e = mid;
			else
				s = mid;
		}
		System.out.print(s);
	}
}