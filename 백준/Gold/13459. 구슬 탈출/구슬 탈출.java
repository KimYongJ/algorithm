//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/13459
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

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
		{
			if(now.node1.x >= now.node2.x)
				return flag ? now.node1 : now.node2;
			return flag ? now.node2 : now.node1;
		}
		else if(idx == 1)	// 남
		{
			if(now.node1.y >= now.node2.y)
				return flag ? now.node1 : now.node2;
			return flag ? now.node2 : now.node1;
		}
		else if(idx == 2)	// 서
		{
			if(now.node1.x <= now.node2.x)
				return flag ? now.node1 : now.node2;
			return flag ? now.node2 : now.node1;
		}
		else				// 북
		{
			if(now.node1.y <= now.node2.y)
				return flag ? now.node1 : now.node2;
			return flag ? now.node2 : now.node1;
		}
	}
	public static void main(String[] args)throws Exception{
		final int dxy[][] = {{0,1},{1,0},{0,-1},{-1,0}};
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y			= Integer.parseInt(st.nextToken());
		int X			= Integer.parseInt(st.nextToken());
		Node red		= null;
		Node blue		= null;
		goal			= null;
		map				= new char[Y+1][X];
		
		for(int y=0; y<Y; y++)
		{
			String str = br.readLine();
			for(int x=0; x<X; x++)
			{
				char c = str.charAt(x);
				if(c == '#')
					map[y][x] = c;
				else
				{
					map[y][x] = '.';
					if(c == 'O')
					{
						goal = new Node(y,x, false);
						map[y][x] = 'O';
					}
					else if(c == 'R')
						red = new Node(y,x, true);
					else if(c == 'B')
						blue = new Node(y,x, false);
				
				}
			}
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
					Node n1 = getNode(now, i, true);
					Node n2 = getNode(now, i, false);
					Node nextN1 = getNextPosition(n1, dxy[i],null);
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
					if( !(n1.y == nextN1.y && n1.x == nextN1.x &&n2.y == nextN2.y && n2.x == nextN2.x) )
						q.add(new Position(nextN1, nextN2));
				}
			}
		}
		System.out.print(0);
	}
}