// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;
import java.util.Arrays;

class Point{
	int m, n, direction, cmd_cnt;
	Point(int m, int n, int direction, int cmd_cnt){
		this.m = m;							// 세로 좌표 값
		this.n = n;							// 가로 좌표 값 
		this.direction = direction; 		// 해당 객체가 바라보는 방향
		this.cmd_cnt = cmd_cnt; 			// 해당 좌표까지 오는데 걸린 명령어 횟수
	}
}
class Main{
	static final int INF 	= Integer.MAX_VALUE;
	static int result 		= Integer.MAX_VALUE;
	static int dx[] 		= {1,-1,0,0};
	static int dy[] 		= {0,0,1,-1}; 
	static int M,N, visit[][][];
	static int endM, endN, endDir;
	static boolean map[][];					// 좌표의 갈수 있고, 없음을 표시하는 배열
	static ArrayDeque<Point> q = new ArrayDeque<>();
	
	static int read() throws Exception{ 	// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
	// 좌표 유혀성 검증 함수
	public static boolean position_validate(int m, int n) {
		return m>=0 && n>=0 && m<M && n<N;
	}
	// 목표 도착점까지 BFS 실행
	public static void BFS() {
		
		while(!q.isEmpty()) { 							// 큐가 빌 때까지 반복
			Point now = q.poll(); 						// 큐에서 데이터하나를 꺼낸다 
			
			// 종료 지점 도달시 종료 
			if(now.m == endM && now.n == endN) {
				if( endDir == now.direction ) { // 도착한 방향이 종료 방향과 같은경우
					result = Math.min(result,now.cmd_cnt);
				}else {
					if((now.direction == 0 && endDir==1) ||
						(now.direction== 2 && endDir==3)) {
						result = Math.min(result,now.cmd_cnt+2);
					}else {
						result = Math.min(result,now.cmd_cnt+1);
					}
				}
				continue;
			}
			
			if(visit[now.direction][now.m][now.n] < now.cmd_cnt)
				continue; 								// 해당좌표에 오기 까지 가장 작은 명령어 횟수가 아니면 연산제외
			
			visit[now.direction][now.m][now.n] = now.cmd_cnt; // 해당 좌표 최단명령어 갱신
			
			for(int i=0; i<4; i++) {
				int nextM = now.m;
				int nextN = now.n;
				int next_cmd_cnt = now.cmd_cnt;
				// 방향 전환시 명령어 카운트 +해주는 로직
				if(now.direction != i) {  				// 자기 자신일경우 방향전환은 안함
					if( (now.direction <2 && i<2) ||  // (동서)이거나 (남북)일 때 +2처리
						(now.direction >=2 && i>=2)) {
						next_cmd_cnt += 2;
					}else { 							
						next_cmd_cnt += 1;				//  나머지는 +1
					}
				}
				for(int j=0; j<3; j++) {
					nextM += dy[i]; 		// 새로운 좌표 계산 j가 3번반복되므로 1,2,3 이동에 대해 처리 가능
					nextN += dx[i]; 		// 새로운 좌표 계산 j가 3번반복되므로 1,2,3 이동에 대해 처리 가능
					
					if(position_validate(nextM, nextN) && map[nextM][nextN] &&
						visit[i][nextM][nextN] > next_cmd_cnt+1) {
						q.add(new Point(nextM, nextN, i, next_cmd_cnt+1));
					}else break;// 해당 좌표가 방문 불가인 경우 추가 연산은 불가하므로 반복문 탈출
					
				}
				
				
			}
		}
	}
	
	public static void main(String[] args)throws Exception{
		M 		= read();
		N 		= read();
		visit 	= new int[4][M][N]; 			// 해당 좌표당 가장 적은 명령어만 올 수 있도록 함
		map 	= new boolean[M][N]; 			// 좌표에 갈 수 있는지 없는지 체크, true면 갈수있음, false면 못감 
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = read()==0; 			// 해당 좌표가 0이면 true가 들어감
				visit[0][i][j] = INF;			// 방문 초기값을 모두 INF로 둠
			}
		}
		for(int k=1; k<4; k++)
			for(int i=0; i<M; i++)
				Arrays.fill(visit[k][i], INF);	// visit에 나머지 값 초기화
		
		endM 	= read()-1; 					// 시작 세로 값 입력받음
		endN 	= read()-1;						// 시작 가로 값 입력받음
		endDir 	= read()-1;						// 시작 방향 값 입력받음
		q.add(new Point(endM, endN, endDir, 0));// 시작 점을 큐에 넣음
		visit[endDir][endM][endN] = 0;			// 시작점 방문 횟수 0으로 처리
		endM 	= read()-1; 					// 종료 세로 값 입력받음
		endN 	= read()-1;						// 종료 가로 값 입력받음
		endDir 	= read()-1;						// 종료 방향 값 입력받음
		
		BFS();
		
		System.out.println(result);
	}

}