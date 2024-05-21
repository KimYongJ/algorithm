// https://github.com/kimyongj/algorithm
class Main{
	
	static int 		N, max, base[];
	static boolean 	visit[];

	public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
	
	public static void BACK(int depth,int beforeIdx, int sum) {
		if(depth == N) 
		{
			if(max < sum)
				max = sum;
			return;
		}
		for(int i=0; i<N; i++)
			if(!visit[i]) 
			{
				visit[i] = true;
				BACK(depth+1, i, sum + Math.abs(base[beforeIdx] - base[i]));
				visit[i] = false;
			}
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		max 	= Integer.MIN_VALUE;
		base 	= new int[N];
		visit	= new boolean[N];
		
		for(int i=0; i<N; i++)
			base[i] = read();
		
		for(int i=0; i<N; i++) 
		{
			visit[i] = true;
			BACK(1, i, 0);
			visit[i] = false;
		}
		
		
		System.out.print(max);
	}	
}