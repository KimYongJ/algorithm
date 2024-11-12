//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1747
class Main{
	public static void main(String[] args)throws Exception{
		final int MAX = 1_003_002;
		boolean prime[] = new boolean[MAX]; // 소수면 false
		prime[0] = prime[1] = true;
		for(int i=2; i<MAX; i++)
			if(!prime[i])
			{
				for(int j=i<<1; j<MAX; j+=i)
					prime[j] = true;
			}

		for(int i=read(); i<MAX; i++)
			if(!prime[i] && isPal(i))
			{
				System.out.print(i);
				return;
			}
	}
	public static boolean isPal(int num) {
		String str = String.valueOf(num);
		int L = -1;
		int R = str.length();
		while(++L<--R)
			if(str.charAt(L) != str.charAt(R))
				return false;
		return true;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}