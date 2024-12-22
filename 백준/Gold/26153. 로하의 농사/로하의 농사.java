//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/26153
class Main{
	
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, MAX;
	static int map[][];
	static boolean visit[][];
	
	public static void main(String[] args)throws Exception{
		Y		= read();
		X		= read();
		map		= new int[Y][X];
		visit	= new boolean[Y][X];
		
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++)
				map[y][x] = read();

		int sy = read();
		int se = read();
		int cnt= read();
		
		MAX = map[sy][se];
		
		if(cnt != 0)
		{
			visit[sy][se] = true;
			for(int i=0; i<4; i++)
			{
				int ny = sy + dxy[i][0];
				int nx = se + dxy[i][1];
				if(0<=ny && 0<=nx && ny<Y && nx<X && !visit[ny][nx])
				{
					visit[ny][nx] = true;
					back(ny, nx, cnt - 1, map[sy][se] + map[ny][nx], i);
					visit[ny][nx] = false;
				}
			}
		}
		System.out.print(MAX);
	}
	public static void back(int sy, int se, int cnt, int sum, int prevDir) {
		if(MAX < sum)
			MAX = sum;
		
		for(int i=0; i<4; i++)
		{
			int ny = sy + dxy[i][0];
			int nx = se + dxy[i][1];
			if(0<=ny && 0<=nx && ny<Y && nx<X && !visit[ny][nx])
			{
				int minus = prevDir == i ? 1 : 2;
				if(minus <= cnt) {
					visit[ny][nx] = true;
					back(ny, nx, cnt - minus, sum + map[ny][nx], i);
					visit[ny][nx] = false;
				}
			}
		}
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}