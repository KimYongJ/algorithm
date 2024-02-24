// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int Y, X,  map[][];
	static int dp [][];
	static int dxy[][] = {{-1,0},{1,0},{0,1},{0,-1}};
	
	// 후위 순회 방식을 통해 dp[y][x]의 가장 큰 값을 반환 
	public static int DFS(int y, int x) 
	{
		// dp[y][x]에 0이 아닌 값이 가장 큰 것이 보장된다. -1인 경우도 반환되게 하여 사이클 탐지
		if(dp[y][x] != 0 )
			return dp[y][x];
		
		dp[y][x] = -1; // 사이클 탐색을 위해 해당 위치에 마킹을 한다.
		
		int nextY, nextX, nextCnt, maxCnt = 0;
		for(int i=0; i<4; i++)
		{
			nextY = y + dxy[i][0] * map[y][x]; // 다음좌표
			nextX = x + dxy[i][1] * map[y][x]; // 다음좌표
			// 범위를 벗어나지 않고, 구멍이 아닌 경우
			if( !(nextY<0 || nextX<0 || nextY >=Y || nextX >= X || map[nextY][nextX] == 0)) 
			{
				// DFS 탐색으로 반환되는 값을 받아온다.
				nextCnt = DFS(nextY, nextX);
				if(nextCnt == -1) // -1을 만난 경우는 사이클이기 때문에 -1 반환 및 종료
					return -1;

				// 가장큰 nextCnt값을 담는다. 
				maxCnt = Math.max(maxCnt, nextCnt);
			}
		}
		
		return dp[y][x] = maxCnt + 1; // dp[y][x]에 가장큰 값을 저장하고 반환한다.
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		Y 					= Integer.parseInt(st.nextToken());
		X 					= Integer.parseInt(st.nextToken());
		map 				= new int[Y][X];
		dp					= new int[Y][X];
		
		for(int y=0; y<Y; y++) 
		{
			String str = br.readLine();
			for(int x=0; x<X; x++) 
			{
				int c = str.charAt(x);
				map[y][x] = c=='H' ? 0 : c-'0';
			}
		}
		System.out.println( DFS(0,0) );
	}
}