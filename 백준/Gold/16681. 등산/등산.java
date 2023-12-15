// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Node{
	int node;
	long dist;
	Node(int node, long dist){
		this.node = node;
		this.dist = dist;
	}
	
}
class Main{
	static final long INF 			= 10_000_000_000L;
	static final long MIN			= Long.MIN_VALUE;
	static long result		 		= MIN;
	static int N, M, D, E; 			// 순서 : 노드갯수(10만이하), 간선갯수(20만이하), 거리당 체력 소모량(100이하) , 높이당 성취감(100이하)
	static int a,b,d;				// 시작노드, 종료노드, 노드사이 거리 (10만이하)
	static int heights[]; 			// 노드당 높이
	static long dist_HtoG[]; 		// 시작점에서 모든 노드까지 최단 거리를 담을 배열
	static long dist_StoG[]; 		// 학교에서 모든 노드까지 최단 거리를 담을 배열
	static boolean visit[];
	static ArrayList<Node>[] list; 	// 간선 정보를 담을 Node 배열
	static PriorityQueue<Node> pq;
	public static void Dijkstra(int start, long dist[]) {
		visit = new boolean[N+1];
		pq = new PriorityQueue<Node>((a,b)->{
			if(a.dist-b.dist >= 0L)
				return 1;
			return -1;
		});
		pq.add(new Node(start,0L));
		dist[start] = 0;
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(visit[now.node])
				continue;
			
			visit[now.node]= true; 
			
			for(Node next : list[now.node]) {
				int nextNode = next.node;
				long nextDist = next.dist;
				
				if(!(heights[now.node]<heights[nextNode])) 
					continue;//항상 높이가 증가하는 방향으로 가야 한다.
				
				long distSum = dist[now.node] + nextDist;
				if(dist[nextNode] > distSum) {
					dist[nextNode] = distSum;
					pq.add(new Node(nextNode, distSum));
				}
			}
		}
		
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 노드갯수
		M = Integer.parseInt(st.nextToken()); // 간선갯수
		D = Integer.parseInt(st.nextToken()); // 거리당 체력 소모량
		E = Integer.parseInt(st.nextToken()); // 높이당 성취감
		heights = new int[N+1];
		dist_HtoG = new long[N+1];
		dist_StoG = new long[N+1];
		list = new ArrayList[N+1];
		
		for(int i=0; i<=N; i++) {
			dist_HtoG[i] = INF;
			dist_StoG[i] = INF;
			list[i] = new ArrayList<Node>();
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)  // 높이 셋팅 
			heights[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			list[a].add(new Node(b,d));
			list[b].add(new Node(a,d));
		}
		
		Dijkstra(1,dist_HtoG); // 집 노드부터 모든 정점까지 최단거리
		Dijkstra(N,dist_StoG); // 학교노드 부터 모든 정점까지 최단거리

		for(int i=2; i<N; i++) {
			if(dist_HtoG[i] != INF && dist_StoG[i] != INF) { 	// 목표노드까지 도달 못할 경우 연산하지 않음 
				long hg = (heights[i] * E) - (dist_HtoG[i]* D); // 시작에서 목표까지 가면서 얻은 가치 
				long sg = -dist_StoG[i]* D; 					// 학교에서 목표까지 소모한 체력 
				if(result < hg+sg) 
					result = hg+sg;
			}
		}
		
		if(result == MIN) {
			System.out.println("Impossible");
		}else {
			System.out.println(result);			
		}
	}
}