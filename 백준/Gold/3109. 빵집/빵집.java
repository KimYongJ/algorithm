// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int Y, X, nextY, nextX, pipe_cnt;
	static int dy[] = {-1,0,1};
	static char map[][];
	static boolean endCondition, visit[][];
	
	public static void DFS(int y, int x) 
	{
		// 마지막 직전에 도달했을 때 종료 
		if(x == X) 
		{
			endCondition = true;
			return;
		}
		for(int i=0; i<3; i++) 
		{
			nextY = y + dy[i];
			nextX = x + 1;
			if(nextY>=0 && nextY<Y && map[nextY][nextX]!='x' && !visit[nextY][nextX] && !endCondition)
			{
				visit[nextY][nextX] = true;
				DFS(nextY, nextX);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		Y 					= Integer.parseInt(st.nextToken());
		X 					= Integer.parseInt(st.nextToken());
		map 				= new char[Y][X];
		visit 				= new boolean[Y][X];
		// 값을 입력 받는다. 
		for(int i=0; i<Y; i++)
			map[i] = br.readLine().toCharArray();
		
		X--;
		for(int i=0; i<Y; i++) 
		{
			endCondition = false;
			DFS(i,0);
			if(endCondition)
				pipe_cnt++;
		}
		
		System.out.println(pipe_cnt);
	}
}