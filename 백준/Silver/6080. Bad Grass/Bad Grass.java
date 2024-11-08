//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/6080
import java.util.ArrayDeque;
class Main{
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1},{-1,-1},{-1,1},{1,-1},{1,1}};
		int Y		= read();
		int X		= read();
		int[][]map	= new int[Y+2][X+2];
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				map[y][x] = read();
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		int cnt = 0;
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(map[y][x] != 0)
				{
					++cnt;
					map[y][x] = 0;
					
					q.add(new int[] {y,x});
					while(!q.isEmpty())
					{
						int[] now = q.poll();
						for(int xy[] : dxy)
						{
							int nextY = now[0] + xy[0];
							int nextX = now[1] + xy[1];
							if(map[nextY][nextX] != 0)
							{
								map[nextY][nextX] = 0;
								q.add(new int[] {nextY, nextX});
							}
						}
					}
					
				}

		System.out.print(cnt);
	}
}