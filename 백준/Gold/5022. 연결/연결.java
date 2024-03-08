// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Point{
	int y, x;
	Point(int y, int x){
		this.y=y; this.x=x;
	}
}
class Node{
	int y, x, dist;
	Node(int y, int x, int dist){
		this.y=y; this.x=x; this.dist=dist;
	}
}
class Main
{
	static final int MAX_VALUE = Integer.MAX_VALUE;
	static int Y, X, dist, nextX, nextY, nextDist, node[][];
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean visit[][], firstVisit[][];
	static ArrayDeque<Node> q;
	static Point beforeNode[][];
	
	public static void DFS(int y1, int x1, int y2,int x2) 
	{
		visit[y1][x1] = true;
		if(y1==y2 && x1==x2) return;
		nextY = beforeNode[y1][x1].y;
		nextX = beforeNode[y1][x1].x;
		DFS(nextY, nextX, y2, x2);
	}
	public static int first_BFS(int[] start, int[] end) 
	{
		dist 			= MAX_VALUE;
		q 				= new ArrayDeque<>();
		firstVisit[start[0]][start[1]] = true;
		q.add(new Node(start[0], start[1], 0));
		
		while(!q.isEmpty()) 
		{
			Node now = q.poll();
			if(now.y == end[0] && now.x == end[1]) 
			{
				dist = now.dist;
				break;
			}
			for(int xy[] : dxy) 
			{
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				nextDist = now.dist + 1;
				if( 0<=nextY && 0<=nextX && nextY<=Y && nextX<=X && !firstVisit[nextY][nextX]) 
				{
					firstVisit[nextY][nextX] = true;
					beforeNode[nextY][nextX] = new Point(now.y,now.x);	// 현재 좌표에, 바로전 좌표를 세팅한다. 추후 역 탐색시 사용
					q.add(new Node(nextY, nextX, nextDist));
				}
			}
		}

		DFS(end[0], end[1], start[0], start[1]); // 지나온길을 역으로 탐색하며 좌표들에 방문 처리를하여 second_BFS에서 해당 부분을 탐색하지 못하도록한다. 
		
		return dist;
	}
	public static int second_BFS(int[] start, int[] end) 
	{
		q = new ArrayDeque<>();
		q.add(new Node(start[0], start[1],0));
		
		while(!q.isEmpty()) 
		{
			Node now = q.poll();
			for(int xy[] : dxy) 
			{
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				nextDist = now.dist + 1;
				if( 0<=nextY && 0<=nextX && nextY<=Y && nextX<=X) 
				{
					if(nextY == end[0] && nextX == end[1])	// 종료점 도달시 종료 
						return nextDist;
					if( !visit[nextY][nextX])				// 방문하지 않은 곳만 큐에 다시 넣는다.
					{
						visit[nextY][nextX] = true;
						q.add(new Node(nextY, nextX, nextDist));
					}
				}
			}
		}
		return MAX_VALUE;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		Y 			= Integer.parseInt(st.nextToken());
		X 			= Integer.parseInt(st.nextToken());
		node 		= new int[4][2];					// a1,a2 , b1,b2를 담을 배열
		beforeNode 	= new Point[Y+1][X+1];
		for(int i=0; i<4; i++) 
		{
			st = new StringTokenizer(br.readLine());
			node[i][0] = Integer.parseInt(st.nextToken());
			node[i][1] = Integer.parseInt(st.nextToken());
		}
		
		visit 		= new boolean[Y+1][X+1];
		firstVisit	= new boolean[Y+1][X+1];
		firstVisit[node[2][0]][node[2][1]] = 
		firstVisit[node[3][0]][node[3][1]] = true;				// 첫번째 탐색시, 다음 노드와 겹치지 않게 하기 위해 다음노드든 못지나가도록 visit true처리 
		int dist1 		= first_BFS(node[0], 	node[1]); 		// a1,a2사이의 최단거리를 구하고, a1,a2사이의 최단거리에 visit true 처리한다.
		int dist2 		= second_BFS(node[2], 	node[3]); 		// b1,b2사이의 최단거리를 구하는데, 이 때 위에서 구한 a1, a2의 최단거리 경로는 못지나간다.(true처리되어잇기 때문에)
		int sumDist1	= dist2 != MAX_VALUE ? 	dist1 + dist2 : MAX_VALUE;
		
		
		visit 		= new boolean[Y+1][X+1];
		firstVisit	= new boolean[Y+1][X+1];
		firstVisit[node[0][0]][node[0][1]] = 
		firstVisit[node[1][0]][node[1][1]] = true;				// 첫번째 탐색시, 다음 노드와 겹치지 않게 하기 위해 다음노드든 못지나가도록 visit true처리 
		int dist3 		= first_BFS(node[2], 	node[3]);
		int dist4 		= second_BFS(node[0], 	node[1]); 
		int sumDist2 	= dist4 != MAX_VALUE ? 	dist3 + dist4 : MAX_VALUE;
		
		
		if(sumDist1 == MAX_VALUE && sumDist2 == MAX_VALUE)		// 둘다 도달 할 수 없다면 불가능 출력
			System.out.println("IMPOSSIBLE");
		else 
			System.out.println(Math.min(sumDist1,  sumDist2));	// 둘 중 하나라도 도달했다면 작은 값 출력
	}
}

