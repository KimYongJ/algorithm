// https://github.com/kimyongj/algorithm
import java.util.ArrayDeque;
class Node{
	int y, x;
	Node(int y, int x){this.y=y; this.x=x;}
}
class Main{
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int Y, X, K, map[][], nextY, nextX;
	static boolean visit[][];
	static ArrayDeque<Node> q;
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static boolean BFS(int mid) { // mid 힘으로 몇개를 캘 수 있는지
		int cnt = 0;
		visit 	= new boolean[Y][X];
		q 		= new ArrayDeque<>();
		for(int y=0; y<Y; y++) {
			if(map[y][0] <= mid) {
				q.add(new Node(y,0));
				visit[y][0] = true;
				cnt++;
			}
			if(map[y][X-1] <= mid) {
				q.add(new Node(y,X-1));
				visit[y][X-1] = true;
				cnt++;
			} 
		}
		for(int x=1; x<X-1; x++) {
			if(map[0][x] <= mid) {
				q.add(new Node(0,x));
				visit[0][x] = true;
				cnt++;
			}
		}
		while(!q.isEmpty()) {
			Node now = q.poll();
			for(int xy[] : dxy) {
				nextY = now.y + xy[0];
				nextX = now.x + xy[1];
				if(nextY >=0 && nextX>=0 && nextY<Y && nextX<X 
					&& map[nextY][nextX] <= mid && !visit[nextY][nextX]) {
					if(++cnt >= K) 
						return true;
					visit[nextY][nextX] = true;
					q.add(new Node(nextY,nextX));
				}
			}
		}
		return cnt >= K;
	}
	public static void main(String[] args)throws Exception{
		Y = read();
		X = read();
		K = read();
		map = new int[Y][X];
		int left = 0, right = 0, mid;
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++) 
			{
				map[y][x] = read();
				right = Math.max(right, map[y][x]);
			}

		while(left < right) 
		{
			mid = (left + right) / 2; // 채굴기의 힘
			if(BFS(mid))
				right = mid;
			else
				left = mid + 1;
		}
		System.out.println(right);
	}
}