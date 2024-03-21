// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Objects;

class Position{
	int y, x;
	Position(int y, int x){
		this.y=y; this.x=x;
	}
	@Override
	public boolean equals(Object o) {
		Position o1 = (Position)o;
		return o1.y == y && o1.x==x;
	}
	@Override
	public int hashCode() {return Objects.hash(y,x);}
}

class Main
{	
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, year,nextY, nextX, map[][];
	static boolean visit[][];
	static HashSet<Position> pos;
	static ArrayDeque<Position> q, q1;
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static boolean check_BFS() {
		if(pos.size() == 0) 
			return false;
		Position first = pos.iterator().next();
		q1 			= new ArrayDeque<>();
		visit 		= new boolean[Y][X];
		visit[first.y][first.x] = true;
		q1.add(new Position(first.y,first.x));
		int cnt = 1;
		while(!q1.isEmpty()) {
			Position now = q1.poll();
			for(int xy[] : dxy) {
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				if(nextY>=0 && nextX>=0 && nextY<Y && nextX<X && !visit[nextY][nextX]
						&& map[nextY][nextX] > 0) {
					visit[nextY][nextX] = true;
					cnt++;
					q1.add(new Position(nextY,nextX));
				}
			}
		}
		return cnt == pos.size();
	}
	public static void melt_BFS() {
		int allwater, size = q.size();
		for(int i=0; i<size; i++) // 현재 큐 사이즈 만큼만 반복
		{
			Position now = q.poll();
			allwater = 0;
			for(int xy[] : dxy) {
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				if(nextY>=0 && nextX>=0 && nextY<Y && nextX<X) {
					if(map[nextY][nextX] > 0) {
						map[nextY][nextX] -= 1;
						if(map[nextY][nextX] == 0) {
							allwater++;
							q.add(new Position(nextY,nextX));
							pos.remove(new Position(nextY,nextX)); // set에서 0이된 좌표를 지워줌
						}
					}else 
						allwater++;
				}
			}
			if(allwater != 4)						// 주변이 전부 물이 아닌 경우 
				q.add(new Position(now.y, now.x));
		}
	}
	public static void setting_BFS(int y, int x) {
		boolean flag;
		q1 			= new ArrayDeque<>();
		visit[y][x] = true;
		q1.add(new Position(y, x));
		while(!q1.isEmpty()) {
			Position now = q1.poll();
			flag = false;
			for(int xy[] : dxy) {
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				if(nextY>=0 && nextX>=0 && nextY<Y && nextX<X && !visit[nextY][nextX]) { 
					if(map[nextY][nextX] == 0)				// 다음 좌표가 물이면 넣음
					{
						visit[nextY][nextX] = true;
						q1.add(new Position(nextY, nextX));
					}
					else flag = true;						// 다음 좌표가 빙산일 경우 체크
				}
			}
			if(flag)	// now노드 주변에 빙산이 있다면 큐에 삽입
				q.add(new Position(now.y,now.x));
		}
	}
	public static void main(String[] args)throws Exception{
		Y 	= read();
		X 	= read();
		map = new int[Y][X];
		pos = new HashSet<>();
		q 	= new ArrayDeque<>();
		for(int y=0; y<Y; y++) 
			for(int x=0; x<X; x++) 
			{
				map[y][x] = read();
				if(map[y][x] != 0)
					pos.add(new Position(y,x));	// 추후 연결이 되어있는지 체크할 때 사용
			}
		visit = new boolean[Y][X];
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++)
				if(map[y][x] == 0 && !visit[y][x])
					setting_BFS(y,x); 				// 빙산과 가까운 물의 좌표를 넣는다. 
		while(true)									// 큐가 빌 때 까지 반복 
		{
			year++;									// 1년 추가
			melt_BFS();								// 얼음을 녹인다.
			if(!check_BFS() )		                // 떨어진 부분이 있다면 break
				break;
		}
		System.out.println(q.isEmpty() || pos.size()==0 ? 0 : year);
	}
	
}

