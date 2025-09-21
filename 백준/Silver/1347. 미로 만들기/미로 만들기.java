//https://www.acmicpc.net/problem/1347
//2초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	public static void main(String[] args)throws Exception{
		final int dxy[][] = {{1,0},{0,-1},{-1,0},{0,1}};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();

		boolean map[][] = new boolean[102][102];
		int minY = 50, minX = 50, maxY = 50, maxX = 50;
		int y = 50, x = 50;
		int dir = 0;
		map[y][x] = true;
		
		for(char c : br.readLine().toCharArray())
		{
			if(c == 'L')
			{
				dir = dir == 0 ? 3 : dir-1;
				continue;
			}
			
			if(c == 'R')
			{
				dir = (dir + 1)%4;
				continue;
			}
			
			int ny = y + dxy[dir][0];
			int nx = x + dxy[dir][1];
			map[ny][nx] = true;
			y = ny;
			x = nx;
			minY = Math.min(minY, y);
			minX = Math.min(minX, x);
			maxY = Math.max(maxY, y);
			maxX = Math.max(maxX, x);
		}
		
		StringBuilder sb = new StringBuilder();
		for(y=minY; y<=maxY; y++)
		{
			for(x=minX; x<=maxX; x++)
				sb.append(map[y][x] ? '.' : '#');
			sb.append('\n');
		}
		
		System.out.print(sb);
	}
}