// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Point{
	int y, x, dist, bitmask;
	Point(int y, int x, int dist, int bitmask){
		this.y = y; 		this.x = x;
		this.dist = dist; 	this.bitmask = bitmask;
	}
}
class Main{
	
	static char map[][];
	static boolean isContinue, visit[][][];
	static int Y, X, nextY, nextX, nextBitmask;
	static int dxy[][] = {{0,1},{1,0},{-1,0},{0,-1}};
	static ArrayDeque<Point> q;
	
	public static void BFS() {
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			if(map[now.y][now.x] == '1') 
			{
				System.out.println(now.dist);
				return;
			}
			
			for(int xy[] : dxy)
			{
				nextY 		= now.y + xy[0];
				nextX 		= now.x + xy[1];
				nextBitmask = now.bitmask;
				isContinue	= true;
				if(map[nextY][nextX] != '#') 
				{
					if('a' <= map[nextY][nextX] && map[nextY][nextX] <='f' )		// 가려는 곳이 열쇠인 경우
						nextBitmask |= (1 << (map[nextY][nextX]-'a'));				// 열쇠를 넣는다.
					
					else if('A' <= map[nextY][nextX] && map[nextY][nextX] <='F' )	// 가려는 곳이 문인 경우
						if( (nextBitmask & (1<<(map[nextY][nextX] -'A'))) == 0 ) 	// 열쇠가 없다면 
							isContinue = false;										// 종결
					
					if(isContinue && !visit[nextBitmask][nextY][nextX])
					{
						visit[nextBitmask][nextY][nextX] = true;
						q.add(new Point(nextY, nextX, now.dist + 1, nextBitmask));
					}
				}
			}
		}
		System.out.println(-1);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		Y 					= Integer.parseInt(st.nextToken());
		X 					= Integer.parseInt(st.nextToken());
		map 				= new char[Y+2][X+2];
		visit 				= new boolean[64][Y+2][X+2];
		q 					= new ArrayDeque<>();
		String str;
		
		for(int y=0; y<Y+2; y++)
			map[y][0] = map[y][X+1] = '#';	// 패딩 넣기
		for(int x=0; x<X+2; x++)
			map[0][x] = map[Y+1][x] = '#';	// 패딩 넣기
		
		for(int y=1; y<=Y; y++) 
		{
			str = br.readLine();
			for(int x=1; x<=X; x++) 
			{
				map[y][x] = str.charAt(x-1);
				if(map[y][x]=='0') 
				{
					q.add(new Point(y,x,0,0));
					visit[0][y][x] = true;
				}
			}
		}
		BFS();
	}
}