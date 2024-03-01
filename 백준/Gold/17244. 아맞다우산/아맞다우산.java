// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Node{
	int y, x, dist;
	Node(int y, int x, int dist){
		this.y = y;
		this.x = x;
		this.dist = dist;
	}
}
class Main{
	
	static int Y, X, sy, sx, ey, ex, idx, nextY, nextX, p[][], next_p[][];
	static int result = Integer.MAX_VALUE;
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static char map[][];
	static boolean visit[], vs[][];
	static ArrayDeque<Node> q;
	public static int BFS(int y1, int x1, int y2, int x2) 
	{
		vs = new boolean[Y][X];
		q = new ArrayDeque<>() {{add(new Node(y1,x1,0));}};
		vs[y1][x1] = true;
		
		while(!q.isEmpty()) 
		{
			Node now = q.poll();
			
			if(now.y == y2 && now.x == x2)
				return now.dist;
			
			for(int xy[] : dxy) 
			{
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				if(nextY >=0 && nextX >=0 && nextY <Y && nextX<X && !vs[nextY][nextX] && map[nextY][nextX] != '#') {
					vs[nextY][nextX] = true;
					q.add(new Node(nextY, nextX, now.dist+1));
				}
			}
		}
		
		
		return 0;
	}
	
	public static void getResult() 
	{
		int dist = 0;
		if(idx!=0) {
			// S -> 첫번 째 물건 까지 거리
			dist += BFS(sy, sx, next_p[0][0], next_p[0][1]);
			
			for(int i=0; i<idx-1; i++) 
			{
				dist += BFS(next_p[i][0], next_p[i][1], next_p[i+1][0], next_p[i+1][1]);
				if(dist >= result) 
					return;
			}
			// 마지막 물건에서 E까지 거리
			dist += BFS(ey, ex, next_p[idx-1][0], next_p[idx-1][1]);
		}
		else {
			dist = BFS(sy, sx, ey, ex);
		}
		if(result > dist)		// 최종 결과에 최소 값 세팅 
			result = dist;
	}
	
	public static void DFS(int depth) 
	{
		if(depth == idx) 
		{
			getResult();
			return;
		}

		for(int i=0; i<idx; i++) 
		{
			if(!visit[i]) 
			{
				visit[i] = true;
				next_p[depth][0] = p[i][0];
				next_p[depth][1] = p[i][1];
				DFS(depth + 1);
				visit[i] = false;
			}
		}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st	= new StringTokenizer(br.readLine());
		X					= Integer.parseInt(st.nextToken());
		Y					= Integer.parseInt(st.nextToken());
		map					= new char[Y][X];
		p					= new int[5][2];
		
		for(int y=0; y<Y; y++) 
		{
			String str = br.readLine();
			for(int x=0; x<X; x++) 
			{
				map[y][x] = str.charAt(x);
				if(map[y][x] == 'S')			// 시작 위치를 담아 놓음  
				{
					sy = y;
					sx = x;
				}
				else if(map[y][x] == 'E') 		// 종료위치를 담아 놓음
				{
					ey = y;
					ex = x;
				}
				else if(map[y][x] == 'X') 
				{
					p[idx][0] = y;
					p[idx][1] = x;
					++idx; 						// 추후 완전 탐색시 x_p의 인덱스로 쓰임
				}
			}
		}
		visit	= new boolean[idx];				// 완전 탐색시 사용
		next_p	= new int[idx][2];				// X의 위치를 골고루 담을 변수
		DFS(0); // 물건의 위치 완전 탐색
		System.out.println(result);
	}
}