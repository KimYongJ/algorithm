// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node{
	int node, dist;
	Node(int node, int dist){
		this.node = node;
		this.dist = dist;
	}
}

class Main{
	
	static final int INF = 10_000_000; // 최댓값
	static int max_dist;
	static int N, M, G; //  순서 : 노드갯수(천이하), 간선갯수(만이하), 목표노드
	static int a, b, d; //  순서 : 시작노드, 종료노드, 걸리는시간(100이하)
	static int dist[];
	static ArrayList<Node>[] list;
	static PriorityQueue<Node> pq;
	public static int Dijkstra(int start, int goal) {
		pq = new PriorityQueue<Node>((a,b)->a.dist-b.dist); // 거리기준 내림차순 정렬 큐 선언
		Arrays.fill(dist, INF); // 거리 초기화
		dist[start] = 0;		// 시작 값 셋팅
		pq.add(new Node(start,0)); // start노드까지 가는 거리 0 셋팅
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			for(Node next_Node : list[now.node]) {
				int nextNode = next_Node.node;
				int nextDist = next_Node.dist;
				int distSum = dist[now.node] + nextDist;
				if(dist[nextNode] > distSum) {
					dist[nextNode] = distSum;
					pq.add(new Node(nextNode, distSum));
				}
			}
		}
		return dist[goal];
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		dist = new int[N+1];
		for(int i=1; i<=N; i++)
			list[i] = new ArrayList<Node>();

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			list[a].add(new Node(b,d));
		}

		for(int i=1; i<=N; i++) {
			if(i==G) continue; // 목표노드는 탐색하지 않음
			a = Dijkstra(i,G); // 시작노드에서 목표노드까지 가는 시간
			b = Dijkstra(G,i); // 목표노드에서 시작노드까지 오는 시간
			if(max_dist < a+b)
				max_dist = a+b;
			
		}
		System.out.println(max_dist);
	}
}
