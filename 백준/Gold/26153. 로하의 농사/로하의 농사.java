//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/26153

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, MAX;
	static int map[][];
	static boolean visit[][];
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y		= Integer.parseInt(st.nextToken());
		X		= Integer.parseInt(st.nextToken());
		map		= new int[Y][X];
		visit	= new boolean[Y][X];
		
		for(int y=0; y<Y; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<X; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int sy = Integer.parseInt(st.nextToken());
		int se = Integer.parseInt(st.nextToken());
		int cnt= Integer.parseInt(st.nextToken());
		
		MAX = map[sy][se];
		if(cnt != 0)
		{
			visit[sy][se] = true;
			for(int i=0; i<4; i++)
			{
				int ny = sy + dxy[i][0];
				int nx = se + dxy[i][1];
				if(0<=ny && 0<=nx && ny<Y && nx<X && !visit[ny][nx])
				{
					visit[ny][nx] = true;
					back(ny, nx, cnt - 1, map[sy][se] + map[ny][nx], i);
					visit[ny][nx] = false;
				}
			}
		}
		System.out.print(MAX);
	}
	public static void back(int sy, int se, int cnt, int sum, int prevDir) {
		if(MAX < sum)
			MAX = sum;
		
		for(int i=0; i<4; i++)
		{
			int ny = sy + dxy[i][0];
			int nx = se + dxy[i][1];
			if(0<=ny && 0<=nx && ny<Y && nx<X && !visit[ny][nx])
			{
				int minus = prevDir == i ? 1 : 2;
				if(minus <= cnt) {
					visit[ny][nx] = true;
					back(ny, nx, cnt - minus, sum + map[ny][nx], i);
					visit[ny][nx] = false;
				}
			}
		}
	}
}