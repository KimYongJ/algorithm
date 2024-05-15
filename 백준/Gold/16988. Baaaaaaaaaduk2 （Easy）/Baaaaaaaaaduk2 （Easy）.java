// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	static int 		dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int 		Y, X, sum, max, nextX, nextY, map[][];
	static boolean 	flag, visit[][];
	public static int DFS(int y, int x) {
		if(visit[y][x]) return 0;
		visit[y][x] = true;
		int cnt = 1;
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(nextY>=0 && nextX>=0 && nextY<Y && nextX<X) 
			{
				if(map[nextY][nextX] == 0) 
					flag = false; 
				else if(map[nextY][nextX] == 2 && !visit[nextY][nextX])
					cnt += DFS(nextY, nextX);
			}
		}
		return cnt;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y 		= Integer.parseInt(st.nextToken());
		X 		= Integer.parseInt(st.nextToken());
		map 	= new int[Y][X];
		
		for(int y=0; y<Y; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<X; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++) 
				if(map[y][x] == 0) 
				{
					map[y][x] = 1;
					for(int y1=y; y1<Y; y1++)
						for(int x1=0; x1<X; x1++) 
							if(map[y1][x1] == 0) 
							{
								map[y1][x1] = 1;
								visit		= new boolean[Y][X];
								sum			= 0;
								for(int i=0; i<Y; i++) {
									for(int j=0; j<X; j++) {
										if(map[i][j] == 2 && !visit[i][j]) 
										{
											flag	= true;
											int num = DFS(i,j);
											if(flag) 
												sum += num;
										}
									}
								}
								max = Math.max(max, sum);
								map[y1][x1] = 0;
							}
					map[y][x] = 0;
				}
	
		System.out.print(max);
	}
}