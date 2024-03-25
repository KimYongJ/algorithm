// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;

class Position{
	int y,x,k,dist;
	Position(int y, int x, int k, int dist){
		this.y=y; this.x=x; this.k=k;
		this.dist=dist;
	}
}
class Main
{	
	static int MAX_VALUE = 100_000_000;
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, K, nextY, nextX, nextDist, nextK, map[][];
	static int visit[][];
	static ArrayDeque<Position> q;
	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static int BFS() {
		visit[1][1] = 1;
		q.add(new Position(1,1,0,1));
		Position now;
		while(!q.isEmpty()) {
			now = q.poll();
			
			if(now.y==Y && now.x==X) // 종료 조건
				return now.dist;
			
			for(int xy[] : dxy) {
				nextY = xy[0] + now.y;
				nextX = xy[1] + now.x;
				nextDist = now.dist + 1;
				nextK = now.k + 1;
				if(map[nextY][nextX] == 0 && visit[nextY][nextX] > now.k) {
					visit[nextY][nextX] = now.k;
					q.add(new Position(nextY, nextX, now.k, nextDist ));
				}else if(visit[nextY][nextX] > nextK && map[nextY][nextX] == 1 && nextK <= K) {
					visit[nextY][nextX] = nextK;
					q.add(new Position(nextY, nextX, nextK, nextDist));
				}
			}
		}
		return -1;
	}
	public static void main(String[] args)throws Exception{
		Y 		= read();
		X 		= read();
		K 		= read();
		map 	= new int[Y+2][X+2];
		visit 	= new int[Y+2][X+2];
		q 		= new ArrayDeque<>();
		for(int y=1; y<=Y; y++) 
		{
			for(int x=1; x<=X; x++) {
				map[y][x] = System.in.read()-'0';
				visit[y][x] = MAX_VALUE;
			}
			System.in.read();
		}
		
		for(int y=0; y<Y+2; y++)
			visit[y][0] = visit[y][X+1] = 
			map[y][0] = map[y][X+1] = -1;	// 패딩 삽입
			
		for(int x=0; x<X+2; x++)
			visit[0][x] = visit[Y+1][x] =
			map[0][x] = map[Y+1][x] = -1;	// 패딩 삽입
		
		System.out.print( BFS() );
	}
}

