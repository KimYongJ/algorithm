//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2642
class Pos{
	int y, x, num;
	Pos(int y, int x, int n){this.y=y; this.x=x; num=n;}
}
class Main{
	
	static int dxy[][] = {{0,1},{0,-1},{1,0},{-1,0}};	// 순서 : 동서남북
	static int posIdx, map[][];
	static int face[];
	static Pos pos[];
	static boolean visit[][];
	
	public static void main(String[] args)throws Exception{
		map 	= new int[8][8];
		face	= new int[7];	// 자기와 마주보는 값
		pos		= new Pos[6];	// 전개도의 1~6까지 숫자의 위치를 담을 배열

		// 입력된 숫자가 0 이상인 경우 pos배열에 담음
		for(int y=1; y<=6; y++)
			for(int x=1; x<=6; x++)
				if((map[y][x] = read()) != 0)
					pos[posIdx++] = new Pos(y,x, map[y][x]);

		// 0이상의 숫자가 6개가 아닌 경우 0 출력
		if(posIdx != 6)
		{
			System.out.print(0);
			return;
		}

		for(Pos p : pos)
		{
			int faceCnt = 0;// p.y,p.x 좌표와 마주보는 면의 개수
			for(int i=0; i<4; i++)
			{
				int nextY = p.y + dxy[i][0];
				int nextX = p.x + dxy[i][1];
				// 인접한 곳이 있는 경우만 해당 방향으로DFS탐색
				if(map[nextY][nextX] == 0)	
					continue;
				
				visit = new boolean[8][8];
				visit[p.y][p.x] = true; 
				int faceNumber = DFS(p.y, p.x, i, 0);
				if(0<faceNumber)
				{
					face[p.num] = faceNumber;	// 자기와 마주보는 면을 입력
					++faceCnt;					// 자기와 마주보는 면개수 + 1
				}
			}
			// 자기와 마주보는 면이 1개 미만이나 초과일 경우 0출력
			if(faceCnt != 1)
			{
				System.out.print(0);
				return;
			}
		}
		System.out.print(face[1]);
	}
	public static int DFS(int y, int x, int dir, int depth) {
		if(depth == 2)
			return map[y][x];	// 같은 방향으로 2번간 곳의 숫자 반환(마주보는면)
		
		for(int i=0; i<4; i++)
		{
			int nextY = y + dxy[i][0];
			int nextX = x + dxy[i][1];
			if(!visit[nextY][nextX] && map[nextY][nextX] != 0)
			{
				visit[nextY][nextX] = true;
				int num = DFS(nextY, nextX, dir, depth + (dir==i ? 1 : 0));
				if(num!=0)
					return num;
			}
		}
		
		return 0;
	}
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}