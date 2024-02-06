// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;

class Main{
	
	static int X, Y, result1, result2, cnt, cnt_one, arr[][];
	static int nextX, nextY;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static boolean visit[][];
	static ArrayDeque<int[]> q1 = new ArrayDeque<>();
	static ArrayDeque<int[]> q2 = new ArrayDeque<>();
	// 빠른 입력을 위한 함수
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }
    // BFS를 돌 때 유효성 검사
	public static boolean validate(int x, int y) {
		return x>=0 && y>=0 && x<X && y<Y && !visit[y][x];
	}
	public static void BFS() {
		cnt = 0;
		while(!q1.isEmpty()) {
			int[] now = q1.poll();
			for(int i=0; i<4; i++) {
				nextX = now[0] + dx[i];
				nextY = now[1] + dy[i];
				if(validate(nextX, nextY)) {
					visit[nextY][nextX] = true; // 방문처리
					if(arr[nextY][nextX] == 0) {
						q1.add(new int[] {nextX,nextY}); // 0인 경우만 큐에 넣는다.
					}else if(arr[nextY][nextX] == 1) {
						cnt ++; // 1의 갯수 카운팅
						q2.add(new int[] {nextX,nextY});
					}
				}
			}
		}
		
		while(!q2.isEmpty()) 
			q1.add( q2.poll() );
		
		
		cnt_one = cnt; // 이전 1의 갯수를 구하기 위한 코드 
	}
	public static void main(String[] args)throws Exception{
		Y 		= read();
		X 		= read();
		arr 	= new int[Y][X];
		visit 	= new boolean[Y][X];
		for(int i=0; i<Y; i++) 
			for(int j=0; j<X; j++) {
				arr[i][j] = read();
				if(arr[i][j] == 1) cnt_one++;
			}

		q1.add(new int[] {0,0}); // 큐 2개중 먼저쓸 q1에 0,0을 입력
		while(cnt_one != 0) {
			result1++; // 총 카운팅 갯수
			result2 = cnt_one; // 종료직전의 1의 갯수
			BFS();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(result1-1).append('\n').append(result2);
		System.out.print(sb);
	}
}