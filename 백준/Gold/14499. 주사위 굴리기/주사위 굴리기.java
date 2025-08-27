//https://www.acmicpc.net/problem/14499
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static final int dirInfo[][][] = {
											{{1,1},{1,2},{3,1},{1,0}},// 동
											{{1,1},{1,0},{3,1},{1,2}},// 서
											{{1,1},{0,1},{3,1},{2,1}},// 북
											{{1,1},{2,1},{3,1},{0,1}}// 남
									};
	static int dxy[][] = {{0,1},{0,-1},{-1,0},{1,0}};
	static int Y, X, ny, nx, k;
	static int[][] dice,map;

	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		ny = Integer.parseInt(st.nextToken());
		nx = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[Y][X];
		dice = new int[4][3];
		
		for(int y=0; y<Y; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<X; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		while(k-->0)
		{
			int m = Integer.parseInt(st.nextToken()) - 1;// 이동 명령어 : 동(1) 서(2) 북(3) 남(4)
			
			ny += dxy[m][0];// 다음 좌표 갱신
			nx += dxy[m][1];// 다음 좌표 갱신
			
			if(ny<0 || nx<0 || Y<=ny || X<=nx) // 좌표가 유효하지 않은 경우 좌표 원복 및 다음 연산 진행
			{
				ny -= dxy[m][0];
				nx -= dxy[m][1];
				continue;
			}
			
			move(m);// 주사위를 해당 방향으로 굴림
			changeNumber();// 바뀐 주사위의 바닥면 or 지도의 숫자 변경
			
			sb.append(dice[1][1]).append('\n');
		}
		
		System.out.print(sb);
	}
	static void move(int m) {
		int dir[][] = dirInfo[m];
		
		int last = dice[dir[3][0]][dir[3][1]];

		for(int i=3; i>0; i--)
			dice[dir[i][0]][dir[i][1]] = dice[dir[i-1][0]][dir[i-1][1]];
		
		dice[dir[0][0]][dir[0][1]] = last;
	}
	static void changeNumber() {
		if(map[ny][nx] == 0)// 이동한 칸에 쓰여 있는 수가 0이면, 주사위 바닥면에 쓰여 있는 숫자를 지도에 복사
		{
			map[ny][nx] = dice[3][1];
			return;
		}
		// 이동한 칸에 쓰여 있는 수가 0이 아니면 지도에 쓰여 있는 수가 주사위 바닥으로 복사되고 지도는 0이되면
		dice[3][1] = map[ny][nx];
		map[ny][nx] = 0;
	}
}