// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int Y, X, idx, nY, nX, safe_zone,check[][];
	static int flag;// DFS를 돌면서 check배열에 넣을 숫자, 사이클마다 해당 숫자가 1씩증가하며 들어간다.
	static char map[][];
	static int dxy[][] = {{-1,0},{0,1},{1,0},{0,-1}}; // 위 오른쪽, 아래, 왼쪽 순
	public static int nextDir(char c) 
	{
	    switch (c) 
	    {
	        case 'U': return 0;
	        case 'R': return 1;
	        case 'D': return 2;
	        default: return 3;
	    }
	}
	public static void DFS(int y, int x) 
	{
		if(check[y][x] == 0)  		// 처음 방문하는 곳인 경우
		{
			check[y][x] = flag;
			idx 		= nextDir(map[y][x]);
			nY 			= y + dxy[idx][0];
			nX 			= x + dxy[idx][1];
			DFS(nY, nX);
		}
		else if(check[y][x] == flag) 	// 사이클을 발견한 경우
			safe_zone++;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		Y 					= Integer.parseInt(st.nextToken());
		X 					= Integer.parseInt(st.nextToken());
		check 				= new int[Y][X];
		map 				= new char[Y][X];
		
		// 명령어를 입력 받음
		for(int i=0; i<Y; i++)
			map[i] = br.readLine().toCharArray();
		
		for(int i=0; i<Y; i++)
			for(int j=0; j<X; j++)
				if(check[i][j]==0) // 방문한 적이 없을 때 DFS 진행
				{
					flag += 1;
					DFS(i, j);
				}

		System.out.println(safe_zone);
	}
	
}