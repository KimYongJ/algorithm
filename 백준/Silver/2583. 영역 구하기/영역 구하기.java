// https://github.com/KimYongJ/algorithm
import java.util.PriorityQueue;

class Main{
	
	static int dxy[][] = {{0,1},{1,0},{-1,0},{0,-1}};
	static int Y, X, K, newX, newY;
	static boolean visit[][];
	static int cnt, area;
	public static void main(String[] args)throws Exception{
		PriorityQueue<Integer> q 	= new PriorityQueue<>(); // 오름차순 정렬
		StringBuilder sb 			= new StringBuilder();
		Y 							= read();
		X 							= read();
		K 							= read();
		visit 						= new boolean[X][Y];
		for(int i=0; i<K; i++) {
			int x1 = read();
			int y1 = Y-read();
			int x2 = read();
			int y2 = Y-read();
			for(int x=x1; x< x2; x++)
				for(int y=y2; y<y1; y++)
					visit[x][y] = true;
		}
		
		for(int x=0; x<X; x++)
			for(int y=0; y<Y; y++)
				if(!visit[x][y]) {		// 방문하지 않은 곳이라면
					area = 0;			// 넓이 초기화
					cnt += 1;			// 방문하지 않은 영역 +1
					DFS(x, y);			// DFS실행
					q.add( area );	// 넓이를 리스트에 담는다.
				}

		sb.append(cnt).append('\n');
		while(!q.isEmpty()) 
			sb.append(q.poll()).append(' ');
		
		System.out.println(sb);
	}
	// DFS 함수 실행
	public static void DFS(int x, int y) {
		visit[x][y] = true;				// 전달된 노드 방문처리
		area 		+= 1;				// 넓이 +1 처리
		for(int xy[] : dxy) { 			// 새로운 좌표 생성
			newX 	= x+xy[0];
			newY 	= y+xy[1];
			if( newX>=0 && newY>=0 && newX<X && newY<Y && !visit[newX][newY])// 좌표 유효성 검사
				DFS(newX, newY); 		// 좌표가 유효하다면 DFS진행 
		}
	}
	// 빠른 입력을 위한 함수
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

}