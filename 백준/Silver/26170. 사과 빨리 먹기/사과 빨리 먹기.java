//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/26170
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int map[][] = new int[5][5];
	static int minDist = 1<<30;
	
	public static void DFS(int y, int x, int appleCnt, int dist) {
		if(minDist <= dist)
			return;
		else if(appleCnt == 3)
		{
			minDist = Math.min(minDist, dist);
			return;
		}
		
		for(int xy[] : dxy)
		{
			int nextY = y + xy[0];
			int nextX = x + xy[1];
			if(0<=nextY && 0<=nextX && nextY<5 && nextX<5 && map[nextY][nextX] != -1)
			{
				int nextAppleCnt= appleCnt + map[nextY][nextX];
				int origin		= map[nextY][nextX];
				map[nextY][nextX] = -1;
				DFS(nextY, nextX, nextAppleCnt, dist + 1);	
				map[nextY][nextX] = origin;
				
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int y=0; y<5; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<5; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		map[y][x] = -1;
		DFS(y, x, 0, 0);		
		
		System.out.print(minDist == 1<<30 ? -1 : minDist);
	}
}