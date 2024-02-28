// https://github.com/KimYongJ/algorithm

import java.util.ArrayDeque;


class Main{
	
	static int N, M, map[][];
	static int nextY, nextX, nextTime;
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean visit[][][];
	static ArrayDeque<Node> q;
	// 빠른 입력을 위한 함수
    static int read() throws Exception 
    {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static void BFS() 
	{
		q = new ArrayDeque<Node>();
		q.add(new Node(1,1,0,0));
		visit[0][1][1] = visit[1][1][1] =true;
		//큐가 빌 때 까지 반복
		while(!q.isEmpty()) 
		{
			Node now = q.poll();
			// 종료점일 경우 종료
			if(now.y == N && now.x == N) 
			{
				System.out.println(now.time);
				return;
			} 
			
			for(int[] xy : dxy) 
			{
				
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				nextTime = now.time + 1;
				
				if(visit[now.bridge][nextY][nextX]) continue;
				
				 
				// 다음이 땅인 경우
				if(map[nextY][nextX] == 1) 
				{
					q.add(new Node(nextY, nextX, nextTime, now.bridge));
					visit[now.bridge][nextY][nextX] = true;
				}
				// 다음 위치가 주기가있는 오작교 일 때, 현재 위치가 땅이여야 다리 건너기가능
				else if(map[nextY][nextX] > 1 && map[now.y][now.x]==1)
				{
					// 시간이 맞아 건널 수 있을 때 
					if(nextTime % map[nextY][nextX] == 0) 
					{
						q.add(new Node(nextY, nextX, nextTime, now.bridge));
						visit[now.bridge][nextY][nextX] = true;
					}else 
						q.add(new Node(now.y,now.x, nextTime, now.bridge));
				}
				// 절벽인데 다리를 한번 놓을 수 있는 경우 + 현재 위치가 땅이여야 다리 놓아 가기 가능
				else if(map[nextY][nextX] == 0 && now.bridge == 0 && map[now.y][now.x]==1) 
				{
					// 절벽이라 다리를 생성하는데 마침 주기와 시간이 맞아 떨어질 때
					if(nextTime % M == 0)
					{
						q.add(new Node(nextY, nextX, nextTime, 1));
						visit[1][nextY][nextX] = true;
					}
					// 절벽이지만 주기가맞지 않을 때 기다린다.
					else 
						q.add(new Node(now.y,now.x, nextTime, now.bridge));
				}
				
			}
		}
		
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		M 		= read();
		map 	= new int[N+2][N+2];				// 입력 받을 지도
		visit 	= new boolean[2][N+2][N+2];			// 다리 생성 유무에 따라서 visit을 다르게 해야 한다. 나중에 다리를 생성한게 더 빠를 수도 있기 때문
		
		// 패딩 입력
		for(int i=0; i<N+2; i++)
			map[0][i] = map[N+1][i] = map[i][0] = map[i][N+1] = -1;
		
		// 값 입력 받음
		for(int i=1; i<=N; i++) 
			for(int j=1; j<=N; j++) 
				map[i][j] = read();
		
		// 절벽 교차점 방문 체크
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++)
				if(map[i][j] == 0)
				{
					if((map[i-1][j] == 0 && map[i][j-1] == 0) || (map[i+1][j]==0 && map[i][j-1] == 0) ||
						(map[i+1][j]==0 && map[i][j+1] == 0) || (map[i][j+1]==0 && map[i-1][j] == 0))
						visit[0][i][j] = 
						visit[1][i][j] = true;
				}
		BFS();
	}
}
class Node
{
	int y, x, time, bridge;
	Node(int y, int x, int time, int bridge)
	{
		this.y = y;
		this.x = x;
		this.time = time;
		this.bridge = bridge;
	}
}