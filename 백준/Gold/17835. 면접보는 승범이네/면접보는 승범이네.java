// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node{
	int node;
	long dist;
	Node(int node, long dist){
		this.node=node; this.dist=dist;
	}
}
public class Main {
	
	static final long MAX_DIST = 200_000_000_000L;
	static long dist[];
	static int N, M, K;
	static int farthestNode;
	static long farthestDistance = -1;
	static ArrayList<Node>[] adlist;
	static PriorityQueue<Node> pq;
	static boolean visit[];
	public static void Dijkstra() {
		long nextDist;
		while(!pq.isEmpty()){
			Node now = pq.poll();
			
			if(visit[now.node])continue;
			visit[now.node]= true;
			
			for(Node next : adlist[now.node]) {
				if(!visit[next.node]) {
					nextDist = now.dist + next.dist;
					if(dist[next.node]> nextDist) {
						dist[next.node] = nextDist;
						pq.add(new Node(next.node, nextDist));
					}
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(dist[i] > farthestDistance) {
				farthestDistance = dist[i];
				farthestNode = i;
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dist = new long[N+1];
		adlist = new ArrayList[N+1];
		visit = new boolean[N+1];
		pq = new PriorityQueue<Node>((a,b)->Long.compare(a.dist,b.dist));
		for(int i=0; i<=N; i++) {
			dist[i] = MAX_DIST;
			adlist[i] = new ArrayList<>();
		}
		int a,b,c;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adlist[b].add(new Node(a,c));	// 역방향으로 간선을 연결하여 다익스트라알고리즘을 실행
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			a = Integer.parseInt(st.nextToken());
			dist[a] = 0;				// 면접장까지 가는 거리 0 세팅 
			pq.add(new Node(a,0));		// 면접장까지 가는 거리 0 세팅
		}
		Dijkstra();
		System.out.print(
							new StringBuilder().append(farthestNode)
								.append('\n')
								.append(farthestDistance).toString()
						);
	}
}