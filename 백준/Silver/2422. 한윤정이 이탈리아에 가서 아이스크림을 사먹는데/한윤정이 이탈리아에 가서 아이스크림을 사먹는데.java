//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2422
class Main{
	
	static boolean pair[][];
	static int ice[];
	static int N, M, cnt;
	
	public static void main(String[] args)throws Exception{
		N		= read();
		M		= read();
		ice		= new int[4];
		pair	= new boolean[N+1][N+1];
		
		while(M-->0)
		{
			int a = read();
			int b = read();
			pair[a][b] = pair[b][a] = true;
		}
		
		bruteforce(1, 1);
		
		System.out.print(cnt);
	}
	public static void bruteforce(int depth, int idx) {

		if(depth == 4)
		{
			if(!pair[ice[1]][ice[3]])
				++cnt;
			return;
		}
		for(int i=idx; i<=N; i++)
		{
			ice[depth] = i;
			if(!pair[ice[depth]][ice[depth-1]])
				bruteforce(depth + 1, i + 1);
		}
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}