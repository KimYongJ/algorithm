// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;

class Main{
	
	static int y, x, Y, X, STRIDE, map[][];
	static int dxy[][] = {{0,1},{1,0},{-1,0},{0,-1},{-1,-1},{-1,1},{1,1},{1,-1},};
	static ArrayDeque<Point> q;
	
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
    
	public static void BFS() 
	{
		while(!q.isEmpty()) 
		{
			Point now = q.poll(); 						// 큐에서 데이터를 뽑아 온다.
			
			int ny, nx, dist; 							// 새로만들 좌표
			for(int xy[] : dxy) 
			{
				ny = now.y + xy[0]; 					// 새로운 y좌표
				nx = now.x + xy[1]; 					// 새로운 x좌표
				dist = now.dist + 1;					// 해당 좌표까지 거리 
				
				if(ny>=0 && nx>=0 && ny<Y && nx<X)  	// 유효성 통과한 좌표
					if( map[ny][nx]==0 || (map[ny][nx] != 0 && map[ny][nx] > dist) ) // map에 이미 저장된 dist가 0이거나, 0이 아닌데 새로 구한 dist가 더작을 때
					{
						map[ny][nx] = dist; 			// 0일때는 첫 dist를, 0이 아니였을때는 더 작은 상어로부터 거리를 넣는다.
						q.add(new Point(ny,nx,dist));
					}
			}
		}
	}
	
	public static void main(String[] args)throws Exception{

		Y 		= read();
		X 		= read();
		map 	= new int[Y][X];
		q		= new ArrayDeque<Point>();
		
		for( y=0; y<Y; y++)
			for(x=0; x<X; x++) {
				map[y][x] = read();
				if(map[y][x] == 1) 		// 상어 장소를 만나면 큐에 상어 장소를 놓는다.
					q.add(new Point(y,x,1));
			}
		
		BFS();
		
		for(y=0; y<Y; y++)
			for(x=0; x<X; x++)
				if(map[y][x] > STRIDE)
					STRIDE = map[y][x];	// dp처럼 사용한 map에 저장된 상어로부터 거리 중 가장 긴 값을 찾아 넣는다.

		System.out.println(STRIDE-1);	// 상어로 부터 거리를 0부터 시작한게 아니라 1부터 시작하였으므로 1을 빼준다.
	}
}
class Point{
	int x, y, dist;
	Point(int y, int x, int dist){
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
}