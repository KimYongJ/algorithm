//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13460
import java.util.ArrayDeque;
class Node{
	int y, x; 
	Node(int y, int x){this.y=y; this.x=x;}
}

class Pos{ 
	Node red, blue; 
	long dir;
	Pos(Node r, Node b){red=r; blue = b;}
}

class Main{
	
	static int dxy[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	static int Y, X, oy, ox;
	static char map[][];
	static Node red, blue;
	static boolean visit[][][][];
	
	public static void main(String[] args)throws Exception{
		Y		= read();
		X		= read();
		map 	= new char[Y][X];
		visit	= new boolean[10][10][10][10];
		
		for(int y=0; y<Y; y++)
		{
			for(int x=0; x<X; x++)
			{
				map[y][x] = (char)System.in.read();
				if(map[y][x] == 'R')
				{
					map[y][x] = '.';
					red = new Node(y,x);
				}
				else if(map[y][x] == 'B')
				{
					map[y][x] = '.';
					blue = new Node(y,x);
				}
				else if(map[y][x] == 'O')
				{
					map[y][x] = '.';
					oy = y; ox = x;
				}
			}
			System.in.read();
		}
		
		ArrayDeque<Pos> q = new ArrayDeque<>();
		visit[red.y][red.x][blue.y][blue.x] = true; 
		q.add(new Pos(red, blue));
		
		int T = -1;
		while(++T < 10)
		{
			int size = q.size();
			while(size-->0)
			{
				Pos now = q.poll();
				int ry	= now.red.y;
				int rx	= now.red.x;
				int by	= now.blue.y;
				int bx	= now.blue.x;

				for(int i=0; i<4; i++)
				{
					boolean redFirst = check(ry, rx, by, bx, i);
					if(redFirst)
					{
						red = nextPosition(ry, rx, 0, 0, i);
						if(red.y == oy && red.x == ox)
							blue= nextPosition(by, bx, 0, 0, i);
						else
							blue= nextPosition(by, bx, red.y, red.x, i);
					}
					else
					{
						blue= nextPosition(by, bx, 0, 0, i);
						if(blue.y == oy && blue.x == ox)
							red = nextPosition(ry, rx, 0, 0, i);
						else
							red = nextPosition(ry, rx, blue.y, blue.x, i);
					}
					
					if(blue.y == oy && blue.x == ox)
						continue;
					if(red.y == oy && red.x == ox)
					{
						System.out.println(T+1);
						return;
					}
					if(!visit[red.y][red.x][blue.y][blue.x])
					{
						visit[red.y][red.x][blue.y][blue.x] = true;
						q.add(new Pos(red, blue));
					}
				}
			}
		}
		
		System.out.println(-1);
	}
	public static Node nextPosition(int y, int x, int y1, int x1, int dir) {
		while(!(y==oy && x==ox))
		{
			int nextY = y + dxy[dir][0];
			int nextX = x + dxy[dir][1];
			if(map[nextY][nextX] == '#' || (nextY==y1 && nextX == x1))
				break;
			y = nextY;
			x = nextX;
		}
		return new Node(y, x);
	}
	public static boolean check(int ry, int rx, int by, int bx, int flag) {
		if(flag == 0)	// 상
			return ry <= by;
		if(flag == 1)
			return ry >= by;
		if(flag == 2)
			return rx <= bx;
		else
			return rx >= bx;
	}
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}


