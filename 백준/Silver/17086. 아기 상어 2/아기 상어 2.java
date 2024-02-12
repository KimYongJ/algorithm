import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


class Point{
	int x, y, dist;
	Point(int y, int x, int dist){
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
}
class Main{
	
	static int y, x, Y, X, STRIDE, map[][];
	static boolean  visit[][];
	static int dxy[][] = {{0,1},{1,0},{-1,0},{0,-1},{-1,-1},{-1,1},{1,1},{1,-1},};
	static ArrayDeque<Point> q;
	public static void BFS(int y, int x) {
		q 		= new ArrayDeque<>();
		visit	= new boolean[Y][X];
		q.add(new Point(y,x,0));
		
		while(!q.isEmpty()) {
			Point now = q.poll();

			
			if(map[now.y][now.x] == 1)// 상어를 만나면 종료 
			{
				STRIDE = Math.max(STRIDE, now.dist);
				break;
			}
			
			if(visit[now.y][now.x]) continue;
			visit[now.y][now.x] = true;
			
			int ny, nx, dist;
			for(int xy[] : dxy) 
			{
				ny = now.y + xy[0];
				nx = now.x + xy[1];
				dist = now.dist + 1;
				
				if(ny>=0 && nx>=0 && ny<Y && nx<X && !visit[ny][nx]) {
					q.add(new Point(ny,nx,dist));
				}
				
			}
			
			
		}
		
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		
		Y 		= Integer.parseInt(st.nextToken());
		X 		= Integer.parseInt(st.nextToken());
		map 	= new int[Y][X];
		
		
		for( y=0; y<Y; y++) {
			st = new StringTokenizer(br.readLine());
			for(x=0; x<X; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		
		for( y=0; y<Y; y++)
			for( x=0; x<X; x++)
				if(map[y][x] == 0) // 상어가 아닌 곳에서 부터 시작
					BFS(y,x);

		System.out.println(STRIDE);
	}
}