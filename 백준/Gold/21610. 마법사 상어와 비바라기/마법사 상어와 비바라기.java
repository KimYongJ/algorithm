//https://www.acmicpc.net/problem/21610
//1초 1024MB
//5 4 // 지도의 크기(2<=50), 이동 횟수(1<=100)
//0 0 1 0 2 // 초기 물의양이 제공됨
//2 3 2 1 0
//4 3 2 9 0
//1 0 2 9 0
//8 8 2 1 0
//1 3 // 이동명령 방향(1<=8), 이동칸수(1<=50)가 순서대로 주어집니다.
//3 4
//8 1
//4 8
//이동이 모두 끝난 후 바구니의 물의 합 : 77

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static final int[][] dxy = {{},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},};// 대각선 인덱스 : 2,4,6,8
	static boolean[][] isCloud, isCloudDummy;
	static int[][] map;
	static int N, M;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		isCloud = new boolean[N][N];
		isCloudDummy = new boolean[N][N];
		
		for(int y=0; y<N; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		
		isCloud[N-1][0] = isCloud[N-1][1] = isCloud[N-2][0] = isCloud[N-2][1] = true;
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());// 이동명령 방향(1<=8)
			int k = Integer.parseInt(st.nextToken());// 이동칸수(1<=50)가 순서대로 주어집니다.
			
			moveCloud(d, k);// 구름 이동 및 이동 장소에 물의 양 1증가
			inc();// 물이 증가한 칸의 물복사
			makeCloud();// 처음 구름을 제외한 모든 장소에 물의 양이 2이상인 곳에 구름을 만들고, 그곳의 물의 양이 2줄어든다.
		}
		System.out.print(sum());
	}
	static void moveCloud(int dir, int cnt) {
		for(int y=0; y<N; y++)
		{
			for(int x=0; x<N; x++)
			{
				if(!isCloud[y][x])// 구름이 아니면 제외
					continue;
				
				int xy[] = dxy[dir];
				int ny = (y + (xy[0]*cnt)) % N;
				int nx = (x + (xy[1]*cnt)) % N;
				
				if(ny < 0)ny += N;
				if(nx < 0)nx += N;
				
				isCloudDummy[ny][nx] = true;// 구름
				map[ny][nx] += 1;// 물 추가
			}
		}
		isCloud = isCloudDummy;
		
		isCloudDummy = new boolean[N][N];
	}
	static void makeCloud() {
		for(int y=0; y<N; y++)
		{
			for(int x=0; x<N; x++)
			{
				if(!isCloud[y][x] && map[y][x] >=2)//구름이 아니였고, 물이 2이상이면, 구름이 됨
				{
					map[y][x] -= 2;
					isCloud[y][x] = true;
				}
				else
					isCloud[y][x] = false;
			}
		}
	}
	static void inc() {
		for(int y=0; y<N; y++)
		{
			for(int x=0; x<N; x++)
			{
				if(!isCloud[y][x])// 구름이 아니였다면 제외
					continue;
				
				int cnt = 0;
				
				for(int i=2; i<=8; i+=2)
				{
					int ny = y + dxy[i][0];
					int nx = x + dxy[i][1];
					
					if(0<=ny && 0<=nx && ny<N && nx<N && 0 < map[ny][nx])
						++cnt;
				}
				
				map[y][x] += cnt;
			}
		}
	}
	static int sum() {
		int sum = 0;
		for(int y=0; y<N; y++)
			for(int x=0; x<N; x++)
				sum += map[y][x];
		return sum;
	}
}