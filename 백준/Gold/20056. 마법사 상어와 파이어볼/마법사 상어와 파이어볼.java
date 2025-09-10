//https://www.acmicpc.net/problem/20056
//1초 512MB
//7 5 3 // 맵크기(4<=50), 초기 파이어볼 정보(0<=N^2), 이동횟수(1<=1000)
//1 3 5 2 4 // y, x, 질량(1<=1000), 속력(1<=1000), 방향(0<=7)
//2 3 5 2 6
//5 2 9 1 7
//6 2 1 3 5
//4 4 2 4 2
//남아있는 파이어볼 질양의 합 : 9


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main{
	
	static final int dxy[][] = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	static ArrayDeque<FireBall>[][] map, dummy;
	static int N, M, K;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 맵크기(4<=50)
		M = Integer.parseInt(st.nextToken());// 초기 파이어볼 정보(0<=N^2)
		K = Integer.parseInt(st.nextToken());// 이동횟수(1<=1000)
		map = new ArrayDeque[N][N];
		dummy = new ArrayDeque[N][N];
		
		for(int y=0; y<N; y++)
		{
			for(int x=0; x<N; x++)
			{
				map[y][x] = new ArrayDeque<>();
				dummy[y][x] = new ArrayDeque<>();
			}
		}
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());// 질량(1<=1000)
			int c = Integer.parseInt(st.nextToken());// 속력(1<=1000)
			int d = Integer.parseInt(st.nextToken());// 방향(0<=7)
			
			map[y][x].add(new FireBall(w,c,d));
		}
		
		while(K-->0)
		{
			move();
			sumAndDiv();
		}
		
		System.out.print(print());
	}
	static void move() {
		for(int y=0; y<N; y++)
		{
			for(int x=0; x<N; x++)
			{
				ArrayDeque<FireBall> q = map[y][x];
				while(!q.isEmpty())
				{
					FireBall f = q.poll();
					
					int ny = (y + (f.cnt*dxy[f.dir][0])) % N;
					int nx = (x + (f.cnt*dxy[f.dir][1])) % N;
					
					if(ny < 0) ny+=N;
					if(nx < 0) nx+=N;

					dummy[ny][nx].add(f);
				}
			}
		}
		// 이동한 위치를 map으로 다시 변경
		ArrayDeque<FireBall>[][] tmp = map;
		map = dummy;
		dummy = tmp;
	}
	static void sumAndDiv() {
		for(int y=0; y<N; y++)
		{
			for(int x=0; x<N; x++)
			{
				ArrayDeque<FireBall> q = map[y][x];
				int size = q.size();
				if(size < 2)
					continue;
				
				int weightSum = 0;
				int cntSum = 0;
				int odd = 0;
				
				while(!q.isEmpty())
				{
					FireBall f = q.poll();
					weightSum += f.weight;
					cntSum += f.cnt;
					if(f.dir % 2 != 0)
						odd++;
				}
				
				weightSum /= 5;
				cntSum /= size;
				
				if(weightSum <= 0)// 질량이 0이면 스킵
					continue;
				// 모두 홀수나 짝수인 경우 방향은 0부터 시작해서 짝수, 아니면 1부터 시작해서 홀수
				int d = (odd == size) || (odd == 0) ? 0 : 1;
				
				for(int i=d; i<=7; i+= 2)
					q.add(new FireBall(weightSum, cntSum, i));
			}
		}
	}
	static int print() {
		int sum = 0;
		
		for(int y=0; y<N; y++)
			for(int x=0; x<N; x++)
				while(!map[y][x].isEmpty())
					sum += map[y][x].poll().weight;

		return sum;
	}
	static class FireBall{
		int weight, cnt, dir;
		FireBall(int w, int c, int d){
			weight = w;
			cnt = c;
			dir = d;
		}
	}
}