//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/24463
class Main{
	
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X;
	static int[][] map, pos;
	static boolean[][] visit, check;
	
	public static void main(String[] args)throws Exception{
		Y 		= read();					// 3<=N,M<=2001(홀수)
		X		= read();					// 3<=N,M<=2001(홀수)
		map		= new int[Y+2][X+2];		// 지도
		pos		= new int[2][2];			// 시작, 종료 위치를 담을 배열
		visit	= new boolean[Y+2][X+2];	// DFS시 해당 위치를 방문 했는지 체크
		check	= new boolean[Y+2][X+2];	// 최단경로에 true를 해준다.
		
		// 값을 입력 받는다.
		for(int y=1; y<=Y; y++)
		{
			for(int x=1; x<=X; x++)
				map[y][x] = System.in.read();
			System.in.read();
		}
		// 시작 종료위치 확인
		int idx = 0;
		for(int y=1; y<=Y; y++)
			if(map[y][1] == '.')
			{
				pos[idx][0] = y;
				pos[idx++][1] = 1;
			}
			else if(map[y][X] == '.')
			{
				pos[idx][0] = y;
				pos[idx++][1] = X;
			}
		
		for(int x=1; x<=X; x++)
			if(map[1][x] == '.')
			{
				pos[idx][0] = 1;
				pos[idx++][1] = x;
			}
			else if(map[Y][x] == '.')
			{
				pos[idx][0] = Y;
				pos[idx++][1] = x;
			}
		
		// DFS를 돌면서 끝점을 만나면 check배열에 true를 하여 경로를 체크한다.
		check[pos[0][0]][pos[0][1]] = true;
		DFS(pos[0][0], pos[0][1]);
		
		StringBuilder sb = new StringBuilder();
		for(int y=1; y<=Y; y++)
		{
			for(int x=1; x<=X; x++)
				if(!check[y][x] && map[y][x] == '.')
					sb.append('@');
				else
					sb.append((char)map[y][x]);
			
			sb.append('\n');
		}
		
		System.out.print(sb.toString());
	}
	static boolean DFS(int y, int x) {
		if(y == pos[1][0] && x == pos[1][1])
			return check[y][x] = true;
		
		visit[y][x] = true;
		for(int xy[] : dxy)
		{
			int nextY = y + xy[0];
			int nextX = x + xy[1];
			if(!visit[nextY][nextX] && map[nextY][nextX] =='.')
			{
				if(DFS(nextY, nextX))
					return check[nextY][nextX] = true;
			}
		}
		return false;
	}
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}