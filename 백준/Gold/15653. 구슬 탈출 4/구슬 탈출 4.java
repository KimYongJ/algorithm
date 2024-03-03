// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Node{
	int y1, x1, y2, x2, cnt;
	Node(int y1, int x1, int y2, int x2, int cnt){
		this.y1 = y1; this.x1 = x1;
		this.y2 = y2; this.x2 = x2;
		this.cnt = cnt;
	}
}
class Main{
	
	static int Y, X, oY, oX, bY, bX, rY, rX;
	static int newY1, newY2, newX1, newX2, nextCnt;
	static int dxy[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	static char map[][];
	static boolean visit[][][][];
	static ArrayDeque<Node> q ;
	public static int getNextY(int i, int newy, int newx) {
		while(true)
		{
			newy += dxy[i][0];
			if(0> newy || newy >= Y || map[newy][newx] == '#') 
			{
				newy -= dxy[i][0];
				return newy;
			}else if(map[newy][newx] == 'O') 
				return -1;
		}
	}
	public static int getNextX(int i, int newy, int newx) {
		while(true) {
			newx += dxy[i][1];
			if(0> newx || newx >= X || map[newy][newx] == '#') {
				newx -= dxy[i][1];
				return newx;
			}else if(map[newy][newx] == 'O')
				return -1;
		}
	}

	public static int BFS() {
		q 		= new ArrayDeque<>();
		visit	= new boolean[Y][X][Y][X];
		q.add(new Node(rY, rX, bY, bX, 0));
		visit[rY][rX][bY][bX] = true;
		
		while(!q.isEmpty()) 
		{
			Node now = q.poll();
			
			nextCnt = now.cnt + 1;
			if(nextCnt > 100 ) continue;
			Loop:
			for(int i=0; i<4; i++) 
			{
				newY1 = now.y1;
				newX1 = now.x1;
				newY2 = now.y2;
				newX2 = now.x2;
				if(i <= 1) 								// 상 하 움직임
				{
					newY1 = getNextY(i, newY1, newX1);	// 다음 Y1 값
					newY2 = getNextY(i, newY2, newX2);	// 다음 Y2 값
					if(newY2 == -1)						// -1 이면 Blue가 구멍에 빠진 것이므로 무조건 실패
						continue Loop;
					if(newY1 == -1) 					// -1 이면 o를 만난 것이므로 출력 후 종료
					{
						System.out.println(nextCnt);
						System.exit(0);
					}
					if(now.x1 == now.x2 && newY1 == newY2)// 같은 열에 있어서 굴린 위치가 같다면 
					{
						if(i == 0) 						// 위로 굴리기
						{
							if(now.y1 > now.y2)  newY1 += 1;
							else  newY2 += 1;
						}else 							// 아래로 굴리기
						{
							if(now.y1 > now.y2) newY2 -= 1;
							else  newY1 -= 1;

						}
					}
				}
				else 					// 좌 우 움직임
				{
					newX1 = getNextX(i, newY1, newX1);
					newX2 = getNextX(i, newY2, newX2);
					if(newX2 == -1)						// -1 이면 Blue가 구멍에 빠진 것이므로 무조건 실패
						continue Loop;
					if(newX1 == -1) 					// -1 이면 o를 만난 것이므로 출력 후 종료
					{
						System.out.println(nextCnt);
						System.exit(0);
					}
					if(now.y1 == now.y2 && newX1 == newX2)// 같은 행에 있어서 굴린 위치가 같다면
					{
						if(i==3)  					// 우측 굴리기 
						{
							if(now.x1 > now.x2) newX2 -= 1;
							else newX1 -= 1;
							
						}else						// 좌측 굴렸을 때  
						{
							if(now.x1 > now.x2) newX1 += 1;
							else newX2 += 1;
						}
					}

				}
				if(nextCnt <= 100 && !visit[newY1][newX1][newY2][newX2]) 
				{
					visit[newY1][newX1][newY2][newX2] = true;
					q.add(new Node(newY1, newX1, newY2, newX2, nextCnt));
				}
				
			}
			
		}

		return -1;
	}
	public static void main(String[] args)throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		map = new char[Y][X];
		
		String str; char c;
		
		for(int y=0; y<Y; y++) 
		{
			str = br.readLine();
			for(int x=0; x<X; x++) 
			{
				c = str.charAt(x);
				switch(c) 
				{
					case 'R': rY = y; rX = x;break;
					case 'B': bY = y; bX = x;break;
					case 'O': oY = y; oX = x;break;
				}
				map[y][x] = c;
			}
		}
		System.out.println( BFS() );
		
	}
}