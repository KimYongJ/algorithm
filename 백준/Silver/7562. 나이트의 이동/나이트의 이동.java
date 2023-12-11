// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		new Solution().solution();
	}
}
class Solution{
 
	int T, N, arr[][], startY, startX, endY, endX;
	int dxy[][] = {{-1,-2},{-1,2},{-2,-1},{-2,1},{1,-2},{1,2},{2,-1},{2,1}};// 나이트 이동좌표
	boolean visit[][];
	ArrayDeque<Node> q; 	// BFS 진행할 큐 선언
	void solution() throws Exception{
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb 	= new StringBuilder();
		StringTokenizer st;
		T 					= Integer.parseInt(br.readLine());
		while(T-->0) {
			N 				= Integer.parseInt(br.readLine());
			arr 			= new int[N][N];
			visit			= new boolean[N][N];
			st 				= new StringTokenizer(br.readLine());
			startY 			= Integer.parseInt(st.nextToken()); 	// 시작점 입력
			startX 			= Integer.parseInt(st.nextToken()); 	// 시작점 입력
			st 				= new StringTokenizer(br.readLine());
			endY 			= Integer.parseInt(st.nextToken());	// 도착점 입력
			endX 			= Integer.parseInt(st.nextToken());	// 도착점 입력
			
			sb.append( BFS() ).append('\n');
		}
		System.out.println(sb);
	}
	int BFS() {
		int cnt = 0;
			if( !(startY == endY && startX == endX) ) {
			q = new ArrayDeque<Node>();
			q.add(new Node(startY,startX,0));
			
			Loop:
			while(!q.isEmpty()) {
				Node now = q.poll();
				if(!visit[now.y][now.x]) {
					visit[now.y][now.x] = true;
					for(int xy[] : dxy) {
						int newY = now.y + xy[0];
						int newX = now.x + xy[1];
						int newDist = now.dist + 1;
						if(newY == endY && newX == endX) {
							cnt = newDist;
							break Loop;
						}
						if(newY>=0 && newX>=0 && newY<N && newX<N) {
							q.add(new Node(newY, newX, newDist));
						}
					}
				}
			}
		}
		return cnt;
	}
	
	// 빠른 입력을 위한 함수
	int read() throws Exception{ 					
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
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

