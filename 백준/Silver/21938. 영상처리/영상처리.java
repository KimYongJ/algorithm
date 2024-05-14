// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	static final int 	dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int 			T, nextY, nextX, map[][];
	
	public static void DFS(int y, int x) {
		for(int xy[] : dxy) {
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(map[nextY][nextX] >= T) {
				map[nextY][nextX] = 0;
				DFS(nextY, nextX);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r,g,b,cnt=0;
		int Y 	= Integer.parseInt(st.nextToken());
		int X 	= Integer.parseInt(st.nextToken());
		map 	= new int[Y+2][X+2];
		
		
		for(int y=1; y<=Y; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++) {
				r = Integer.parseInt(st.nextToken());
				g = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				map[y][x] = (r+g+b) / 3;
			}
		}
		T = Integer.parseInt(br.readLine());
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(map[y][x] >= T) 
				{
					cnt ++;
					map[y][x] = 0;
					DFS(y,x);
				}

		System.out.print(cnt);
	}
}