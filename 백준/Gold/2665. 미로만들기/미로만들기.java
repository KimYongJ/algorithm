//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2665
import java.util.ArrayDeque;
class Node{
	int y, x, cnt;
	Node(int y, int x, int cnt){this.y=y; this.x=x; this.cnt=cnt;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		final int dxy[][]	= {{1,0},{0,1},{-1,0},{0,-1}};
		int N				= read();
		int map[][]			= new int[N][N];
		
		for(int y=0; y<N; y++)
		{
			for(int x=0; x<N; x++)
				map[y][x] = (char)System.in.read() - '0';
			System.in.read();
		}
		
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.add(new Node(0, 0, 0));
		map[0][0] = 2;
		
		while(!q.isEmpty())
		{
			Node now = q.poll();
			
			if(now.y + 1 == N && now.y == now.x)
			{
				System.out.print(now.cnt);
				return;
			}
			
			for(int xy[] : dxy)
			{
				int nextY = now.y + xy[0];
				int nextX = now.x + xy[1];
				if(nextY<0 || nextX<0 || N<=nextY || N<=nextX)
					continue;
				if(map[nextY][nextX] == 1)
				{
					map[nextY][nextX] = 2;
					q.addFirst(new Node(nextY, nextX, now.cnt));
				}
				else if(map[nextY][nextX] == 0)
				{
					map[nextY][nextX] = 2;
					q.addLast(new Node(nextY, nextX, now.cnt+1));
				}
			}
		}
	}
}
