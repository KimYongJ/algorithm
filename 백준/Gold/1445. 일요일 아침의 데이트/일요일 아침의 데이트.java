// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node{
	int y,x, g, g1;
	Node(int y, int x, int g, int g1){
		this.y=y;this.x=x;
		this.g=g;this.g1=g1;
	}
}
class Main{
	static final int MAX = 3_000;
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, sy, sx, ey, ex;
	static int[][] g, g1;
	static boolean map[][];
	static PriorityQueue<Node> pq;
	public static boolean check(int y, int x) {	// 주변쓰레기 체킹
		if(y==ey && x==ex)
			return false;
		
		int nextY, nextX;
		for(int xy[] : dxy) {
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(nextY>=0 && nextX>=0 && nextY<Y && nextX<X && map[nextY][nextX])
				return true;
		}
		return false;
	}
	public static void add(int y, int x, int nextG, int nextG1) {
		g[y][x] = nextG;
		g1[y][x] = nextG1;
		pq.add(new Node(y, x, nextG, nextG1));
	}
	public static void Dijkstra() {
		g[sy][sx] = g1[sy][sx] = 0;
		pq.add(new Node(sy,sx,0,0));
		Node now;
		int nextY, nextX;
		while(!pq.isEmpty()) {
			now = pq.poll();
			
			if(now.y == ey && now.x == ex)
				break;
			
			for(int xy[] : dxy) 
			{
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				
				if(nextY>=0 && nextX>=0 && nextY<Y && nextX<X) {
					if(map[nextY][nextX]) { 						// 다음이 쓰레기일 때 
						if(g[nextY][nextX] > now.g + 1)
							add(nextY, nextX, now.g+1, now.g1);
					}else {
						if(check(nextY,nextX)) {					// 다음 주변에 쓰레기가 있을 때 
							if(g1[nextY][nextX] > now.g1 + 1)
								add(nextY, nextX, now.g, now.g1 + 1);
						}
						else { 									// 다음 주변에 쓰레기가 없을 때
							if(g[nextY][nextX] > now.g && g1[nextY][nextX] > now.g1)
								add(nextY, nextX, now.g, now.g1);
						}
					}
				}
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y 	= Integer.parseInt(st.nextToken());
		X 	= Integer.parseInt(st.nextToken());
		g 	= new int[Y][X];	// 쓰레기를 직접 지나는 최소 개수 
		g1 	= new int[Y][X];	// 쓰레기 옆을 지나는 칸의 개수
		map = new boolean[Y][X];
		pq	= new PriorityQueue<Node>((a,b)->{
												if(a.g==b.g) return a.g1-b.g1;
												return a.g-b.g;
											});
		
		for(int y=0; y<Y; y++) 
		{
			int x = 0;
			for(char c : br.readLine().toCharArray())
			{
				if(c=='S') 
				{
					sy = y; 
					sx = x;
				}
				else if(c=='F') 
				{
					ey = y; 
					ex = x;
				}
				map[y][x] 	= c=='g';
				g[y][x]		= 
				g1[y][x] 	= MAX;
				x++;
			}
		}
		
		Dijkstra();
		
		int r1 = g[ey][ex];
		int r2 = g1[ey][ex];
		
		if(r1 == MAX) r1 = 0;
		if(r2 == MAX) r2 = 0;
		
		System.out.print(
				new StringBuilder().append(r1)
						.append(' ').append(r2)
				);
	}
}