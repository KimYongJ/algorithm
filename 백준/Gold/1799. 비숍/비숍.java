// https://github.com/KimYongJ/algorithm
// 비숍을 놓을 수 있는곳 : 1(true), 없으면 : 0(false)

class Main{
	static int dxy[][] = {{1,-1},{1,1},{-1,-1},{-1,1}};
	static int N, result[], color[][];
	static boolean visit[][], map[][];
    static int read() throws Exception {// 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static boolean check(int y, int x) {
		int nextY, nextX;
		for(int xy[] : dxy) {
			nextY = y + xy[0];
			nextX = x + xy[1];
			while(nextY>=0 && nextX>=0 && nextY<N && nextX<N) {
				if(visit[nextY][nextX])
					return false;
				nextY += xy[0];
				nextX += xy[1];
			}
		}
		return true;
	}
	public static void BackTracking(int chesscolor, int y, int x, int cnt) {
		if(N <= y) 
		{
			if(result[chesscolor] < cnt)
				result[chesscolor] = cnt;
			return;
		}
		int nextY = y;
		int nextX = x + 2;
		if(nextX >= N) 
		{
			nextY += 1;
			if(nextY < N)
				nextX = color[nextY][0] == chesscolor ? 0 : 1;
		}
		// 현재장소에서 둘 수 있는 경우
		if(map[y][x] && check(y, x)) 
		{
			visit[y][x] = true;
			BackTracking(chesscolor, nextY, nextX, cnt+1);
			visit[y][x] = false;
		}
		// 둘 수 없거나 안두는 경우
		BackTracking(chesscolor, nextY, nextX, cnt);
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		result 	= new int[2];
		color 	= new int[N][N];
		map 	= new boolean[N][N];
		visit 	= new boolean[N][N];
		for(int y=0; y<N; y++) 
			for(int x=0; x<N; x++) 
			{
				map[y][x] 	= read() == 1; 
				color[y][x] = (y+x)%2;		// 맵의 색깔을 입혀 좌표를 쉽게 구할 수 있도록한다.
			}
		
		BackTracking(0,0,0,0);
		BackTracking(1,0,1,0);
		System.out.println(result[0] + result[1]);
	}
}