// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;

class Main{
	
	static int 		dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int 		Y, X, sum, max, nextX, nextY, map[][];
	static boolean 	flag, visit[][];
	static ArrayList<int[]> zeroList;
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
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
		if(visit[y][x]) 
			return;
		
		visit[y][x] = true;
		
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(nextY>=0 && nextX>=0 && nextY<Y && nextX<X && !visit[nextY][nextX]) 
			{
				if(map[nextY][nextX] == 0)				// 0일 때만 위치 삽입 및 방문 처리
				{
					zeroList.add(new int[] {nextY, nextX});
					visit[nextY][nextX] = true;
				}
				else
					findZeroPositions(nextY, nextX);	// 1과 2일 때만 DFS 순회
			}
		}
	}
	public static void main(String[] args)throws Exception{
		Y 		= read();
		X 		= read();
		map 	= new int[Y][X];
		visit	= new boolean[Y][X];
		zeroList= new ArrayList<>();
		
		for(int y=0; y<Y; y++) 
			for(int x=0; x<X; x++)
				map[y][x] = read();
		
		
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++)
				if(!visit[y][x] && map[y][x] != 0)  // 맵을 돌면서 0의 좌표를 담는다.
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
				for(int y=0; y<Y; y++)			// 맵 전체를 돌면서 0이 주변에 하나도 없는 2의 개수를 합함
					for(int x=0; x<X; x++) 
						if(map[y][x] == 2 && !visit[y][x]) 
						{
							flag	= true;		// 최종적으로 더 해도되는지 판단할 플레그 
							int num = DFS(y,x); // 0이 주변에 하나도 없는 2의 개수
							if(flag) 			
								sum += num;		// flag가 true이면 0이 주변에 하나도 없다는 것이므로 더해줌
						}		
				max = Math.max(max, sum);		// max를 갱신
				
				map[p2[0]][p2[1]] = 0;
			}
			map[p1[0]][p1[1]] = 0;
		}

		System.out.print(max);
	}
}