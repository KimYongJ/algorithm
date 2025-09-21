//https://www.acmicpc.net/problem/1347
//2초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int dxy[][] = {{1,0},{0,-1},{-1,0},{0,1}};
		boolean map[][] = new boolean[102][102];
		int minY = 50;
		int minX = 50;
		int maxY = 50;
		int maxX = 50;
		int y = 50;
		int x = 50;
		int dir = 0;
		
		map[y][x] = true;
		
		br.readLine();
		for(char c : br.readLine().toCharArray())
		{
			if(c == 'L')
			{
				dir = (dir + 3) % 4;
				continue;
			}
			
			if(c == 'R')
			{
				dir = (dir + 1) % 4;
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