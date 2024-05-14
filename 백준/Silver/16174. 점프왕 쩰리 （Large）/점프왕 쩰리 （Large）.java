// https://github.com/kimyongj/algorithm
class Main{
	
	static final int 	dxy[][] = {{1,0},{0,1}};
	static int 			N, nextY, nextX, map[][];
	static boolean 		visit[][];
	public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
	public static boolean DFS(int y, int x, int move) {
		if(map[y][x] == -1)	return true;
		if(visit[y][x])		return false;
		
		visit[y][x] = true;
		
		for(int xy[] : dxy) 
		{
			nextY = y + (xy[0]*move);
			nextX = x + (xy[1]*move);
			if(nextY>=0 && nextX>=0 && nextY<N && nextX<N 
				&& !visit[nextY][nextX] && DFS(nextY, nextX, map[nextY][nextX]))
				return true;
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		map 	= new int[N][N];
		visit 	= new boolean[N][N];
		
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				map[i][j] = read();

		System.out.print( DFS( 0, 0, map[0][0] ) ? "HaruHaru" : "Hing");
	}
}