// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	// 위, 왼쪽위 순으로 반시계방향
	static final int dxy[][] = {{0,0},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
	static int result;
	
	public static boolean validate(int nextY, int nextX) {
		return nextY >=0 && nextX>= 0 && nextY<4 && nextX<4;
	}
	
	public static int[][][] copy(int a[][][]) {
		int x, y=0, b[][][] = new int[2][4][4];
		while(y<4) {
			x = 0;
			while(x<4) {
				b[0][y][x] = a[0][y][x];
				b[1][y][x] = a[1][y][x];
				x++;
			}
			y++;
		}
		return b;
	}
	public static void moveFish(int map[][][]) {
		Loop:
		for(int i=1; i<=16; i++)			// 물고기 번호를 1번부터 16번까지 탐색
		{
			for(int y=0; y<4; y++)			// i번 물고기를 찾기 위해 배열을 전부 탐색
			{
				for(int x=0; x<4; x++) 		// i번 물고기를 찾기 위해 배열을 전부 탐색
				{
					if(map[0][y][x] == i)	// i번 물고기를 찾은 경우
					{
						int dir = map[1][y][x];
						for(int c=0; c<8; c++) 
						{
							int nextY = y + dxy[dir][0];
							int nextX = x + dxy[dir][1];
							int n;
							if(validate(nextY,nextX) && map[0][nextY][nextX] >= 0) {
								if(map[0][nextY][nextX] == 0) {
									map[0][nextY][nextX] = map[0][y][x];
									map[0][y][x] = 0;
									map[1][nextY][nextX] = dir;
									map[1][y][x] = 0;
								}else {
									n = map[0][nextY][nextX];
									map[0][nextY][nextX] = map[0][y][x];
									map[0][y][x] = n;
									
									n = map[1][nextY][nextX];
									map[1][nextY][nextX] = dir;
									map[1][y][x] = n;
								}
								break;
							}
							if(++dir == 9)	// 반시계 방향 탐색
							{
								dir = 1;
							}
						}
						continue Loop;
					}
				}
			}
		}
	}
	
	public static void DFS(int map[][][], int sum, int y, int x, int sharkDir) {
		int newMap[][][] = copy(map);			// 물고기번호와 좌표 복사

		moveFish(newMap);						// 물고기 이동

		int nextY = y + dxy[sharkDir][0];		// 상어의 다음 위치 생성
		int nextX = x + dxy[sharkDir][1];		// 상어의 다음 위치 생성
		newMap[0][y][x] = 0;					// 상어자리의 상어 표시를 없앤다
		while(validate(nextY, nextX)) 
		{
			if(newMap[0][nextY][nextX] > 0)
			{
				int fishNum = newMap[0][nextY][nextX];
				
				newMap[0][nextY][nextX] = -1; 		// 먹힌 곳은 -1처리 
				DFS(newMap, sum + fishNum, nextY, nextX, newMap[1][nextY][nextX]);
				newMap[0][nextY][nextX] = fishNum;	// 백트레킹
			}
			nextY += dxy[sharkDir][0];
			nextX += dxy[sharkDir][1];
		}
		
		result = Math.max(result, sum);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int [][][]map = new int[2][4][4];

		for(int y=0; y<4; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<4; x++) 
			{
				map[0][y][x] = Integer.parseInt(st.nextToken()); // 물고기 점수
				map[1][y][x] = Integer.parseInt(st.nextToken()); // 물고기 위치
			}
		}
		int score = map[0][0][0];			// 물고기 점수
		map[0][0][0] = -1;					// 상어에게 먹힘을 표현, 상어는 -1
		DFS(map, score, 0, 0, map[1][0][0]);// 순서 : 맵, 먹은점수, y좌표, x좌표, 상어 방향
		
		System.out.print(result);
	}
}