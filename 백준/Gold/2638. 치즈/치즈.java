// https://github.com/KimYongJ/algorithm

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;


class Main{
	static int N, M, TIME, exist[][];
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean map[][], visit[][];
	static ArrayList<Point> list;
	static ArrayDeque<Point> q;
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
	
	public static void BFS() {
		q.add(new Point(0,0)); // 0,0은 항상 공기이기 때문에 넣어줌
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			if(visit[now.y][now.x])continue;		// 기방문 장소면 스킵
			visit[now.y][now.x]= true; 				// 방문처리  
			for(int xy[] : dxy) {
				int y = now.y + xy[0];
				int x = now.x + xy[1];
				
				if(y>=0 && x>=0 && y<N && x<M && !visit[y][x]){
					if(map[y][x]) { 				// 치즈라면 맞닿은 곳이기 때문에 exist에 +1
						if(exist[y][x] == 1)		// 치즈가 이미 맞닿은 적이 있다면 list에 넣음
							list.add(new Point(x,y));
						exist[y][x]++;
					}else {
						q.add(new Point(x,y)); 		// 방문하지 않았고, 치즈도 아닌 경우 큐에 넣음
					}
				}
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
		q 		= new ArrayDeque<>(); 	// BFS실행할 큐
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++)
				map[i][j] = "1".equals(st.nextToken()); // 치즈가 있는경우(1) true 저장
		}
		
		while(true) {
			visit = new boolean[N][M]; 	// 방문 배열 초기화
			exist = new int[N][M]; 		// 치즈가 공기랑 몇번 맞닿았는지 체크하는 배열
			q.clear();					// 큐 초기화
			list.clear();				// 리스트 초기화
			BFS();
			if(list.size() == 0) 		// 녹일 치즈가 없다면 종료 
				break;
			else // 녹일 치즈가 있다면
				for(Point l : list)
					map[l.y][l.x] = false; // 치즈를 녹인다.
			TIME++;
		}
		
		System.out.println(TIME);
		
	}
}