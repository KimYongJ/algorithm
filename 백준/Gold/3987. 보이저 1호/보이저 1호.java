//https://www.acmicpc.net/problem/3987
//1초 128MB
//5 5 // 세로, 가로(1<=500)
//../.\ //  '/'와 '\'는 행성을, C는 블랙홀을, '.'는 빈 칸
//.....
//.C...
//...C.
//\.../
//3 3 // 탐사선 위치 세로, 가로가 주어짐
//시그널 방향 및 시간 출력(U, R, D, L)/ 무한 전파시 Voyager 출력
//U
//17
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	static int Y, X;
	static int sy, sx;
	static boolean [][][]visit;
	static char[][] map;
	static char[] dir = {'U','R','D','L'}; // 상 우 하 좌
	static int dxy[][] = {{-1,0},{0,1},{1,0},{0,-1}}; // 상 우 하 좌
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		map = new char[Y][X];
		visit = new boolean[4][Y][X];
		
		for(int y=0; y<Y; y++)
			map[y] = br.readLine().toCharArray();
		
		st = new StringTokenizer(br.readLine());
		sy = Integer.parseInt(st.nextToken()) - 1;
		sx = Integer.parseInt(st.nextToken()) - 1;
		
		int maxTime = -1;
		int maxDir = -1;
		
		for(int i=0; i<4; i++)
		{
			int time = getTime(0, i, sy, sx, clearVisit()); // 해당 방향으로 보냈을 때 시간
			
			if(time < 0)
			{
				System.out.print(new StringBuilder().append(dir[i]).append('\n').append("Voyager").toString());
				return;
			}
			if(maxTime < time)
			{
				maxTime = time;
				maxDir = i;
			}
		}
		
		System.out.print(new StringBuilder().append(dir[maxDir]).append('\n').append(maxTime).toString());
	}
	static int getTime(int time, int dir, int y, int x, boolean visit[][][]) {
		
		while(true)
		{
			if(visit[dir][y][x]) // 같은 방향으로 재진입시 -1 으로 반환
				break;
			
			visit[dir][y][x] = true;
			
			int ny = dxy[dir][0] + y;
			int nx = dxy[dir][1] + x;
			
			if(ny < 0 || nx < 0 || Y <= ny || X <= nx || map[ny][nx] == 'C')
				return time + 1;
			
			if(map[ny][nx] != '.')
				dir = nextDir(dir, map[ny][nx]); // 행성 만나면 다음 방향으로 전환
			
			time += 1;
			y = ny;
			x = nx;
		}
		
		return -1;
	}
	static boolean[][][] clearVisit(){
		for(int dir=0; dir<4; dir++)
			for(int y=0; y<Y; y++)
				Arrays.fill(visit[dir][y], false);
		return visit;
	}
	static int nextDir(int dir, char c)
	{
		// 상 우 하 좌
		if(dir == 0) return c == '/' ? 1 : 3;
		if(dir == 1) return c == '/' ? 0 : 2;
		if(dir == 2) return c == '/' ? 3 : 1;
		
		return c == '/' ? 2 : 0;
	}
}