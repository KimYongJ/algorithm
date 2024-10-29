//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3482
// 가장 먼노드 응용 문제
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, map[][];
	static int maxY, maxX, maxDist;
	static boolean visit[][];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int y, int x, int dist) {
		if(maxDist < dist)
		{
			maxDist = dist;
			maxY = y;
			maxX = x;
		}
		for(int xy[] : dxy) {
			int nextY = y + xy[0];
			int nextX = x + xy[1];
			if(!visit[nextY][nextX] && map[nextY][nextX] == 0) {
				visit[nextY][nextX] = true;
				DFS(nextY, nextX, dist + 1);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0)
		{
			X		= read();
			Y		= read();
			maxY	= -1;
			maxX	= -1;
			maxDist = 0;
			map		= new int[Y][X];
			visit	= new boolean[Y][X];
			
			for(int y=0; y<Y; y++)
			{
				for(int x=0; x<X; x++)
				{
					if(System.in.read() == '#')
						map[y][x] = 1;	// 1은 바위, 0은 갈 수 있는 곳
				}
				System.in.read();
			}
			
			for(int y=0; y<Y; y++) 
				for(int x=0; x<X; x++) 
					if(map[y][x] == 0 && !visit[y][x])
					{
						if(maxY == -1)
						{
							maxY = y;
							maxX = x;
						}
						visit[y][x] = true;
						DFS(y, x, 0);
					}
			
			maxDist = 0;
			visit	= new boolean[Y][X];
			DFS(maxY, maxX, 0);
			sb.append("Maximum rope length is ").append(maxDist).append(".\n");
		}
		System.out.print(sb.toString());
	}
}