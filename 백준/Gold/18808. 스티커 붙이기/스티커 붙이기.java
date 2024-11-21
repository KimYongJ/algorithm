//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/18808
class Main{
	
	static int cnt;
	static int y, x, Y, X, K;
	static int[][] map, arr;
	
	public static void main(String[] args)throws Exception{
		Y	= read();	// 세로 1<=40
		X	= read();	// 가로 1<=40
		K	= read();	// 스티커의 개수 1<=100
		map = new int[Y][X];
		
		while(K-->0)
		{
			y	= read();
			x	= read();
			arr = new int[y][x];
			
			for(int y1=0; y1<y; y1++)
				for(int x1=0; x1<x; x1++)
					arr[y1][x1] = read();
			
			int n = 4;
			while(n-->0)
			{
				if(scan())
					break;
				rotate();
			}
		}
		
		System.out.print(cnt);
	}
	public static void rotate() {
		int nextY		= x;
		int nextX		= y;
		int nextArr[][] = new int[nextY][nextX];
		for(int y1=0; y1<y; y1++)
			for(int x1=0; x1<x; x1++)
				nextArr[x1][y-y1-1] = arr[y1][x1];
		
		y	= nextY;
		x	= nextX;
		arr	= nextArr;
	}
	public static boolean scan() {
		int ylen = Y - y;
		int xlen = X - x;
		for(int y1=0; y1<=ylen; y1++)
		{
			LOOP:
			for(int x1=0; x1<=xlen; x1++)
			{
				for(int y2=0; y2<y; y2++)
					for(int x2=0; x2<x; x2++)
						if(arr[y2][x2] == 1 && map[y2+y1][x2+x1] == 1)
							continue LOOP;

				for(int y2=0; y2<y; y2++)
					for(int x2=0; x2<x; x2++)
						if(arr[y2][x2] == 1)
						{
							map[y2+y1][x2+x1] = 1;
							++cnt;
						}
				
				return true;
			}
		}
		return false;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}