// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int node, dist;
	Node(int node, int dist){
		this.node=node; this.dist=dist;
	}
}
class Main{
	static final int MAX = 200_000_000;
	static int N, M, s, e, dist[];
	static boolean visit[];
	static ArrayList<Node>[] adlist;
	static PriorityQueue<Node> pq;
	public static void Dijkstra() {
		dist[s] = 0;
		pq.add(new Node(s,0));
		int nextDist;
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
		//	if(visit[now.node])continue;
		//	visit[now.node] = true;
			
			for(Node next : adlist[now.node]) {
				if(!visit[next.node]) {
					nextDist = next.dist + now.dist;
					if(dist[next.node] > nextDist) {
						dist[next.node] = nextDist;
						pq.add(new Node(next.node, nextDist));
//						if(next.node == e) {
//							System.out.println(nextDist);
//							return;
//						}
					}
				}
			}
		}
		System.out.println(dist[e]);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st	= new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());
		M 		= Integer.parseInt(st.nextToken());
		dist 	= new int[N+1];
		visit 	= new boolean[N+1];
		adlist 	= new ArrayList[N+1];
		pq 		= new PriorityQueue<Node>((a,b)->a.dist-b.dist);
		for(int i=1; i<=N; i++) {
			dist[i] 	= MAX;
			adlist[i] 	= new ArrayList<>();
		}
		int a,b,c;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adlist[a].add(new Node(b,c));
			adlist[b].add(new Node(a,c));
		}
		st	= new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		Dijkstra();
	}
}