//https://www.acmicpc.net/problem/19237
//1초 512MB
//4 2 6 // 격자 크기(2<=20), 상어 수(2<=N^2), 냄세가 사라지는 초(1<=1000)
//1 0 0 0 // 격자의 모습이 주어지고 상어번호가 1번부터 주어집니다.
//0 0 0 0
//0 0 0 0
//0 0 0 2
//4 3 // 상어의 각 방향이 주어짐, 1,2,3,4는 각 상 하 좌 우를 의미
//1 2 3 4// 1번째 상어가 위를볼 때 우선순위
//2 3 4 1// 1번째 상어가 아래를 볼 때 
//3 4 1 2// 1번째 상어가 왼쪽을 볼 때
//4 1 2 3// 1번째 상어가 오른족을 볼 때
//1 2 3 4// 2번재 상어 정보 동일
//2 3 4 1
//3 4 1 2
//4 1 2 3
//1번 상어만 남게되는 초 : 26

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	
	static final int[][] dxy = {{-1,0},{1,0},{0,-1},{0,1}};
	static PriorityQueue<Shark>[][] map;
	static Shark[] shark;
	static int[][][] dir;// 각 상어마다의 우선 순위(상어인덱스 : 방향 : 우선순위)
	static int[][] smell;// 냄세가 사라지는 초
	static int[][] who;//어떤 상어인지
	static int N, M, K;
	static int sharkCnt;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 격자 크기(2<=20)
		M = Integer.parseInt(st.nextToken());// 상어 수(2<=N^2)
		K = Integer.parseInt(st.nextToken());// 냄세가 사라지는 초(1<=1000)
		sharkCnt = M;
		who = new int[N][N];
		smell = new int[N][N];
		dir = new int[M + 1][4][4];
		shark = new Shark[M + 1];
		map = new PriorityQueue[N][N];
		
		int pos[][] = new int[M + 1][2];// 상어의 방향을 입력 받고 그상어가 
		
		for(int y=0; y<N; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++)
			{
				who[y][x] = Integer.parseInt(st.nextToken());// 상어 순번, 0이면 없는것
				map[y][x] = new PriorityQueue<>();
				
				if(who[y][x] != 0)
				{
					pos[who[y][x]][0] = y;
					pos[who[y][x]][1] = x;
					smell[y][x] = K;// 냄세가 사라지는 초
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int rank=1; rank<=M; rank++) // 상어의 첫 방향을 입력 받음
		{
			int dir = Integer.parseInt(st.nextToken()) - 1; // 방향은 -1을 줄여서 받음
			int y = pos[rank][0];
			int x = pos[rank][1];
			shark[rank] = new Shark(y, x, rank, dir);
		}
		
		for(int rank=1; rank<=M; rank++)// 상어 순위
		{
			for(int i=0; i<4; i++)// 각 상어마다의 상하좌우
			{
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<4; j++)// 각 방향마다 탐색 우선순위
					dir[rank][i][j] = Integer.parseInt(st.nextToken()) - 1;// 각 랭크별 상하좌우 우선순위를 받음 -1로 받음
			}
		}
		
		
		int time = 0;
		while(++time<1001)
		{
			for(int i=1; i<=M; i++)
			{
				Shark s = shark[i];
				
				if(s.out)// 아웃당한 상어면 스킵
					continue;
				
				boolean move = false;
				int pref[] = dir[s.rank][s.dir]; // 현 방향으로 순환 우선순위
				// 냄세가 없는 칸 먼저 탐색
				for(int dirIdx : pref)
				{
					int ny = s.y + dxy[dirIdx][0];
					int nx = s.x + dxy[dirIdx][1];
					if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
					// 해당 좌표 냄세가 없으면 이동
					if(smell[ny][nx] < time)
					{
						s.y = ny;
						s.x = nx;
						s.dir = dirIdx;
						map[s.y][s.x].add(s);
						move = true;
						break;
					}
				}
				
				if(move)continue;
				
				// 자기 자신칸 탐색
				for(int dirIdx : pref)
				{
					int ny = s.y + dxy[dirIdx][0];
					int nx = s.x + dxy[dirIdx][1];
					if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
					if(who[ny][nx] == s.rank)
					{
						s.y = ny;
						s.x = nx;
						s.dir = dirIdx;
						map[s.y][s.x].add(s);
						break;
					}
				}
				
			}
			
			kickOffAndMark(time);
			
			if(sharkCnt == 1)break;
		}
		
		if(time > 1000)
			time = -1;
		
		System.out.print(time);
	}
	static void kickOffAndMark(int time) {
		for(int y=0; y<N; y++)
		{
			for(int x=0; x<N; x++)
			{
				if(smell[y][x] <= time) who[y][x] = 0;
				
				while(map[y][x].size() > 1) // 자기보다 순위가 큰 애들은 내쫓음
				{
					Shark lose = map[y][x].poll();
					lose.out = true;
					sharkCnt--;// 상어수를 줄임
				}
				
				if(map[y][x].size() == 1)
				{
					Shark win = map[y][x].poll();
					who[y][x] = win.rank;// 자기 마킹
					smell[y][x] = time + K;// 냄세가 사라지는 초 설정
				}
			}
		}
	}
	static class Shark implements Comparable<Shark>{
		int y, x;
		int rank;
		int dir;
		boolean out;
		Shark(int y, int x, int rank, int dir){
			this.y = y;
			this.x = x;
			this.rank = rank;
			this.dir = dir;
			this.out = false;
		}
		@Override
		public int compareTo(Shark o) {
			return o.rank - this.rank;
		}
	}

}