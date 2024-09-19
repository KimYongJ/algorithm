//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14786
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int A = read();	// 0<=십만
		int B = read();	// 0<=십만
		int C = read();	// 0<=십만
		
		double diff = 0.0000000009;
		double s	= 0;
		double e	= 100_001;
		double x	= 0;
		
		// Ax + Bsin(x) = C를 만족하는 X찾기
		while(e - s > diff)
		{
			double mid = (s + e) / 2;
			double cal = A * mid + B * Math.sin(mid);
			
			if(cal <= C)
			{
				x = mid;
				s = mid;
			}
			else
				e = mid;
		}
		System.out.print(x);
	}
}