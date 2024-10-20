//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10711
import java.util.ArrayDeque;

class Node{int y, x; Node(int y, int x){this.y=y; this.x=x;}}

class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		final int dxy[][]	= {{1,0},{0,1},{-1,0},{0,-1},{-1,-1},{-1,1},{1,-1},{1,1}};
		int Y				= read();
		int X				= read();
		int map[][]			= new int[Y][X];
		ArrayDeque<Node> q	= new ArrayDeque<>();
		
		for(int y=0; y<Y; y++)
		{
			for(int x=0; x<X; x++)
			{
				char c = (char)System.in.read();
				if(c != '.')
					map[y][x] = c - '0';
				else 
					q.add(new Node(y,x));
			}
			System.in.read();
		}
		
		int T = -1;
		
		while(!q.isEmpty())
		{
			int size = q.size();
			while(size-->0)
			{
				Node now = q.poll();
				for(int xy[] : dxy)
				{
					int nextY = now.y + xy[0];
					int nextX = now.x + xy[1];
					if(0<=nextY && 0<=nextX && nextY<Y && nextX<X 
							&& map[nextY][nextX] != 0 && --map[nextY][nextX] == 0)
							q.add(new Node(nextY, nextX));
				}
			}
			++T;
		}
		
		System.out.print(T);
	}

}