//https://www.acmicpc.net/problem/20165
//1초 512MB
//5 5 3 // 행열(1<=100) 순서, 라운드수(1<=10,000)
//1 1 1 1 1 // 도미노 길이 1<=5
//1 2 2 1 1
//3 1 2 2 2
//1 3 2 1 1
//1 3 3 1 1
//3 1 E // 라운드수 * 2 개수에 걸쳐 공격수와 수비수 행동이 주어짐
//3 5	// 행열의 도미노를 세움
//5 3 N // 행 열, 도미노를 넘어뜨리는 방향(E,W,S,N동서남북)
//3 3
//5 2 N
//3 1
//답
//11	// 공격수의 공격 점수 출력
//S F S S S // 넘어진 것은 F, 아니면 S 출력
//S F S S S
//S F S F S
//S F F S S
//S F F S S
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int score;
	static int Y, X, R;
	static int map[][];
	static boolean down[][]; // 넘어지면 true, 서있으면 false
	static int dxy[][] = {{0,1},{0,-1},{1,0},{-1,0}};// 동서남북
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y		= Integer.parseInt(st.nextToken());// 행(1<=100)
		X		= Integer.parseInt(st.nextToken());// 열(1<=100)
		R		= Integer.parseInt(st.nextToken());// 라운드수(1<=10,000)
		map		= new int[Y + 2][X + 2];
		down	= new boolean[Y + 2][X + 2];
		
		for(int y=1; y<=Y; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		
		while(R-->0)
		{
			st = new StringTokenizer(br.readLine());
			
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			
			if(!down[y][x])
			{
				if(c == 'E') moveE(y,x,0);
				else if(c == 'W') moveW(y,x,1);
				else if(c == 'S') moveS(y,x,2);
				else moveN(y,x,3);
			}
			
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			down[y][x] = false;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(score).append('\n');
		for(int y=1; y<=Y; y++)
		{
			for(int x=1; x<=X; x++)
				sb.append(down[y][x] ? 'F' : 'S').append(' ');
			sb.append('\n');
		}
		System.out.print(sb);
	}
	static void moveW(int y, int x, int dir)// 서쪽 이동
	{
		int limit = Math.max(1, x - map[y][x] + 1);
		while(limit <= x)
		{
			if(!down[y][x])
			{
				limit = Math.max(1, Math.min(limit,x - map[y][x] + 1));
				score++;
			}
			down[y][x] = true;
			x += dxy[dir][1];
		}
	}
	static void moveE(int y, int x, int dir)// 동쪽 이동
	{
		int limit = Math.min(X, x + map[y][x] - 1);
		while(limit >= x)
		{
			if(!down[y][x])
			{
				limit = Math.min(X, Math.max(limit,x + map[y][x] - 1));
				score++;
			}
			down[y][x] = true;
			x += dxy[dir][1];
		}
	}
	static void moveS(int y, int x,int dir)// 남쪽 이동
	{
		int limit = Math.min(Y, y + map[y][x] - 1);
		while(limit >= y)
		{
			if(!down[y][x]) // 서있으면 limit 갱신
			{
				limit = Math.min(Y, Math.max(limit,y + map[y][x] - 1));
				score++;
			}
			down[y][x] = true;// 현재 위치 넘어뜨림
			y += dxy[dir][0]; // 다음 Y좌표 계산
		}
	}
	static void moveN(int y, int x, int dir)// 북쪽 이동
	{
		int limit = Math.max(1,y - map[y][x] + 1);
		while(limit <= y)
		{
			if(!down[y][x]) // 서있으면 limit 갱신
			{
				limit = Math.max(1, Math.min(limit, y - map[y][x] + 1));
				score++;
			}
			down[y][x] = true;// 현재 위치 넘어뜨림
			y += dxy[dir][0]; // 다음 Y좌표 계산
		}
	}
}