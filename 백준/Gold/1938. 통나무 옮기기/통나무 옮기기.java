// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

class Main{


	static int N, goal[][];
	static int dxy[][] = {{1,0},{-1,0},{0,1},{0,-1},  {-1,-1},{-1,1},{1,-1},{1,1}};
	static boolean visit[][][];
	static char map[][];
	
	public static boolean rotate_validate(int y, int x) 
	{
		if(map[y][x] == '1') return false;
		int nextX, nextY;
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if( !(nextY>=0 && nextX>=0 && nextY<N && nextX<N && map[nextY][nextX] !='1') )
				return false;
		}
		return true;
	}
	public static boolean move_validate(int y, int x, int dir) 
	{
		if( y<0 || x<0 || y>=N || x>=N || map[y][x] == '1') return false;
		if(dir == 0)  	// 가로일 때 
		{
			if(x+1 >=N || x-1<0 || map[y][x+1] == '1' || map[y][x-1] =='1')
				return false;
		}else			// 세로일 때 
		{
			if(y+1 >=N || y-1 <0 || map[y+1][x] == '1' || map[y-1][x] =='1')
				return false;
		}
		return true;
	}

	public static void BFS(int y, int x, int dir) 
	{
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.add(new Node(y,x,dir, 0));
		visit[dir][y][x] = true;
		
		int nextX, nextY, nextDir, cnt;
		while(!q.isEmpty())
		{
			Node now = q.poll();
			
			if(goal[now.dir][0] == now.y && goal[now.dir][1] == now.x) 
			{
				System.out.println(now.cnt);
				System.exit(0);
			}
			
			cnt = now.cnt + 1;
			for(int i=0; i<5; i++) 
			{
				if(i==4) // 회전 가능한지 파악 
				{
					nextDir = now.dir == 0 ? 1 : 0;
	 				if(rotate_validate(now.y,now.x) && !visit[nextDir][now.y][now.x]) // 회전 가능한 경우 방향 회전
	 				{
	 					visit[nextDir][now.y][now.x] = true;
	 					q.add(new Node(now.y, now.x, nextDir, cnt));
	 				}
				}else {
					nextY = now.y + dxy[i][0];
					nextX = now.x + dxy[i][1];
					if(move_validate(nextY,nextX, now.dir) && !visit[now.dir][nextY][nextX]) 
					{
						visit[now.dir][nextY][nextX] = true;
						q.add(new Node(nextY, nextX, now.dir, cnt));
					}
				}
			}
				
			
		}
		
	}
	public static void main(String[] args)throws Exception
	{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		N 					= Integer.parseInt(br.readLine());
		map 				= new char[N][N];
		visit 				= new boolean[2][N+1][N+1]; // 가로인지 세로인지 dir과 가운데 좌표 y, x를 기준으로 방문 체크 
		goal				= new int[2][2];
		
		int sidx=0, 
			eidx=0, 
			end[][] = new int[3][2],
			start[][] = new int[3][2];
		
		for(int y=0; y<N; y++) 
		{
			String str = br.readLine();
			for(int x=0; x<N; x++) 
			{
				char c = str.charAt(x);
				if(c=='B')  // 시작 위치 넣기 
				{
					start[sidx][0] = y;
					start[sidx++][1] = x;
					c = '0';
				}else if(c=='E')  // 종료 위치 넣기
				{
					end[eidx][0] = y;
					end[eidx++][1] = x;
					c = '0';
				}
				map[y][x] = c;
				
			}
		}
		
		int dir;
		// 도촥 좌표 다시 셋팅 
		dir = end[1][0] -1 == end[0][0] ? 1 : 0;
		goal[dir][0] = end[1][0];
		goal[dir][1] = end[1][1];
		
		// 세로면 1 가로면 0
		dir = start[1][0] -1 == start[0][0] ? 1 : 0;
		BFS(start[1][0], start[1][1], dir);

		
		System.out.println(0);
	}
}
class Node{
	int y, x, dir, cnt;
	Node(int y, int x, int dir, int cnt){
		this.y = y;
		this.x = x;
		this.dir = dir;
		this.cnt = cnt;
	}
}