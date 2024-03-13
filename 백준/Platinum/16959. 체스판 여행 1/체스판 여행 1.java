// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Node{
	int y, x, type, time, goal;// type0비숍 1룩 2나이트
	Node(int y, int x, int type, int time, int goal){
		this.y=y; this.x=x; this.type=type;
		this.time=time; this.goal=goal;
	}
}
class Main
{	
	static int N, NN, map[][], min;
	
	static int dxy[][][] = {
			{{-1,-1},{-1,1},{1,1},{1,-1}},								// 비숍
			{{1,0},{0,1},{-1,0},{0,-1}}, 								// 룩
			{{-2,-1},{-2,1},{2,-1},{2,1},  {-1,2},{1,2},{-1,-2},{1,-2} }// 나이트
	};
	static boolean visit[][][][]; // 순서 : [goal][type][y][x]까지 오는데 걸린 시간
	static ArrayDeque<Node> q;
	public static void BFS() {

		int nextType, nextTime, nextY, nextX, nextGoal;
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			if(now.goal == NN) 
			{
				min = Math.min(min, now.time);
				continue;
			}
			nextTime = now.time + 1;
			nextGoal = now.goal + 1;

			if(now.type != 2) 						// 현재가 비숍이거나 룩일 때
			{
				for(int xy[] : dxy[now.type]) 
				{
					nextY = now.y;					// 현재 좌표를 삽입
					nextX = now.x;					// 현재 좌표를 삽입
					while(true) 					// 좌를 벗어날 때 까지 계속 반복
					{
						nextY += xy[0];				// 계속 더해나간다
						nextX += xy[1];				// 계속 더해나간다
						if(map[nextY][nextX] > 0) 	// 좌표가 유효 범위 안에 있을 때
						{
							if( !visit[now.goal][now.type][nextY][nextX] ) 							// 방문하지 않았었다면
							{
								visit[now.goal][now.type][nextY][nextX] = true;						// 방문처리
								if(map[nextY][nextX] == nextGoal) 									// 목표지점에 도달한 경우
									q.add(new Node(nextY, nextX, now.type, nextTime, nextGoal));	// 큐에 삽입
								else 
									q.add(new Node(nextY, nextX, now.type, nextTime, now.goal));	// 방문한 곳이 목표지점이 아닐 경우
							}
						}else break;
					}
				}
			}else 					// 현재가 나이트일 때 
			{
				for(int xy[] : dxy[now.type]) 
				{
					nextY = now.y + xy[0];
					nextX = now.x + xy[1];
					if(nextY >=1 && nextX>=1 && nextY<=N && nextX<=N &&!visit[now.goal][now.type][nextY][nextX]) 	// 유효범위 안에 있고, 다음 좌표가 한번도 방문하지 않았다면
					{
						visit[now.goal][now.type][nextY][nextX] = true;						// 방문 처리
						if(map[nextY][nextX] == nextGoal)									// 방문한 곳이 목표로한 곳이라면 
							q.add(new Node(nextY, nextX, now.type, nextTime, nextGoal));	// 큐에 삽입
						else
							q.add(new Node(nextY, nextX, now.type, nextTime, now.goal));	// 방문한 곳이 목표지점이 아닐 경우
					}
				}
			}
			nextType = (now.type + 1)%3;
			if(!visit[now.goal][nextType][now.y][now.x]) 
			{
				visit[now.goal][nextType][now.y][now.x] = true;
				q.add(new Node(now.y, now.x, nextType, nextTime, now.goal));
			}
			
			nextType = (nextType + 1)%3;
			if(!visit[now.goal][nextType][now.y][now.x]) 
			{
				visit[now.goal][nextType][now.y][now.x] = true;
				q.add(new Node(now.y, now.x, nextType, nextTime, now.goal));
			}
			
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		min		= 999999;
		N 		= Integer.parseInt(br.readLine());
		NN		= N*N;
		map 	= new int[N+2][N+2];
		q 		= new ArrayDeque<>();
		visit	= new boolean[(NN)+1][3][N+2][N+2];
		for(int i=0; i<N+2; i++)		// 패딩 넣기
			map[i][0] = map[0][i] = map[N+1][i] = map[i][N+1] = -1;
		
		for(int i=1; i<=N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) 
			{
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) 
				{
					q.add(new Node(i,j,0,0,1));
					q.add(new Node(i,j,1,0,1));
					q.add(new Node(i,j,2,0,1));
				}
			}
		}
		
		BFS();
		
		System.out.println(min);
	}
}

