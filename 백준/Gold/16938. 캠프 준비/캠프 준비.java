// https://github.com/kimyongj/algorithm

class Main{
	
	static int cnt;
	static int N, L, R, limit;
	static int arr[];

	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void comb(int depth, int idx, int sum, int min, int max) {
		if(N < depth) return ;
		
		if(L<=sum && sum<=R && max-min >= limit)
			cnt++;
		
		for(int i=idx; i<N; i++)
			comb(depth - 1, i+1, sum + arr[i], Math.min(min, arr[i]), Math.max(max, arr[i]));
			
	}

	public static void main(String[] args)throws Exception{
		N 		= read();
		L 		= read();
		R 		= read();
		limit 	= read();
		arr		= new int[N];
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		comb(0,0,0,Integer.MAX_VALUE, Integer.MIN_VALUE);
		
		System.out.print(cnt);
	}
}