//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/3896
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb	= new StringBuilder();
		int prime[]			= new int[100001];
		int len				= 1299710;
		int idx				= 0;
		boolean eratos[]	= new boolean[len];
		eratos[1]			= true;
		// 에라토스테네스의 체를 사용해 소수를 미리 마킹해놓음
		for(int i=2; i<len; i++)
		{
			if(!eratos[i])
			{
				prime[idx] = i;
				idx++;
				int start = i << 1;
				while(start < len)
				{
					eratos[start] = true;
					start += i;
				}
			}
		}
		
		int T = read();
		while(T-- > 0)
		{
			int n = read();
			if(!eratos[n])
				sb.append(0);
			else
			{
				int s = 0;
				int e = 100000;
				while(s < e)
				{
					int mid = (s + e) / 2;
					
					if(prime[mid] < n)
						s = mid + 1;
					else
						e = mid;
				}
				sb.append(prime[e] - prime[e-1]);
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}