// https://github.com/KimYongJ/algorithm

import java.util.ArrayList;
import java.util.PriorityQueue;

class Node{
	int node, dist;
	Node(int node, int dist){
		this.node = node;
		this.dist = dist;
	}
}
class Main{
	
	static final int INF = 10_000_000; 						// 최댓값
	static int max_dist;
	static int N, M, G; 									//  순서 : 노드갯수(천이하), 간선갯수(만이하), 목표노드
	static int a, b, d; 									//  순서 : 시작노드, 종료노드, 걸리는시간(100이하)
	static int dist_GtoA[]; 								// 목표지점에서 모든 경로로 가는 거리
	static int dist_AtoG[]; 								// 모든 경로에서 목표지점으로 가는 거리
	static ArrayList<Node>[] forward_direction;				// 정방향 간선
	static ArrayList<Node>[] reverse_direction;				// 역방향 간선
	static PriorityQueue<Node> pq;
	
	// 빠른 입력을 위한 함수
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
    // 최단거리를 구할 함수
	public static int Dijkstra(int goal, int[] dist, ArrayList<Node>[] list) {
		pq = new PriorityQueue<Node>((a,b)->a.dist-b.dist); // 거리기준 내림차순 정렬 큐 선언
		pq.add(new Node(goal,0));
		
		dist[goal] 					= 0;					// 시작 값 셋팅
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			for(Node next_Node : list[now.node]) {
				int nextNode 		= next_Node.node;
				int nextDist 		= next_Node.dist;
				int distSum 		= dist[now.node] + nextDist;
				if(dist[nextNode] > distSum) {
					dist[nextNode] 	= distSum;
					pq.add(new Node(nextNode, distSum));
				}
			}
		}
		return dist[goal];
	}
	public static void main(String[] args)throws Exception{
		N 							= read();
		M 							= read();
		G 							= read();
		dist_GtoA 					= new int[N+1];			// 정방향( 목표노드부터 모든 지점까지 거리 )
		dist_AtoG 					= new int[N+1];			// 역방향( 모든노드에서 목표노드까지 거리 )
		forward_direction 			= new ArrayList[N+1]; 	// 정방향 간선
		reverse_direction 			= new ArrayList[N+1]; 	// 역방향 간선

		for(int i=0; i<=N; i++) {
			dist_GtoA[i]			= INF;
			dist_AtoG[i] 			= INF;
			forward_direction[i] 	= new ArrayList<Node>();
			reverse_direction[i] 	= new ArrayList<Node>();
		}

		for(int i=0; i<M; i++) {
			a 						= read();
			b 						= read();
			d 						= read();
			forward_direction[a].add(new Node(b,d)); 		// 간선을 정상적으로 셋팅
			reverse_direction[b].add(new Node(a,d)); 		// 간선을 역방향으로 셋팅 
		}
		
		Dijkstra(G, dist_GtoA,forward_direction);			// G에서 모든 경로로 가는 거리
		Dijkstra(G, dist_AtoG,reverse_direction); 			// 모든 경로에서 G로 가는 거리 
		
		for(int i=1; i<N+1; i++) {
			if(i==G) continue;
			if(max_dist < dist_GtoA[i] + dist_AtoG[i]) 
				max_dist = dist_GtoA[i] + dist_AtoG[i];
		}
		System.out.println(max_dist);
	}
}
