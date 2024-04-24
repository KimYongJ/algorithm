// https://github.com/kimyongj/algorithm
import java.util.PriorityQueue;
class Node{
	int y, x, z;
	Node(int y, int x, int z){
		this.y=y; this.x=x; this.z=z;
	}
}
class Main{
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, K, map[][], nextY, nextX, ans, cnt;
	static boolean visit[][];
	static PriorityQueue<Node> pq;
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		Y 		= read();
		X 		= read();
		K		= read();
		map 	= new int[Y][X];
		visit	= new boolean[Y][X];
		pq 		= new PriorityQueue<Node>((a,b)->a.z-b.z);
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++) 
				map[y][x] = read();
		
		for(int y=0; y<Y; y++) 
		{
			pq.add(new Node(y,	0,	map[y][0]));
			pq.add(new Node(y,	X-1,map[y][X-1]));
			visit[y][0] = visit[y][X-1] = true;
			
		}
		for(int x=1; x<X-1; x++) {
			pq.add(new Node(0,	x, 	map[0][x]));
			visit[0][x] = true;
		}
		
		while(true){
			Node now = pq.poll(); 
			ans = Math.max(ans, now.z);
			if(++cnt >= K) 
				break;
			
			for(int xy[] : dxy) {
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				if(nextY >=0 && nextX>=0 && nextY<Y && nextX<X 
					&& !visit[nextY][nextX]) {
					visit[nextY][nextX]= true; 
					pq.add(new Node(nextY,nextX, map[nextY][nextX]));
				}
			}
		}
		

		System.out.println(ans);
	}
}