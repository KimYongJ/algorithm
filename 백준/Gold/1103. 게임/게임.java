// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int Y, X, max, map[][];
	static int dp [][];
	static int dxy[][] = {{-1,0},{1,0},{0,1},{0,-1}};
	static boolean visit[][];
	
	public static void DFS(int y, int x, int depth, int dir) 
	{
		//범위를 벗어나거나 구멍인 경우
		if( y<0 || x<0 || y >=Y || x >= X || map[y][x] == 0)
		{
			if(max < depth) 
				max = depth;
			return;
		}
		// 기방문은 사이클이므로 -1 종료 
		if(visit[y][x]) 
		{
			System.out.println(-1);
			System.exit(0);
		}
		if(dp[y][x] >= depth)
			return;
		dp[y][x] = depth;
		visit[y][x] = true; // 방문처리
		int nextY, nextX, i=0;
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0] * map[y][x];
			nextX = x + xy[1] * map[y][x];
			DFS(nextY, nextX, depth + 1, i++);
		}
		visit[y][x] = false;// 백트레킹
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		Y 					= Integer.parseInt(st.nextToken());
		X 					= Integer.parseInt(st.nextToken());
		map 				= new int[Y][X];
		dp					= new int[Y][X];
		visit 				= new boolean[Y][X];
		
		for(int y=0; y<Y; y++) 
		{
			String str = br.readLine();
			for(int x=0; x<X; x++) 
			{
				int c = str.charAt(x);
				map[y][x] = c=='H' ? 0 : c-'0';
				dp[y][x] = -1;
			}
		}
		
		DFS(0,0,0,0);
		
		System.out.println(max);
	}
}