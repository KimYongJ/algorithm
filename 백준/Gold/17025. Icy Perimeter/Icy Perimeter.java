//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17025
import java.util.ArrayDeque;
class Node{
	int y, x;Node(int y, int x){this.y=y; this.x=x;}
}
class Main{
	
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int N, area, maxArea, perimeter, minPerimeter;
	static boolean[][] map, visit;

	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void BFS(int y, int x) {
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.add(new Node(y, x));
		while(!q.isEmpty()) {
			Node now = q.poll();
			if(visit[now.y][now.x])
				continue;
			
			visit[now.y][now.x] = true;
			++area;
			
			for(int xy[] : dxy)
			{
				int nextY = now.y + xy[0];
				int nextX = now.x + xy[1];
				if(!map[nextY][nextX])
					++perimeter;
				else if(map[nextY][nextX] && !visit[nextY][nextX])
					q.add(new Node(nextY, nextX));
			}
		}
	}
	
	public static void main(String[] args)throws Exception{
		N				= read();
		map				= new boolean[N+2][N+2];
		visit			= new boolean[N+2][N+2];
		minPerimeter	= Integer.MAX_VALUE;
		
		for(int y=1; y<=N; y++)
		{
			for(int x=1; x<=N; x++)
				map[y][x] = System.in.read()=='#';
			System.in.read();
		}
		
		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
				if(map[y][x] && !visit[y][x])
				{
					area		= 0;
					perimeter	= 0;
					BFS(y, x);
					if(maxArea < area)
					{
						maxArea		= area;
						minPerimeter= perimeter;
					}
					else if(maxArea == area && perimeter < minPerimeter)
						minPerimeter = perimeter;
				}
		System.out.print(new StringBuilder().append(maxArea).append(' ').append(minPerimeter));
	}
}