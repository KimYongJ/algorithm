// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;

class Main{
	public static void main(String[] args)throws Exception{
		new Solution().solution();
	}
}
class Solution{
 
	int N, startY, startX, endY, endX;
	int dy[] = {-1,-1,-2,-2,1,1,2,2};
	int dx[] = {-2,2,-1,1,-2,2,-1,1};
	StringBuilder sb = new StringBuilder();
	
	// 빠른 입력을 위한 함수
	int read() throws Exception{ 					
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
	
	void BFS() {
		ArrayDeque<Node> q = new ArrayDeque<>();
		boolean visit[][] = new boolean[N][N];
		q.add(new Node(startY,startX,0));

		while(!q.isEmpty()) {
			Node now = q.poll();
			if(now.y == endY && now.x == endX) {
				sb.append(now.dist).append('\n');
				return;
			}
			if(!visit[now.y][now.x]) {
				visit[now.y][now.x] = true;
				for(int i=0; i<8; i++) {
					int newY = now.y + dy[i];
					int newX = now.x + dx[i];
					if(newY>=0 && newX>=0 && newY<N && newX<N && !visit[newY][newX]) {
						q.add(new Node(newY, newX, now.dist + 1));
					}
				}
			}
		}
	}
	void solution() throws Exception{
		int T 				= read();
		while(T-->0) {
			N 				= read();
			startY 			= read(); 	// 시작점 입력
			startX 			= read(); 	// 시작점 입력
			endY 			= read();	// 도착점 입력
			endX 			= read();	// 도착점 입력
			BFS();
		}
		System.out.println(sb);
	}	

}
class Node{
	int y, x, dist;
	Node(int y, int x, int dist) {
		this.y = y;
		this.x = x;
		this.dist = dist;
	}
}

