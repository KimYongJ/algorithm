// https://github.com/KimYongJ/algorithm

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


class Main{
	static int N, M, TIME, exist[][];
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean flag, map[][], visit[][];
	static ArrayList<Point> list;
	// 빠른 입력을 위한 함수
	public static int read() throws Exception
	{ 			
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
	public static void DFS(int y, int x) {
		if(visit[y][x]) return; // 기방문은 스킵 
		visit[y][x] = true; // 방문 처리 
		
		for(int xy[] : dxy) {
			int ny = y + xy[0];
			int nx = x + xy[1];
			if(ny>=0 && nx>=0 && ny<N && nx<M && !visit[ny][nx]) {
				if(map[ny][nx]) { // 치즈가 있는 곳인 경우
					if(exist[ny][nx]++ == 1) {
						visit[ny][nx] = true; // 방문 처리를해 2번 방문하지 않도록함
						map[ny][nx] = false;// 치즈를녹임으로 써 연산을 줄임 
						flag = true;
					}
				}else DFS(ny,nx);
			}
		}
	}
	public static void main(String[] args)throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N 		= Integer.parseInt(st.nextToken());
		M 		= Integer.parseInt(st.nextToken());
		map 	= new boolean[N][M]; 	// 치즈가 있으면 true 없으면 false
		list 	= new ArrayList<>(); 	// 녹을 치즈의 좌표를 담을 리스트

		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++)
				map[i][j] = "1".equals(st.nextToken()); // 치즈가 있는경우(1) true 저장
		}
		
		while(true) {
			flag = false; //반복 종료할 flag
			visit = new boolean[N][M]; 	// 방문 배열 초기화
			exist = new int[N][M]; 		// 치즈가 공기랑 몇번 맞닿았는지 체크하는 배열
			list.clear();				// 리스트 초기화
			DFS(0,0);
			if(!flag) 					// 녹일 치즈가 없다면 종료 
				break;
			TIME++;
		}
		
		System.out.println(TIME);
		
	}
}