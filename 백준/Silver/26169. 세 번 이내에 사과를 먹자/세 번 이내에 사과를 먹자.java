// https://github.com/kimyongj/algorithm
class Main{
	static final int 	MAX = 5;
	static final int 	dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int 			sy, sx, map[][];
	
	public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
	
	public static boolean backtracking(int depth, int y, int x, int cnt) {
		if(cnt >= 2)	return true;
		if(depth == 3) 	return false;
		
		int ny, nx, tmp;
		for(int xy[] : dxy) 
		{
			ny = y + xy[0];
			nx = x + xy[1];
			tmp= map[ny][nx];
			if(tmp != -1) 
			{
				map[ny][nx] = -1;
				if(backtracking(depth + 1, ny, nx, cnt + tmp)) return true;
				map[ny][nx] = tmp;
			}
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		map = new int[MAX+2][MAX+2];
		
		for(int i=0; i<7; i++)
			map[i][0] = map[0][i] = map[i][6] = map[6][i] = -1;// 외부를 벽으로 막음
		
		for(int y=1; y<=MAX; y++) 
			for(int x=1; x<=MAX; x++)
				map[y][x] = read();	

		sy = read()+1;
		sx = read()+1;
		
		int cnt = map[sy][sx] == 1 ? 1 : 0;
		map[sy][sx] = -1;
		System.out.print(backtracking(0,sy,sx,cnt) ? 1 : 0);
	}
}