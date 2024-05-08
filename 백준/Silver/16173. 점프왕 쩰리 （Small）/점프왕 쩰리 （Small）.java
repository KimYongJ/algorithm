// https://github.com/kimyongj/algorithm

class Main{
	static int N, map[][];
	public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
	public static boolean DFS(int y, int x) {
		if(y >= N || x >= N || map[y][x] == 0) return false;
		if(map[y][x] == -1)	return true;
		return DFS(y + map[y][x], x) || DFS(y, x + map[y][x]);
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		map 	= new int[N][N];
		for(int y=0; y<N; y++)
			for(int x=0; x<N; x++)
				map[y][x] = read();
		
		System.out.println(DFS(0,0) ? "HaruHaru" : "Hing");
	}
}
