//https://www.acmicpc.net/problem/20057
//1초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static final int dxy[][] = {{0,-1},{1,0},{0,1},{-1,0}};
	static final int rate[] = {0,2,10,7,1,5,10,7,1,2};
	static final int sxy[][][] = {
			{{0,-1},{-2,0},{-1,-1},{-1,0},{-1,1},{0,-2},{1,-1},{1,0},{1,1},{2,0}},
			{{1,0},{0,-2},{1,-1},{0,-1},{-1,-1},{2,0},{1,1},{0,1},{-1,1},{0,2}},
			{{0,1},{-2,0},{-1,1},{-1,0},{-1,-1},{0,2},{1,1},{1,0},{1,-1},{2,0}},
			{{-1,0},{0,2},{-1,1},{0,1},{1,1},{-2,0},{-1,-1},{0,-1},{1,-1},{0,-2}}
	};
	static int sand[][];
	static boolean visit[][];
	static int N, out;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		sand = new int[N][N];
		visit = new boolean[N][N];
		
		for(int y=0; y<N; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++)
				sand[y][x] = Integer.parseInt(st.nextToken());
		}

		int y = N / 2;
		int x = y;
		int dir = 0;
		
		while(!(y == 0 && x == 0))
		{
			visit[y][x] = true;
			
			y += dxy[dir][0];
			x += dxy[dir][1];
			
			sandSetting(dir, y, x);
			
			dir = nextDir(dir, y, x);
		}
		
		System.out.print(out);
	}
	static void sandSetting(int dir, int y, int x) {
		int xy[][] = sxy[dir];
		int s = sand[y][x];
		int useSand = 0;
		
		for(int j=1; j<xy.length; j++)
		{
			int ny = y + xy[j][0];
			int nx = x + xy[j][1];
			
			int nextSand = s * rate[j] / 100;
			
			if(ny>=0 && nx>=0 && ny<N && nx<N)
				sand[ny][nx] += nextSand;
			else
				out += nextSand;
			
			useSand += nextSand;
		}
		
		int remainSand = s - useSand;
		int ly = y + xy[0][0];
		int lx = x + xy[0][1];
		
		if(ly>=0 && lx>=0 && ly<N && lx<N)
			sand[ly][lx] += remainSand;
		else
			out += remainSand;
		
		sand[y][x] = 0;
	}
	static int nextDir(int dir, int y, int x) {
		int nextDir = (dir + 1) % 4;// 다음 방향
		
		int ny = y + dxy[nextDir][0];
		int nx = x + dxy[nextDir][1];
		
		if(visit[ny][nx])// 다음 좌표가 방문했다면 현재 방향 유지
			nextDir = dir;
		
		return nextDir;
	}
}