//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/13459
import java.util.ArrayDeque;

class Node{
	int y, x;
	boolean isRed;
	Node(int y1,int x1, boolean bool){y=y1; x=x1; isRed = bool;}
}

class Position{
	Node node1, node2;
	Position(Node n1, Node n2){node1=n1; node2=n2;}
}

public class Main {

	static char map[][];
	static Node goal;
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static Node getNextPosition(Node now, int xy[], Node before)
	{
		int nextY = now.y;
		int nextX = now.x;
		while(
				map[nextY+xy[0]][nextX + xy[1]] != '#' &&
					( before == null || (before != null &&(nextY+xy[0] !=before.y || nextX+xy[1] != before.x)))
			  )
		{
			nextY += xy[0];
			nextX += xy[1];
			if(goal.y == nextY && goal.x == nextX)
				break;
		}
		return new Node(nextY, nextX, now.isRed);
	}
	public static Node getNode(Position now, int idx, boolean flag) {
		if(idx == 0)		// 동
			return (now.node1.x >= now.node2.x) ? (flag ? now.node1 : now.node2) : (flag ? now.node2 : now.node1);
		else if(idx == 1)	// 남
			return (now.node1.y >= now.node2.y) ? (flag ? now.node1 : now.node2) : (flag ? now.node2 : now.node1);
		else if(idx == 2)	// 서
			return (now.node1.x <= now.node2.x) ? (flag ? now.node1 : now.node2) : (flag ? now.node2 : now.node1);
		else				// 북
			return (now.node1.y <= now.node2.y) ? (flag ? now.node1 : now.node2) : (flag ? now.node2 : now.node1);
	}

	public static void main(String[] args)throws Exception{
		final int dxy[][] = {{0,1},{1,0},{0,-1},{-1,0}};
		int Y			= read();
		int X			= read();
		Node red		= null;
		Node blue		= null;
		goal			= null;
		map				= new char[Y][X];
		boolean visit[][][][] = new boolean[Y][X][Y][X];
		for(int y=0; y<Y; y++)
		{
			for(int x=0; x<X; x++)
			{
				map[y][x] = (char)System.in.read();
				if(map[y][x] != '#')
				{
					if(map[y][x] == 'O')
						goal = new Node(y,x, false);
					else if(map[y][x] == 'R'){
						red = new Node(y,x, true);
						map[y][x] = '.';
					}
					else if(map[y][x] == 'B'){
						blue = new Node(y,x, false);
						map[y][x] = '.';
					}
				
				}
			}
			System.in.read();
		}
		ArrayDeque<Position> q = new ArrayDeque<>();
		q.add(new Position(red, blue)); 
		int T = 10;
		while(T-->0)
		{
			int size = q.size();
			while(size-->0)
			{
				Position now = q.poll();
				// 동쪽(0)이면 x좌표가 큰것, 남(1)쪽이면 y좌표가 큰것, 서(2)쪽이면 x좌표가 작은것, 북(3)쪽이면 y좌표가 작은것
				for(int i=0; i<4; i++)
				{
					boolean redIn	= false;
					boolean blueIn	= false;
					Node n1			= getNode(now, i, true);
					Node n2			= getNode(now, i, false);
					Node nextN1		= getNextPosition(n1, dxy[i],null);
					Node nextN2;
					if(map[nextN1.y][nextN1.x] == 'O') {
						if(nextN1.isRed)redIn = true;
						else blueIn = true;
						nextN2 = getNextPosition(n2, dxy[i],null);
					}
					else
						nextN2 = getNextPosition(n2, dxy[i],nextN1);
					

					if(map[nextN2.y][nextN2.x] == 'O') {
						if(nextN2.isRed)redIn = true;
						else blueIn = true;
					}
					if(blueIn)
						continue;
					if(redIn)
					{
						System.out.print(1);
						return;
					}
					
					if(nextN1.isRed) {
						Node dummy = nextN1;
						nextN1 = nextN2;
						nextN2 = dummy;
					}
					if(!visit[nextN1.y][nextN1.x][nextN2.y][nextN2.x])
					{
						visit[nextN1.y][nextN1.x][nextN2.y][nextN2.x] = true;
						q.add(new Position(nextN1, nextN2));
					}
				}
			}
		}
		System.out.print(0);
	}
}