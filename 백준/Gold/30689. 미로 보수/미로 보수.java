//https://www.acmicpc.net/problem/30689
//2초 1024MB
//3 3 // 세로, 가로 
//LLL // 미로의 방향이 먼저 주어짐
//DLU
//RUU
//3 1 5 // 각칸의 탈출 비용이 주어짐
//2 9 6
//8 7 1
//답 : 2

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static final int dxy[][] = {{-1,0},{0,1},{1,0},{0,-1}};
	static int ans;
	static int Y, X;
	static int dir[][];
	static int state[][];
	static int value[][];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y = Integer.parseInt(st.nextToken()); // 1<=2,000
		X = Integer.parseInt(st.nextToken()); // 1<=2,000
		dir = new int[Y][X];
		value = new int[Y][X];
		state = new int[Y][X];
		
		for(int y=0; y<Y; y++) // 방향 입력 받음
		{
			String str = br.readLine();
			for(int x=0; x<X; x++)
			{
				switch(str.charAt(x))
				{
				case 'R' : dir[y][x] = 1;break;
				case 'D' : dir[y][x] = 2;break;
				case 'L' : dir[y][x] = 3;break;
				}
			}
		}
		
		for(int y=0; y<Y; y++) // 값 입력 받음
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<X; x++)
				value[y][x] = Integer.parseInt(st.nextToken());// 1<=500
		}
		
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++)
				if(state[y][x] == 0)
					dfs(y, x);
		
		System.out.print(ans);
	}
	static void dfs(int y, int x) {
		state[y][x] = 1; // 방문 중
		
		int nextY = y + dxy[dir[y][x]][0];
		int nextX = x + dxy[dir[y][x]][1];
		
		if(nextY >= 0 && nextX >= 0 && nextY < Y && nextX < X)
		{
			if(state[nextY][nextX] == 0)
			{
				dfs(nextY, nextX);
			}
			else if(state[nextY][nextX] == 1)
			{
				int min = value[nextY][nextX];
				int ny = nextY;
				int nx = nextX;
				while(true)
				{
					int ty = ny + dxy[dir[ny][nx]][0];
					int tx = nx + dxy[dir[ny][nx]][1];
					min = Math.min(min, value[ty][tx]);
					ny = ty;
					nx = tx;
					if(nextY == ny && nextX == nx)
						break;
				}
				ans += min;
			}
		}
		state[y][x] = 2;// 방문 종료
	}
}