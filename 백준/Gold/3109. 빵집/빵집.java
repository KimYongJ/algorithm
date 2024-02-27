// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int Y, X, nextY, nextX, pipe_cnt;
	static int dy[] = {-1,0,1};
	static boolean map[][];
	
	public static boolean DFS(int y, int x) 
	{
		// 마지막 직전에 도달했을 때 종료 
		if(x == X) 
		{
			pipe_cnt++;
			return true;
		}
		// x는 항상 +1 , y는 위, 중간, 아래로 탐색
		for(int i=0; i<3; i++) 
		{
			nextY = y + dy[i];
			nextX = x + 1;
			// 한번 간곳은 두번갈 필요 없다.
			if(map[nextY][nextX])
			{
				map[nextY][nextX] = false;// 방문처리
				if( DFS(nextY, nextX) )
					return true;
			}
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		Y 					= Integer.parseInt(st.nextToken());
		X 					= Integer.parseInt(st.nextToken());
		map 				= new boolean[Y+2][X+2];// 패딩 입력 받음
		// 값을 입력 받는다. 
		for(int i=1; i<=Y; i++) 
		{
			for(int j=1; j<=X; j++)
				map[i][j] = br.read() == '.'; 	// 갈 수 있으면 true
			br.readLine();						// 줄바꿈 버림처리
		}
		// 패딩을 제외한 행부터 DFS시작
		for(int i=1; i<=Y; i++) 
			DFS(i,1);
		
		System.out.println(pipe_cnt);
	}
}