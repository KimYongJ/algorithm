//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/31575
import java.util.ArrayDeque;
class Node{
	int y, x; Node(int y, int x){this.y=y; this.x=x;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		final int dxy[][] = {{0,1},{1,0}};
		int X		= read();
		int Y		= read();
		int map[][] = new int[Y + 2][X + 2];
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				map[y][x] = read();

		ArrayDeque<Node> q = new ArrayDeque<>();
		q.add(new Node(1,1));
		map[1][1] = 0;
		while(!q.isEmpty())
		{
			Node now = q.poll();
			if(now.y==Y && now.x==X)
			{
				System.out.print("Yes");
				return;
			}
			for(int xy[] : dxy)
			{
				int nextY = now.y + xy[0];
				int nextX = now.x + xy[1];
				if(map[nextY][nextX] == 1)
				{
					map[nextY][nextX] = 0;
					q.add(new Node(nextY, nextX));
				}
			}
		}
		System.out.print("No");
	}
}