//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15644

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Node{
	int y, x; 
	Node(int y, int x){this.y=y; this.x=x;}
}

class Pos{ 
	Node red, blue; 
	long dir;
	Pos(Node r, Node b, long d){red=r; blue = b; dir=d;}
}

class Main{
	
	static int dxy[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	static int res;
	static int Y, X, oy, ox;
	static long dir;// 1상 2하 3좌 4우 방향
	static char map[][];
	static Node red, blue;
	static boolean visit[][][][];
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y	= Integer.parseInt(st.nextToken());
		X	= Integer.parseInt(st.nextToken());
		map = new char[Y][X];
		res = -1;
		visit = new boolean[10][10][10][10];
		for(int y=0; y<Y; y++)
		{
			String str = br.readLine();
			for(int x=0; x<X; x++)
			{
				map[y][x] = str.charAt(x);
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
		}
		ArrayDeque<Pos> q = new ArrayDeque<>();
		visit[red.y][red.x][blue.y][blue.x] = true; 
		q.add(new Pos(red, blue, 0));
		
		int T = -1;
		Loop : 
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
					}else {
						blue= nextPosition(by, bx, 0, 0, i);
						if(blue.y == oy && blue.x == ox)
							red = nextPosition(ry, rx, 0, 0, i);
						else
							red = nextPosition(ry, rx, blue.y, blue.x, i);
					}
					
					long nextDir = addDir(T, i+1, now.dir);
					
					if(blue.y == oy && blue.x == ox)
						continue;
					if(red.y == oy && red.x == ox)
					{
						dir = nextDir;
						res = T+1;
						break Loop;
					}
					if(!visit[red.y][red.x][blue.y][blue.x])
					{
						visit[red.y][red.x][blue.y][blue.x] = true;
						q.add(new Pos(red, blue, nextDir));
					}
				}
			}
		}
		
		System.out.println(res);
		if(res != -1)
			System.out.print(getDir(dir));
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
	public static long addDir(int t, int flag, long dir) {
		if(t == 0)
			return flag;
		
		return dir + (long)Math.pow(10, t) * flag;
	}
	public static String getDir(long dir)
	{
		StringBuilder sb = new StringBuilder();
		while(dir != 0)
		{
			switch((int)(dir % 10))
			{
			case 1: sb.append('U');break;
			case 2: sb.append('D');break;
			case 3: sb.append('L');break;
			case 4: sb.append('R');break;
			}
			dir /= 10;
		}
		return sb.toString();
	}
}


