//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14620
class Main{
	
	static boolean	visit[][];
	static int		N, LEN, map[][];
	static int		result = 1<<30;
	
	public static void main(String[] args)throws Exception{
		N		= read();
		LEN		= N*N;
		map		= new int[N][N];
		visit	= new boolean[N][N];
		
		for(int y=0; y<N; y++)
			for(int x=0; x<N; x++)
				map[y][x] = read();
		
		bruteforce(0, 0, 0);
		
		System.out.print(result);
	}
	public static void bruteforce(int depth, int idx, int sum) {
		if(depth == 3)
		{
			result = Math.min(result, sum);
			return;
		}
		
		if(result < sum)
			return;
		
		for(int i=idx; i<LEN; i++)
		{
			int y = i / N;
			int x = i % N;
			
			if(y==0 || N-1==y || x==0 || N-1==x)
				continue;
			
			if(!visit[y][x+1] && !visit[y][x] && !visit[y][x-1] && !visit[y+1][x] && !visit[y-1][x])
			{
				visit[y][x+1] = visit[y][x] = visit[y][x-1] = visit[y+1][x] = visit[y-1][x] = true;
				bruteforce(depth + 1, i + 1, sum + map[y][x+1] + map[y][x] + map[y][x-1] + map[y+1][x] + map[y-1][x]);
				visit[y][x+1] = visit[y][x] = visit[y][x-1] = visit[y+1][x] = visit[y-1][x] = false;
			}
		}

	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}