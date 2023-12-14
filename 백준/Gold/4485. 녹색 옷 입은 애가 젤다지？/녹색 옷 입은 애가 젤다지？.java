// https://github.com/KimYongJ/algorithm 

import java.util.PriorityQueue;
class Node{
	int y, x, dist;
	Node(int y, int x, int dist){
		this.y 		= y;
		this.x 		= x;
		this.dist 	= dist;
	}
}
class Main{
	static int INF 		= 150_000;
	static int N, idx 	= 1;
	static int dx[] 	= {0,0,-1,1};
	static int dy[] 	= {-1,1,0,0};
	static int arr[][], dist[][];
	static StringBuilder sb 	= new StringBuilder();
	
	// 빠른 입력을 위한 함수
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
	// 다익스트라 함수
	public static void Dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.dist-b.dist);
		dist[0][0] = arr[0][0];
		pq.add(new Node(0,0,arr[0][0]));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			for(int i=0; i<4; i++) {
				int nextY = now.y + dy[i]; // 다음 방문할 좌표
				int nextX = now.x + dx[i]; // 다음 방문할 좌표
				if(nextY>=0 && nextX>=0 && nextY<N && nextX<N) { // 좌표 유효성 검증
					int distSum = now.dist + arr[nextY][nextX]; // 꺼낸노드까지 오는데 걸린거리 + 다음 까지 거리
					if(dist[nextY][nextX] > distSum) { // 지금까지 구한 다음노드까지 거리보다 방금 구한것이 더작을 때 
						dist[nextY][nextX] = distSum;
						pq.add(new Node(nextY, nextX, distSum));
					}
					
				}
			}
		}
	}
	public static void main(String[] args)throws Exception{
		N 					= read();
		while(N!=0) {
			arr 			= new int[N][N]; // 입력되는 값을 담을 배열
			dist 			= new int [N][N]; // 최단거리를 담을 배열
			for(int i=0; i<N; i++)
				for(int j=0; j<N; j++) {
					arr[i][j] = read();
					dist[i][j] = INF;
				}
			
			Dijkstra(); // 최단거리를 구하는 알고리즘 

			sb.append("Problem ").append(idx++).append(": ")
			  .append(dist[N-1][N-1]).append('\n');
			N = read();
		}
		System.out.println(sb);
	}
}
