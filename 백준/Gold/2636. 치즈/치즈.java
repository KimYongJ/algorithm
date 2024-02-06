import java.util.ArrayDeque;

class Main{
	
	static int X, Y, result1, result2, cnt, cnt_one, arr[][];
	static int nextX, nextY;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static boolean visit[][];
	static ArrayDeque<int[]> q = new ArrayDeque<>();
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
		q = new ArrayDeque<int[]>() {{add(new int[] {0,0});}};
		visit = new boolean[Y][X];
		cnt = 0;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			for(int i=0; i<4; i++) {
				nextX = now[0] + dx[i];
				nextY = now[1] + dy[i];
				if(validate(nextX, nextY)) {
					visit[nextY][nextX] = true; // 방문처리
					if(arr[nextY][nextX] == 0) {
						q.add(new int[] {nextX,nextY}); // 0인 경우만 큐에 넣는다.
					}else if(arr[nextY][nextX] == 1) {
						arr[nextY][nextX] = 0;// 1인 경우 0으로 바꾸고 
						cnt ++; // 1의 갯수 카운팅
					}
				}
				
			}
		}
		cnt_one = cnt; // 이전 1의 갯수를 구하기 위한 코드 
	}
	public static void main(String[] args)throws Exception{
		Y 	= read();
		X 	= read();
		arr = new int[Y][X];
		
		for(int i=0; i<Y; i++) 
			for(int j=0; j<X; j++) {
				arr[i][j] = read();
				if(arr[i][j] == 1) cnt_one++;
			}

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