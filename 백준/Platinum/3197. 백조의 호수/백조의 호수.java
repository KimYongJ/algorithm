// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Point{
	int y, x;
	Point(int y, int x){
		this.y=y; this.x=x;
	}
}
class Main{
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, DAY, nextY, nextX, goal[][];
	static char map[][];
	static boolean swanVisit[][];
	static ArrayDeque<Point> meltQ, swanQ, nextQ;

	public static void findSwan() 
	{
		nextQ = new ArrayDeque<>();
		
		while(!swanQ.isEmpty()) 
		{
			Point now = swanQ.poll();
			
			if(now.y == goal[1][0] && now.x == goal[1][1]) 
			{
				System.out.println(DAY);
				System.exit(0);
			}
			
			for(int xy[] : dxy) {
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				if(!swanVisit[nextY][nextX]) 
				{
					swanVisit[nextY][nextX] = true;
					if(map[nextY][nextX] == 'X')				// X를 만나면 그 장소를 다음 백조찾을때 연산하도록 nextQ에 넣는다. 이때 X를 . 으로 바꿀피요는 없다. melt에서 녹일 것이기 때문 
						nextQ.add(new Point(nextY, nextX));
					else
						swanQ.add(new Point(nextY, nextX));
				}
			}
		}
		swanQ = nextQ;
	}
	public static void melt() {
		int size = meltQ.size();						// 큐에 현재 있는 만큼만 녹이도록 size에 크기 저장
		for(int i=0; i<size; i++) 
		{
			Point now = meltQ.poll();
			for(int xy[] : dxy) {
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				if(map[nextY][nextX] == 'X') {
					map[nextY][nextX] = '.'; 			// 녹임
					meltQ.add(new Point(nextY, nextX));	// 녹인 후 녹인 곳을 큐에 담아 다음 녹일 때 사용하도록 한다. 이 때 meltQ에 바로 담아도 상관없는 것이 이미 size만큼만 반복할 것이기 때문
				}
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y 			= Integer.parseInt(st.nextToken());
		X 			= Integer.parseInt(st.nextToken());
		goal 		= new int[2][2];
		map 		= new char[Y+2][X+2];
		swanVisit 	= new boolean[Y+2][X+2];
		meltQ 		= new ArrayDeque<>();
		swanQ 		= new ArrayDeque<>();
		for(int y=0; y<Y+2; y++)	// 패딩
		{
			map[y][0] 		= map[y][X+1] 		= 'W';
			swanVisit[y][0] = swanVisit[y][X+1] = true;
		}
		for(int x=0; x<X+2; x++)	// 패딩
		{
			map[0][x] 		= map[Y+1][x] 		= 'W';
			swanVisit[0][x] = swanVisit[Y+1][x] = true;
		}
			
		String str; char c; int idx = 0;
		for(int y=1; y<=Y; y++) 
		{
			str = br.readLine();
			for(int x=1; x<=X; x++) 
			{
				c = str.charAt(x-1);
				if(c=='L') 
				{
					c='.';
					goal[idx][0] = y;
					goal[idx++][1] = x;
				}
				if(c != 'X')
					meltQ.add(new Point(y,x));
				map[y][x] = c;
				
			}
		}
		swanQ.add(new Point(goal[0][0], goal[0][1]));
		swanVisit[goal[0][0]][goal[0][1]] = true;
		while(true) 
		{
			findSwan();
			melt();
			DAY++;
		}

	}
}