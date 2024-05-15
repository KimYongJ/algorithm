// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	static int 		dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int 		Y, X, sum, max, nextX, nextY, map[][];
	static boolean 	flag, visit[][];
	static ArrayList<int[]> zeroList;
	public static int DFS(int y, int x) {
		if(visit[y][x]) 
			return 0;
		
		int cnt = 1;
		
		visit[y][x] = true;
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
	public static void findZeroPositions(int y, int x) {
		if(visit[y][x]) return;
		visit[y][x] = true;
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(nextY>=0 && nextX>=0 && nextY<Y && nextX<X && !visit[nextY][nextX]) 
			{
				if(map[nextY][nextX] == 0) {
					zeroList.add(new int[] {nextY, nextX});
					visit[nextY][nextX] = true;
				}
				else
					findZeroPositions(nextY, nextX);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y 		= Integer.parseInt(st.nextToken());
		X 		= Integer.parseInt(st.nextToken());
		map 	= new int[Y][X];
		visit	= new boolean[Y][X];
		zeroList= new ArrayList<>();
		
		for(int y=0; y<Y; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<X; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++)
				if(!visit[y][x] && map[y][x] != 0)  // 맵을 돌면서 2의 좌표를 담는다.
					findZeroPositions(y,x);

		int size = zeroList.size();
		int [] p1, p2;
		
		for(int i=0; i<size-1; i++) 
		{
			p1 = zeroList.get(i);
			map[p1[0]][p1[1]] = 1;
			
			for(int j=i+1; j<size; j++) 
			{
				
				p2 = zeroList.get(j);
				map[p2[0]][p2[1]] = 1;
				
				visit		= new boolean[Y][X];
				sum			= 0;
				for(int y=0; y<Y; y++)
					for(int x=0; x<X; x++) 
						if(map[y][x] == 2 && !visit[y][x]) 
						{
							flag	= true;
							int num = DFS(y,x);
							if(flag) 
								sum += num;
						}
				max = Math.max(max, sum);
				
				map[p2[0]][p2[1]] = 0;
			}
			map[p1[0]][p1[1]] = 0;
		}

		System.out.print(max);
	}
}