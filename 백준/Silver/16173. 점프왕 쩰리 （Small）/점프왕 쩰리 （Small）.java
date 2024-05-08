// https://github.com/kimyongj/algorithm

class Main{
	static int N, map[][];
	static boolean visit[][];
	public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
	public static void endCondition(int y, int x) {
		if(map[y][x]==-1) {
			System.out.println("HaruHaru");
			System.exit(0);
		}
	}
	public static void DFS(int y, int x) {
		if(y >= N || x >= N || visit[y][x]) return;
		visit[y][x] = true;
		endCondition(y,x);
		DFS(y + map[y][x], x);
		DFS(y, x + map[y][x]);
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		map 	= new int[N][N];
		visit 	= new boolean[N][N];
		for(int y=0; y<N; y++)
			for(int x=0; x<N; x++)
				map[y][x] = read();

		DFS(0,0);
		System.out.println("Hing");
	}
}
