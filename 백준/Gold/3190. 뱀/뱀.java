//https://www.acmicpc.net/problem/3190
//1초 128MB
//6 // 보드 게임의 크기(2<=100)
//3 // 사과의 개수(0<=100)
//3 4 // K개 줄에 사과의 위치가 주어짐(행Y, 열X)
//2 5
//5 3
//3 // 뱀의 방향 변환 횟수 L(1<=100)
//3 D // 게임 시작 시간으로 부터 X(0<=10,000)초 후 왼쪽 혹은 오른쪽으로 90도 회전을 의미(L은 왼쪽 D는 오른쪽)
//15 L
//17 D
//게임이 끝나는 초 출력 : 9
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int dxy[][] = {{0,1},{1,0},{0,-1},{-1,0}};
	static int fy, fx;// 머리의 위치
	static int ly, lx;// 꼬리의 위치
	static int nowDir;// 현재의 방향
	static int flag;// 뱀이 이동한 방향에 넣을 증감 숫자
	static int time;// 현재 시간
	static int N;// 맵의 크기
	static int map[][];
	static char dirInfo[];// idx : 몇초인지 / value : 방향이 뭔지
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());// 보드게임크기 2<=100
		map = new int[N+2][N+2];
		dirInfo = new char[10_001];

		map[1][1] = ly = lx = flag = fy = fx = 1;// 모두다 1세팅
		
		int appleCnt = Integer.parseInt(br.readLine());// 사과 개수 0<=100
		while(appleCnt-->0)
		{
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			map[y][x] = -1; // 사과는 -1
		}
		
		int dirCnt = Integer.parseInt(br.readLine());// 뱀의 방향 전환 횟수(1<=100)
		while(dirCnt-->0)
		{
			st = new StringTokenizer(br.readLine());
			dirInfo[Integer.parseInt(st.nextToken())] = st.nextToken().charAt(0);
		}
		
		while(true)
		{
			setDir();// 해당 초에 방향 전환(0초부터 시작)
			
			++time;// 1초 증가
			
			if(!moveDir())// 해당 방향으로 한칸 이동
				break;

		}
		
		System.out.print(time);
	}
	static boolean moveDir() {
		fy += dxy[nowDir][0];// 한칸 이동
		fx += dxy[nowDir][1];// 한칸 이동
		
		if(fy == 0 || fx == 0 || N < fy || N < fx || map[fy][fx] > 0)
			return false;
		
		boolean isApple = map[fy][fx] < 0;// 이동 위치가 사과인지 체크
		
		map[fy][fx] = ++flag;// 이동 위치에 마킹
		
		if(isApple) // 사과면 연산 종료
			return true;
		
		int target = map[ly][lx] + 1;// 다음 꼬리번호
		map[ly][lx] = 0;// 꼬리쪽을 한칸 줄인다.
		
		for(int xy[] : dxy) // 다음 꼬리 위치 확인
		{
			int ny = ly + xy[0];
			int nx = lx + xy[1];
			if(map[ny][nx] == target)// 다음꼬리 위치 찾으면 마킹
			{
				ly = ny;
				lx = nx;
				break;
			}
		}
		
		return true;
	}
	static void setDir() {
		if(dirInfo[time] == 'L')
			nowDir--;
		else if(dirInfo[time] == 'D')
			nowDir++;
		
		if(nowDir < 0)
			nowDir = 3;
		if(nowDir == 4)
			nowDir = 0;
	}
}