//https://www.acmicpc.net/problem/19238
//1초 512MB
//6 2 2 // 지도 크기(2<=20), 손님 수(1<=지도크기^2), 연료의 양(1<=500,000)
//0 0 1 0 0 0// 0은 빈칸 1은 벽을 나타냄
//0 0 1 0 0 0
//0 0 0 1 0 0
//0 0 0 1 0 0
//0 0 0 0 1 0
//0 0 0 1 0 0
//6 5 // 운전을 시작하는 행, 열번호가 주어짐(1<=N)
//6 6 5 6 // 승객 출발지 행과 열번호, 목적지의 행과 열번호가 주어짐
//4 6 4 5
//답 : 2

import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.ArrayDeque;

class Main{
	
	static final int dxy[][] = {{-1,0},{0,-1},{1,0},{0,1}};
	static boolean[][] isWall, visit, customer;
	static int [][]destinationY, destinationX;
	
	static ArrayDeque<Pos> q;
	static int y, x, gy, gx;
	static int N, M, F;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 지도 크기(2<=20)
		M = Integer.parseInt(st.nextToken());// 손님 수(1<=지도크기^2)
		F = Integer.parseInt(st.nextToken());// 연료의 양(1<=500,000)
		q = new ArrayDeque<>();
		isWall = new boolean[N + 2][N + 2];
		visit = new boolean[N + 2][N + 2];
		customer = new boolean[N + 2][N + 2]; // 손님이 있는 위치
		destinationY = new int[N + 2][N + 2];// 각 손님의 도착 행과 열번호 저장
		destinationX = new int[N + 2][N + 2];// 각 손님의 도착 행과 열번호 저장
		
		for(int i=0; i< N + 2; i++)
			isWall[i][N + 1] = isWall[N + 1][i] = isWall[0][i] = isWall[i][0] = true;
		
		for(int y=1; y<=N; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++)
				isWall[y][x] = "1".equals(st.nextToken());// 벽이면 true
		}
		
		st = new StringTokenizer(br.readLine());
		y = Integer.parseInt(st.nextToken());// 운전을 시작하는 행, 열번호가 주어짐(1<=N)
		x = Integer.parseInt(st.nextToken());// 운전을 시작하는 행, 열번호가 주어짐(1<=N)
		
		for(int i=1; i<=M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int sy = Integer.parseInt(st.nextToken());// 승객 출발지 행번호
			int sx = Integer.parseInt(st.nextToken());// 승객 출발지 열번호
			int ey = Integer.parseInt(st.nextToken());// 목적지의 행번호
			int ex = Integer.parseInt(st.nextToken());// 목적지의 열번호
			
			customer[sy][sx] = true;// 승객의 출발지에 마킹
			destinationY[sy][sx] = ey;
			destinationX[sy][sx] = ex;
		}
		
		while(M-->0)
		{
			if(!moveToPassenger() || !moveToDestination())
			{
				F = -1;
				break;
			}
		}
		
		System.out.print(F);
	}
	static boolean moveToDestination() {
		// 현 택시 위치에서 손님의 목적지까지 최단거리로 이동한다.
		clear();
		q.add(new Pos(y, x, 0));
		visit[y][x] = true;
		while(!q.isEmpty())
		{
			Pos now = q.poll();
			
			if(now.dist > F)// 연료를 다쓴 경우
				continue;
			
			if(now.y == gy && now.x == gx) // 목적지 도착한 경우
			{
				y = gy;// 택시의 시작 위치를 갱신
				x = gx;// 택시의 시작 위치를 갱신
				F += now.dist;// 이동거리의 2배를 연료로 충전이나 쓴연료까지하면 결국 dist플러스
				return true;
			}
			
			addQueue(now);
		}
		
		return false;
	}

	static boolean moveToPassenger() {
		// 현 위치에서 가장 가까운 승객의 위치를 찾아 택시를 그곳으로 이동하며, 연료또한 줄인다.
		clear();
		q.add(new Pos(y, x, 0));
		visit[y][x] = true;
		
		while(!q.isEmpty())
		{
			int size = q.size();
			int minY = 1<<30;
			int minX = 1<<30;
			int minD = q.peek().dist;
			
			if(minD > F)
				break;
			
			while(size-->0)
			{
				Pos now = q.poll();
				
				if(customer[now.y][now.x])
				{
					if(minY > now.y || (minY == now.y && minX > now.x))
					{
						minY = now.y;
						minX = now.x;
					}
				}
				
				addQueue(now);
			}
			
			if(minY != 1<<30)
			{
				y = minY;// 택시의 현재 위치 갱신
				x = minX;// 택시의 현재 위치 갱신
				gy = destinationY[minY][minX];// 택시의 도착 목적지 갱신
				gx = destinationX[minY][minX];// 택시의 도착 목적지 갱신
				customer[minY][minX] = false;// 손님 하나 제거
				F -= minD;// 현재까지 오는데 드는 연료 제거
				return F > 0;// 연료가 0보다 큰경우 true반환
			}
			
		}
		
		return false;
	}
	static void addQueue(Pos now) {
		for(int xy[] : dxy)
		{
			int ny = now.y + xy[0];
			int nx = now.x + xy[1];
			
			if(isWall[ny][nx] || visit[ny][nx])// 벽인 경우 or 방문한 경우 스킵
				continue;
			
			visit[ny][nx] = true;
			q.add(new Pos(ny, nx, now.dist + 1));
		}
	}
	static void clear() {
		q.clear();
		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
				visit[y][x] = false;
	}
	static class Pos{
		int y, x, dist;
		Pos(int y, int x, int dist){
			this.y=y;
			this.x=x;
			this.dist=dist;
		}
	}
}