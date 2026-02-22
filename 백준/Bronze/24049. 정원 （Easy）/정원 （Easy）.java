//https://www.acmicpc.net/problem/24049
//1초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int map[][] = new int[Y + 1][X + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int y=1; y<=Y; y++)
			map[y][0] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int x=1; x<=X; x++)
			map[0][x] = Integer.parseInt(st.nextToken());
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				map[y][x] = map[y-1][x] == map[y][x-1] ? 0 : 1;
		
		System.out.print(map[Y][X]);
	}
}