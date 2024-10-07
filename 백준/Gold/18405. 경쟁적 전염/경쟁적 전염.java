//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/18405
import java.util.ArrayDeque;
import java.util.ArrayList;

class Node{
	int y, x;
	Node(int y, int x){this.y=y; this.x=x;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
		int N		= read();	// 맵크기(1<=200)
		int K		= read();	// 1~K까지 바이러스 종류(1<=1,000)
		int map[][]	= new int[N+2][N+2];
		
		ArrayList<Node> list[] = new ArrayList[K+1];
		
		for(int i=1; i<=K; i++)
			list[i] = new ArrayList<>();
		
		for(int i=0; i<N+2; i++)
			map[0][i] = map[N+1][i] = map[i][0] = map[i][N+1] = 1001;
		
		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
			{
				map[y][x] = read();
				if(map[y][x] != 0)
					list[map[y][x]].add(new Node(y,x));
			}
		
		ArrayDeque<Node> q = new ArrayDeque<>();
		for(int k=1; k<=K; k++)
			for(Node now : list[k])
				q.add(now);
		
		int T = read();
		int Y = read();
		int X = read();
		while(T-->0)
		{
			int size = q.size();
			while(size-- >0)
			{
				Node now = q.poll();
				
				for(int xy[] : dxy)
				{
					int nextY = now.y + xy[0];
					int nextX = now.x + xy[1];
					if(map[nextY][nextX] == 0)
					{
						map[nextY][nextX] = map[now.y][now.x];
						q.add(new Node(nextY, nextX));
					}
				}
				
			}
		}
		
		System.out.print(map[Y][X]);
	}
}