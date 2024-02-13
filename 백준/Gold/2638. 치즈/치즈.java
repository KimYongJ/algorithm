// https://github.com/KimYongJ/algorithm

import java.awt.Point;
import java.util.ArrayDeque;

class Main{
	static int N, M, TIME, map[][];
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean visit[][];
	static ArrayDeque<Point> q;
	// 빠른 입력을 위한 함수
	public static int read() throws Exception
	{ 			
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
	
	public static void DFS(int y, int x) { 
		visit[y][x] = true; // 방문 처리 
		
		for(int xy[] : dxy) 
		{
			int ny = y + xy[0];
			int nx = x + xy[1];
			if(ny>=0 && nx>=0 && ny<N && nx<M && !visit[ny][nx]) 
			{
				if(map[ny][nx] != 0) 				// 치즈가 있는 곳인 경우
				{ 									
					if(++map[ny][nx] == 3)			// 맞닿은 곳이 2번이상인 경우
						q.add(new Point(nx,ny)); 	// 녹은 치즈를 큐에 넣는다, 큐에 넣은 것을 다시 DFS함으로써 중복 탐색을 방지한다.
				}else DFS(ny,nx);					// 치즈가 아닌 경우 DFS탐색
			}
		}
	}
	public static void main(String[] args)throws Exception
	{
		N 		= read();
		M 		= read();
		map 	= new int[N][M]; 		// 치즈가 있으면 true 없으면 false
		visit 	= new boolean[N][M]; 	// 방문 배열 초기화
		q 		= new ArrayDeque<Point>() {{ add(new Point(0,0));}};
		
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				map[i][j] = read();

		while(true) {
			int size = q.size();		// 큐의 크기를 가져온다.
			for(int i=0; i<size; i++) 	// 큐의 크기만큼 DFS를 돈다.
			{
				Point p = q.poll();		// 큐에는 처음에 공기가담겨있으나 추후 녹은 치즈 위치만 담긴다.
				DFS(p.y, p.x);
			}		
			if(q.isEmpty())				// 큐가 비어있다는 것은 녹은 치즈가 없다는 것이기 때문에 종료
				break;
			else TIME++;
		}
		
		System.out.println(TIME);
		
	}
}