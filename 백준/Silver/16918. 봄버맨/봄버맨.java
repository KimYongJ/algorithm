//https://www.acmicpc.net/problem/16918
//2초 512MB
//6 7 3// 행, 가로, 목표 시간( 모든 데이터 1<=200 )
//....... // O는 폭탄, 점은 빈칸
//...O...
//....O..
//.......
//OO.....
//OO.....
//N초 후 격자판의 상태 출력
//OOO.OOO
//OO...OO
//OOO...O
//..OO.OO
//...OOOO
//...OOOO

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, N;
	static int time[][];
	static boolean bomb[][];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		time = new int[Y][X];
		bomb = new boolean[Y][X];
		
		for(int y=0; y<Y; y++)
		{
			String str = br.readLine();
			for(int x=0; x<X; x++)
			{
				char c = str.charAt(x);
				if(c == 'O')
				{
					bomb[y][x] = true;
					time[y][x] = 3;
				}
			}
		}
		
		if(N == 1)
		{
			print();
			return;
		}
		
		for(int i=2; i<=N; i++)
		{
			if(i % 2 == 0)
				add(i);
			else
				delete(i);
		}
		print();
	}
	static void delete(int targetTime) {
		for(int y=0; y<Y; y++) {
			for(int x=0; x<X; x++) {
				if(time[y][x] != targetTime)
					continue;
				
				bomb[y][x] = false;
				time[y][x] = 0;
				
				for(int xy[] : dxy)
				{
					int ny = y + xy[0];
					int nx = x + xy[1];
					
					if(0<=ny && 0<=nx && ny<Y && nx<X && time[ny][nx] != targetTime)
					{
						bomb[ny][nx] = false;
						time[ny][nx] = 0;
					}
				}
			}
		}
	}
	static void add(int targetTime) {
		for(int y=0; y<Y; y++) {
			for(int x=0; x<X; x++) {
				if(!bomb[y][x]) {
					bomb[y][x] = true;
					time[y][x] = targetTime + 3;
				}
			}
		}
	}
	static void print() {
		StringBuilder sb = new StringBuilder();
		for(int y=0; y<bomb.length; y++)
		{
			for(int x=0; x<bomb[y].length; x++)
			{
				sb.append(bomb[y][x] ? 'O' : '.');
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}