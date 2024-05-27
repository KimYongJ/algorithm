// https://github.com/kimyongj/algorithm

class Main{
	
	static int N, N2, M, K, map[];
	static int MAX;

	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void comb(int depth,int idx, int bit) {
		if(depth == 0) 
		{
			int cnt = 0;
			for(int m : map)
				if((m & bit) == m)
					cnt++;
			MAX = Math.max(MAX, cnt);
			return;
		}
		
		for(int i=idx; i<=N2; i++)
			comb(depth - 1, i + 1, bit | (1<<i));
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		M 		= read();
		K 		= read();
		N2 		= N*2;
		map 	= new int[M];
		
		for(int i=0; i<M; i++) 
			for(int k=0; k<K; k++)
				map[i] |=  (1<<read());
		
		comb(N, 1, 0);// 2n개중 n개를 조합한다.
		
		System.out.print(MAX);
	}
}