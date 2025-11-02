//https://www.acmicpc.net/problem/1730
//2초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		char map[][] = new char[N][N];
		
		for(int y=0; y<N; y++)
			for(int x=0; x<N; x++)
				map[y][x] = '.';
		
		int y = 0;
		int x = 0;
		
		for(char c : str.toCharArray())
		{
			int ny = y;
			int nx = x;
			
			if(c == 'U') ny--;
			else if(c == 'D') ny++;
			else if(c == 'R') nx++;
			else if(c == 'L') nx--;
			
			if(ny < 0 || nx < 0 || N <= ny || N <= nx)
				continue;
			
			char draw = c == 'R' || c == 'L' ? '-' : '|';
			
			if(map[y][x] == draw || map[y][x] == '.')
				map[y][x] = draw;
			else
				map[y][x] = '+';
			
			if(map[ny][nx] == draw || map[ny][nx] == '.')
				map[ny][nx] = draw;
			else
				map[ny][nx] = '+';
			
			y = ny;
			x = nx;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int y1=0; y1<N; y1++)
		{
			for(int x1=0; x1<N; x1++)
				sb.append(map[y1][x1]);
			sb.append('\n');
		}
		
		System.out.print(sb);
	}
}