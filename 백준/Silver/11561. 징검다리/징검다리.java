//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11561
class Main{
	static long read() throws Exception {// 빠른 입력을 위한 함수
		long c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = (int)read();
		while(T-- > 0)
		{
			long N	= read();
			long s	= 1;
			long e	= (long)Math.sqrt(N<<=1);
			long res= 0;
			
			while(s <= e)
			{
				long mid = (s + e) / 2;
				if(mid * (mid + 1) <= N) // 등차 수열공식 n(n+1)/2 간소화 후 연산진행
				{
					s = mid + 1;
					res = mid;
				}
				else
					e = mid - 1;
			}
			sb.append(res).append('\n');
		}
		System.out.print(sb.toString());
	}
}