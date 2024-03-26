// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;

class Point{
	int y,x, dist;
	Point(int y, int x, int dist){
		this.y=y; this.x=x; this.dist=dist;
	}
}
class Main
{	
	static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, P, nextY, nextX, nextDist, castleCnt, maxDist[], castle[];
	static char map[][];
	static ArrayDeque<Point> q[];
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static boolean BFS(int idx, ArrayDeque<Point> q, char num) {
		boolean flag = false;
		int size;
		for(int r=0; r<maxDist[idx]; r++) 
		{
			size = q.size();
			if(size == 0) 				// size가 0인 경우 break해줘야 시간 초과가 안남, maxDist[idx] 번까지 계속 for문을 돌면 시간 초과  
				break;
			for(int i=0; i<size; i++)  	// bfs를 돌 때 한칸 한칸씩 가야함, 하나의 연결이 maxDist까지 가면 안됨, 중간에 길을 막아버릴 수 있기 때문.
			{
				Point now = q.poll();
				nextDist = now.dist + 1;
				for(int xy[] : dxy) 
				{
					nextY = now.y + xy[0];
					nextX = now.x + xy[1];
					if(map[nextY][nextX] == '.') 
					{
						flag = true;
						castleCnt++;
						map[nextY][nextX] =num;
						castle[num-'0']++;
						q.add(new Point(nextY, nextX, nextDist));
					}
				}
			}
		}
		return flag;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb 	= new StringBuilder();
		Y 		= read();
		X 		= read();
		P 		= read();	// 성을 갖는 인원
		maxDist = new int[P+1];
		castle 	= new int[P+1];
		map		= new char[Y+2][X+2];
		q		= new ArrayDeque[P+1];

		for(int i=1; i<=P; i++) 
		{
			q[i]		= new ArrayDeque<>();
			maxDist[i] 	= read();
		}
		for(int y=1; y<=Y; y++) 
		{

			for(int x=1; x<=X; x++) 
			{
				map[y][x] = (char)System.in.read();
				if(map[y][x] != '.')			// 빈 공간이 아닐 때
				{
					castleCnt++;				// 반복문 탈출때 사용할 이동 불가능한 공간(나중에 이동 가능한 공간을 구히기위한)
					if(map[y][x] != '#') 
					{
						q[map[y][x]-'0'].add(new Point(y,x,0));// 숫자일 경우 해당 좌표를 큐에 넣는다.
						castle[map[y][x]-'0']++;
					}
				}
			}
			System.in.read();
		}
		boolean flag = true;
		while(flag && Y*X != castleCnt)
		{
			flag = false;
			for(int i=1; i<=P; i++)
				if( BFS(i, q[i], (char)('0' + i)) )
					flag = true;
		}
		for(int i=1; i<=P; i++)
			sb.append(castle[i]).append(' ');
		System.out.println(sb);
	}
}

