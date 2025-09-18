//https://www.acmicpc.net/problem/5212
//1초 128MB
//3 10 // 세로(1<=10), 가로(1<=10)
//..........	// (.)은 바다, X는 땅
//..XXX.XXX.
//XXX.......
//상하좌우 바다가 인접한 곳이 3곳이상인곳은 제거 후 출력
//.XX...X
//XX.....

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y = Integer.parseInt(st.nextToken());// 세로(1<=10)
		int X = Integer.parseInt(st.nextToken());// 가로(1<=10)
		char map[][] = new char[Y][X];
		char map2[][] = new char[Y][X];
		
		for(int y=0; y<Y; y++)
		{
			String str = br.readLine();
			for(int x=0; x<X; x++)
			{
				map[y][x] = str.charAt(x);
				map2[y][x] = '.';
			}
		}
		
		int minY = Y;
		int minX = X;
		int maxY = 0;
		int maxX = 0;
		
		for(int y=0; y<Y; y++)
		{
			for(int x=0; x<X; x++)
			{
				if(map[y][x] == '.')
					continue;

				int sea = 0;
				
				for(int xy[] : dxy)
				{
					int ny = y + xy[0];
					int nx = x + xy[1];
					
					// 범위가 벗어나거나, 바다인경우 카운팅 증가
					if(ny<0 || nx<0 || Y<=ny || X<=nx || map[ny][nx] == '.')
						++sea;
				}
				if(sea < 3)
				{
					map2[y][x] = 'X';
					if(minY > y) minY = y;
					if(minX > x) minX = x;
					if(maxY < y) maxY = y;
					if(maxX < x) maxX = x;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int y=minY; y<=maxY; y++)
		{
			for(int x=minX; x<=maxX; x++)
			{
				sb.append(map2[y][x]);
			}
			sb.append('\n');
		}
		
		System.out.print(sb);
	}
}
