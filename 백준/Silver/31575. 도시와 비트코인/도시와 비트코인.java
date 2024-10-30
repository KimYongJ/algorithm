//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/31575
class Main{
	
	static final int dxy[][] = {{0,1},{1,0}};
	static int X, Y, map[][];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static boolean DFS(int y, int x) {
		if(Y==y && X== x)
			return true;
		for(int xy[] : dxy)
		{
			int nextY = y + xy[0];
			int nextX = x + xy[1];
			if(map[nextY][nextX] == 1)
			{
				map[nextY][nextX] = 0;
				if(DFS(nextY, nextX))
					return true;
			}
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		X	= read();
		Y	= read();
		map	= new int[Y + 2][X + 2];
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				map[y][x] = read();
		
		if(DFS(1,1))
			System.out.print("Yes");
		else
			System.out.print("No");
	}
}