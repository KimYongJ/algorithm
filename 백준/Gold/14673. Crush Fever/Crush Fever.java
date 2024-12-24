//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14673
class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int max;
	static int Y, X;
	static int map[][];
	
	public static void main(String[] args)throws Exception{
		X	= read();
		Y	= read();
		map	= new int[Y+2][X+2];
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				map[y][x] = read();
		
		back(0, 0, map);
		
		System.out.print(max);
	}
	public static void back(int depth, int score, int map[][]) {
		max = Math.max(max, score);
		
		if(depth == 3)
			return;
		
		boolean visit[][] = new boolean[Y+2][X+2];
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(map[y][x] != 0 && !visit[y][x])
				{
					int nextMap[][] = copy(map);
					int cnt = delete(nextMap, visit, y, x, nextMap[y][x]);
					
					back(depth + 1, score + cnt*cnt, down(nextMap));
				}
	}
	public static int delete(int map[][], boolean visit[][], int y, int x, int flag) {
		int cnt = 1;
		map[y][x] = 0;
		visit[y][x] = true;
		for(int xy[] : dxy)
		{
			int nextY = y + xy[0];
			int nextX = x + xy[1];
			if(map[nextY][nextX] == flag)
				cnt += delete(map, visit, nextY, nextX, flag);
		}
		return cnt;
	}
	public static int[][] copy(int origin[][]){
		int map[][] = new int[Y+2][X+2];
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				map[y][x] = origin[y][x];
		return map;
	}
	public static int[][] down(int map[][]){
		for(int y=Y; y>=2; y--)
			for(int x=1; x<=X; x++)
			{
				if(map[y][x] == 0)
				{
					int nextY = y;
					while(0 < nextY && map[nextY][x] == 0)
						--nextY;
					
					map[y][x] = map[nextY][x];
					map[nextY][x] = 0;
				}
			}
		return map;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}