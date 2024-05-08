// https://github.com/kimyongj/algorithm
class Main{
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}, {-1,-1},{-1,1},{1,-1},{1,1}};
	static int Y, X, cnt, nextY, nextX;
	static boolean visit[][];
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void DFS(int y, int x) {
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(visit[nextY][nextX]) 
			{
				visit[nextY][nextX] = false;
				DFS(nextY, nextX);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		Y 		= read();
		X 		= read();
		visit 	= new boolean[Y+2][X+2];

		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				visit[y][x] = read() == 1;

		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(visit[y][x]) 
				{
					cnt++;
					visit[y][x] = false;
					DFS(y,x);
				}

		System.out.println(cnt);
	}
}