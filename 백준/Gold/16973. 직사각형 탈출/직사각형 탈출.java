//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16973
import java.util.ArrayDeque;

class Point{
	int y, x, cnt;
	Point(int y, int x, int cnt){this.y=y; this.x=x; this.cnt=cnt;}
}
class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};// 하, 우, 상, 좌
	static int res = -1;
	static int Y, X, H, W, sy, sx, ey, ex, map[][];
	static boolean visit[][];
	
	public static void main(String[] args)throws Exception{
		Y		= read();
		X		= read();
		map		= new int[Y+2][X+2];
		visit	= new boolean[Y+2][X+2];
		
		for(int y=0; y<Y+2; y++)
			visit[y][0] = visit[y][X+1] = true;
		for(int x=0; x<X+2; x++)
			visit[0][x] = visit[Y+1][x] = true;
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				map[y][x] = read() + map[y][x-1] + map[y-1][x] - map[y-1][x-1];

		H	= read() - 1;
		W	= read() - 1;
		sy	= read();
		sx	= read();
		ey	= read();
		ex	= read();
		
		ArrayDeque<Point> q = new ArrayDeque<>();
		visit[sy][sx] = true;
		q.add(new Point(sy, sx, 0));
		while(!q.isEmpty())
		{
			Point now = q.poll();
			if(now.y == ey && now.x == ex)
			{
				res = now.cnt;
				break;
			}
			for(int xy[] : dxy)
			{
				int nextY = now.y + xy[0];
				int nextX = now.x + xy[1];
				if(!visit[nextY][nextX])
				{
					int ny = nextY + H;
					int nx = nextX + W;
					if(ny <= Y && nx <= X)
					{
						int value = map[ny][nx] - map[ny][nextX-1] - map[nextY-1][nx] + map[nextY-1][nextX-1];
						if(value == 0)
						{
							visit[nextY][nextX] = true;
							q.add(new Point(nextY, nextX, now.cnt + 1));
						}
					}
					
				}
			}
		}
		System.out.print(res);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}