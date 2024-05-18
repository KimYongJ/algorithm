// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int 		dxy[][][] = {
									{{-1, 0}, {0, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}},
									{{-1, -1}, {0, -1}, {1, -1}, {1, 0}, {0, 1}, {-1 ,0}}
									 			
								};	
	static int 		Y, X, sum, nextY, nextX, map[][];
	static boolean 	visit[][];

	
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X 		= Integer.parseInt(st.nextToken());
		Y 		= Integer.parseInt(st.nextToken());
		map 	= new int[Y][X];
		visit 	= new boolean[Y][X];

		for(int y=0; y<Y; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<X; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++)
				if((y==0 || x==0 || y==Y-1 || x==X-1) && map[y][x] == 0 && !visit[y][x]) 
					DFS(y,x);// 외부 0과 연결된 숫자들에 visit체크 
		
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++)
				if(map[y][x] == 0 && !visit[y][x])
					map[y][x] = 1;
				
		int type;
		for(int y=0; y<Y; y++) 
		{
			type = y%2;
			for(int x=0; x<X; x++)
				if(map[y][x] == 1)
					for(int xy[] : dxy[type]) 
					{
						nextY = y + xy[0];
						nextX = x + xy[1];
						if(nextY>=0 && nextX>=0 && nextY<Y && nextX<X) 
						{
							if(map[nextY][nextX] == 0)
								sum++;
						}else sum++;
					}
		}
		
		System.out.println(sum);
	}
	
	public static void DFS(int y, int x) {
		if(visit[y][x]) return;
		visit[y][x] = true;
		
		for(int xy[] : dxy[y%2]) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(nextY>=0 && nextX>=0 && nextY<Y && nextX<X &&
				map[nextY][nextX] == 0 && !visit[nextY][nextX])
					DFS(nextY, nextX);
		}
	}
	
}