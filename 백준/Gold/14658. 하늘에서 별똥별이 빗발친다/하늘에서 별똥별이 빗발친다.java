//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14658
class Main{
	
	static int[][] star;
	static int L, K;
	
	public static void main(String[] args)throws Exception{
		read();read();			// 안쓰는것
		L		= read();		// 트램펄린 한변길이(1<=십만)
		K		= read();		// 별똥별수(1<=백만)
		star	= new int[K][2];
		for(int i=0; i<K; i++)
		{
			star[i][0] = read();
			star[i][1] = read();
		}

		int res = 0;
		for(int i=0; i<K; i++)
			for(int j=0; j<K; j++)
				res = Math.max(res, get(star[i][0], star[j][1]));
		
		System.out.print(K - res);
	}
	public static int get(int x, int y) {
		int res = 0;
		
		for(int[] now : star)
			if(x<=now[0] && now[0]<=x+L && now[1]<=y && y-L<=now[1])
				++res;
		
		return res;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}