//https://www.acmicpc.net/problem/13901
//1초 256MB
//5 5 // 세로, 가로크기 (3<=1,000)
//3 // 장애물 개수 (0<=1,000)
//1 2 // 장애물 위치(세로가로)
//3 3
//2 4
//2 2 // 로봇의 시작위치(세로 , 가로)
//1 2 3 4// 이동방향(1=위,2=아래,3=왼쪽,4=오른쪽)
//로봇이 멈추는 위치 : 2 3

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int dxy[][] = {{0,0},{-1,0},{1,0},{0,-1},{0,1}};
		int Y = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		boolean map[][] = new boolean[Y][X];
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0)
		{
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			map[y][x] = true;
		}
		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		map[y][x] = true;
		
		int dir[] = new int[4];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++)
			dir[i] = Integer.parseInt(st.nextToken());
		
		boolean move = true;
		int idx = 0;
		
		while(move)
		{
			move = false;
			
			for(int i=0; i<4; i++)
			{
				int ny = y + dxy[dir[idx]][0];
				int nx = x + dxy[dir[idx]][1];
				
				if(ny < 0 || nx < 0 || ny == Y || nx == X || map[ny][nx])
				{
					idx = (idx + 1) % 4;
					continue;
				}
				
				map[ny][nx] = move = true;
				y = ny;
				x = nx;
				
				break;
			}
		}
		System.out.print(y + " " + x);
	}
}