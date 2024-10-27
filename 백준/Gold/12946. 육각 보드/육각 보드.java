//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/12946

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1},{1,-1},{-1,1}};
	static int N, max;
	static int[][] color, map;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());
		map		= new int[N][N];
		color	= new int[N][N];

		for(int y=0; y<N; y++)
		{
			String str = br.readLine();
			for(int x=0; x<N; x++)
				map[y][x] = str.charAt(x) == 'X' ? 1 : 0;
		}
		
		for(int y=0; y<N && max != 3; y++)
			for(int x=0; x<N && max != 3; x++)
				if(map[y][x] == 1 && color[y][x] == 0)
					DFS(y, x, 1);

		System.out.print(max);
	}
	public static void DFS(int y, int x, int col) {
		if(max == 3)
			return;
		max = Math.max(color[y][x] = col, max);
		
		for(int xy[] : dxy)
		{
			int nextY = xy[0] + y;
			int nextX = xy[1] + x;
			if(0<=nextY && 0<=nextX && nextY < N && nextX < N && map[nextY][nextX] == 1)
			{
				if(color[nextY][nextX] == 0)
					DFS(nextY, nextX, col == 1 ? 2 : 1);
				else if(color[y][x] == color[nextY][nextX])
				{
					max = 3;
					return;
				}
			}
		}
	}
}