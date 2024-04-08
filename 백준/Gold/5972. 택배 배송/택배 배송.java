// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node{
	int node, dist;
	Node(int node, int dist){this.node=node; this.dist=dist;}
}
class Main{
	static int MAX = 200_000_000;
	static int N, M, nextDist, dist[];
	static boolean visit[];
	static ArrayList<Node>[] adlist;
	static PriorityQueue<Node> pq;
	public static void Dijkstra() {
		dist[1] = 0;
		pq.add(new Node(1,0)); // 1번째 노드까지 0만큼 든다.
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(!visit[now.node]) {
				visit[now.node] = true;		//큐에서 꺼낸 노드는 최단거리임이 보장된다. 
				for(Node next : adlist[now.node]) {
					if(!visit[next.node]) {
						nextDist = now.dist + next.dist;
						if(dist[next.node] > nextDist) {
							dist[next.node]= nextDist;
							pq.add(new Node(next.node, nextDist));
						}
					}
				}
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st	= new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());
		M 		= Integer.parseInt(st.nextToken());
		dist 	= new int[N+1];
		visit 	= new boolean[N+1];
		adlist	= new ArrayList[N+1];
		pq 		= new PriorityQueue<Node>((a,b)->a.dist-b.dist);
		for(int i=0; i<=N; i++) {
			dist[i] 	= MAX;
			adlist[i] 	= new ArrayList<>();
		}
		int a,b,c;
		for(int i=0; i<M; i++) {
			st 	= new StringTokenizer(br.readLine());
			a 	= Integer.parseInt(st.nextToken());
			b 	= Integer.parseInt(st.nextToken());
			c 	= Integer.parseInt(st.nextToken());
			adlist[a].add(new Node(b,c));
			adlist[b].add(new Node(a,c));
		}
		Dijkstra();
		System.out.println(dist[N]);
	}
}