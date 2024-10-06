//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/17836

import java.util.ArrayDeque;

class Node{
	int y, x, t, g;
	Node(int y, int x, int t, int g){
		this.y=y; this.x=x; this.t=t; this.g=g;
	}
}
class Main{
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		final int dxy[][]	= {{1,0},{0,1},{-1,0},{0,-1}};
		int Y				= read();	// 3<=100
		int X				= read();	// 3<=100
		int T				= read();	// 제한시간 1<=만
		int map[][]			= new int[Y+2][X+2];
		boolean visit[][][]	= new boolean[2][Y+2][X+2];
		
		for(int y=0; y<=Y+1; y++)
			map[y][0] = map[y][X+1] = -1;			// 패딩
		for(int x=0; x<=X+1; x++)
			map[0][x] = map[Y+1][x] = -1;			// 패딩
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				map[y][x] = read();
		
		ArrayDeque<Node> q = new ArrayDeque<>();
		
		int g = map[1][1] == 2 ? 1 : 0;
		visit[g][1][1] = true;
		q.add(new Node(1, 1, 0, g));
		
		while(!q.isEmpty())
		{
			Node now = q.poll();
			
			if(now.y == Y && now.x == X && now.t <= T)
			{
				System.out.print(now.t);
				return;
			}
			
			for(int xy[] : dxy)
			{
				int nextY = now.y + xy[0];
				int nextX = now.x + xy[1];
				int nextT = now.t + 1;
				int nextG = now.g;
				if(map[nextY][nextX] < 0 || visit[now.g][nextY][nextX] || T < nextT)
					continue;
				
				if(map[nextY][nextX] == 1 && now.g == 0)
					continue;

				if(map[nextY][nextX] == 2)
					nextG = 1;
				
				visit[nextG][nextY][nextX] = true;
				q.add(new Node(nextY, nextX, nextT, nextG));
				
			}
			
		}

		System.out.print("Fail");
	}
}