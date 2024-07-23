// https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0) 
		{
			int n = read();
			if(n == 1) 
			{
				sb.append(1).append('\n');
				continue;
			}
			int cnt = 0;
			int bef = 0;
			while(bef != n) 
			{
				bef = n;
				for(int i=9; i>=2; i--) 
				{
					if(n % i == 0) 
					{
						cnt++;
						n /= i;
						break;
					}
				}
			}
			sb.append(n>=10 ? -1 : cnt).append('\n');
		}
		System.out.print(sb.toString());
	}
}