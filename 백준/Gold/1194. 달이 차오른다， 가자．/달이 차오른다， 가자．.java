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
	
	static int dxy[][] = {{0,1},{1,0},{-1,0},{0,-1}};
	static int Y, X, nextY, nextX, nextDist, nextBitmask;
	static char map[][];
	static boolean visit[][][];
	static ArrayDeque<Point> q;
	
	public static void BFS() {
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			if(map[now.y][now.x] == '1') 
			{
				System.out.println(now.dist);
				return;
			}
			
			for(int xy[] : dxy) {
				nextY 		= now.y + xy[0];
				nextX 		= now.x + xy[1];
				nextDist 	= now.dist + 1;
				nextBitmask = now.bitmask;
				if(nextY>=0 && nextX>=0 && nextY<Y && nextX<X) 
				{
					if('A' <= map[nextY][nextX] && map[nextY][nextX] <='F' )		// 가려는 곳이 문인 경우
					{
						if( (nextBitmask & (1<<(map[nextY][nextX] -'A'))) != 0 ) 	// 열쇠가 있다면 
						{
							if(!visit[nextBitmask][nextY][nextX])					// 처음 방문한다면
							{
								visit[nextBitmask][nextY][nextX] = true;
								q.add(new Point(nextY, nextX, nextDist, nextBitmask));
							}
						}
					}
					else if('a' <= map[nextY][nextX] && map[nextY][nextX] <='f' )	// 가려는 곳이 열쇠인 경우
					{
						nextBitmask |= (1 << (map[nextY][nextX]-'a'));				// 열쇠를 넣는다.
						if(!visit[nextBitmask][nextY][nextX])						// 처음 방문한다면
						{
							visit[nextBitmask][nextY][nextX] = true;
							q.add(new Point(nextY, nextX, nextDist, nextBitmask));
						}
					}
					else if(map[nextY][nextX] != '#' && !visit[nextBitmask][nextY][nextX])
					{
						visit[nextBitmask][nextY][nextX] = true;
						q.add(new Point(nextY, nextX, nextDist, nextBitmask));
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
		map 				= new char[Y][X];
		visit 				= new boolean[64][Y][X];
		q 					= new ArrayDeque<>();
		String str; char c;
		for(int y=0; y<Y; y++) 
		{
			str = br.readLine();
			for(int x=0; x<X; x++) 
			{
				c = str.charAt(x);
				map[y][x] = c;
				if(c=='0') {
					q.add(new Point(y,x,0,0));
					visit[0][y][x] = true;
				}
			}
		}
		BFS();
	}
}