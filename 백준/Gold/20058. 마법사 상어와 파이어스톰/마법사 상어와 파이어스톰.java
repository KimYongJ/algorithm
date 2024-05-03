// https://github.com/kimyongj/algorithm
class Main{
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int N, Q, L, area, sum, nextY, nextX, map[][];
	static boolean visit[][];
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static int DFS(int y, int x) {
		int cnt = 1;
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(nextY>=0 && nextX>=0 && nextY<N && nextX<N &&map[nextY][nextX] >0 && !visit[nextY][nextX]) 
			{
				visit[nextY][nextX] = true;
				sum += map[nextY][nextX];
				cnt += DFS(nextY,nextX);
			}
		}
		return cnt;
	}
	public static int[][] rotate() {
		int result[][] = new int[N][N];
		for(int y=0; y<N; y+=L)
			for(int x=0; x<N; x+=L)
				for(int i=0; i<L; i++)
					for(int j=0; j<L; j++)
						result[y+j][x+L-i-1] = map[y+i][x+j];
		return result;
	}
	public static int[][] melt(){
		int result[][] = new int[N][N], cnt;
		for(int y=0; y<N; y++)
			for(int x=0; x<N; x++) 
			{
				cnt = 0;
				for(int xy[] : dxy) 
				{
					nextY = y + xy[0];
					nextX = x + xy[1];
					if(nextY>=0 && nextX>=0 && nextY<N && nextX<N &&map[nextY][nextX] >0)
						cnt++;
				}
				result[y][x] = map[y][x];
				if(cnt <3)
					result[y][x] -= 1;
			}
		return result;
	}
	public static void main(String[] args)throws Exception{
		N 		= (int)Math.pow(2, read()); // 크기 
		Q 		= read(); // 횟수
		map 	= new int[N][N];
		visit 	= new boolean[N][N];
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				map[i][j] = read();

		for(int i=0; i<Q; i++) 
		{
			L 	= (int)Math.pow(2,read());
			map = rotate();
			map = melt();
		}
		
		for(int y=0; y<N; y++)
			for(int x=0; x<N; x++)
				if(!visit[y][x] && map[y][x] > 0) 
				{
					visit[y][x] = true;
					sum += map[y][x];
					area = Math.max(area,DFS(y,x));
				}

		System.out.println(new StringBuilder().append(sum).append('\n').append(area));
	}
}