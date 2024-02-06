import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main{
	
	static int X, Y, result1, result2, cnt, cnt_one, arr[][];
	static int nextX, nextY;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static boolean visit[][];
	static ArrayDeque<Point> q = new ArrayDeque<>();
	public static boolean validate(int x, int y) {
		return x>=0 && y>=0 && x<X && y<Y && !visit[y][x];
	}
	public static void BFS() {
		q = new ArrayDeque<Point>() {{add(new Point(0,0));}};
		visit = new boolean[Y][X];
		cnt = 0;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			for(int i=0; i<4; i++) {
				nextX = now.x + dx[i];
				nextY = now.y + dy[i];
				if(validate(nextX, nextY)) {
					visit[nextY][nextX] = true;
					if(arr[nextY][nextX] == 0) {
						q.add(new Point(nextX,nextY)); // 0인 경우만 큐에 넣는다.
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		arr = new int[Y][X];
		
		for(int i=0; i<Y; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<X; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 1)
					cnt_one++;
			}
		}
		
		while(cnt_one != 0) {
			result1++;
			result2 = cnt_one;
			BFS();
		}
		System.out.println(result1-1);
		System.out.println(result2);
	}
}