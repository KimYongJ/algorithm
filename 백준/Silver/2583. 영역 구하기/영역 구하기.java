import java.util.ArrayList;
import java.util.Collections;

class Main{
	
	static int dxy[][] = {{0,1},{1,0},{-1,0},{0,-1}};
	static int Y, X, K;
	static boolean visit[][];
	static int cnt, area;
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		Y = read();
		X = read();
		K = read();
		visit = new boolean[X][Y];
		for(int i=0; i<K; i++) {

			int x1 = read();
			int y1 = Y-read();
			int x2 = read();
			int y2 = Y-read();
			
			for(int x=x1; x< x2; x++)
				for(int y=y2; y<y1; y++)
					visit[x][y] = true;
		}
		ArrayList<Integer> list = new ArrayList<>();
		int cnt = 0;
		for(int x=0; x<X; x++)
			for(int y=0; y<Y; y++)
				if(!visit[x][y]) {
					area = 0;
					cnt +=1;
					DFS(x, y);
					list.add( area );
				}

		
		Collections.sort(list);
		
		sb.append(cnt).append('\n');
		for(int i=0; i<list.size(); i++) 
			sb.append(list.get(i)).append(' ');
		
		System.out.println(sb);
	}
	// DFS 함수 실행
	public static void DFS(int x, int y) {
		visit[x][y] = true; // 전달된 노드 방문처리
		area += 1;
		for(int xy[] : dxy) {
			int newX = x+xy[0];
			int newY = y+xy[1];
			if( newX>=0 && newY>=0 && newX<X && newY<Y && !visit[newX][newY])
				DFS(newX, newY);
		}
	}
	
	// 빠른 입력을 위한 함수
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

}