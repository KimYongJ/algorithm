// https://github.com/kimyongj/algorithm

import java.util.ArrayList;
import java.util.PriorityQueue;

class Node{
	int node;
	long dist;
	Node(int node, long dist){
		this.node=node; this.dist=dist;
	}
}
public class Main {
	
	static final long MAX_DIST = 20_000_000_000L;
	static int N, M, K;
	static int farthestNode;
	static long dist[], farthestDistance = -1;
	static ArrayList<Node>[] adlist;
	static PriorityQueue<Node> pq;
	static boolean visit[];
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static void Dijkstra() {
		long nextDist;
		while(!pq.isEmpty()){
			Node now = pq.poll();
			
			if(visit[now.node])continue;
			visit[now.node]= true;
			if(farthestDistance < now.dist) {
				farthestDistance = now.dist;
				farthestNode = now.node;
			}else if(farthestDistance == now.dist) {
				if(farthestNode > now.node)
					farthestNode = now.node;
			}
			
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
		
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		M		= read();
		K 		= read();
		dist 	= new long[N+1];
		visit 	= new boolean[N+1];
		adlist 	= new ArrayList[N+1];
		pq 		= new PriorityQueue<Node>((a,b)->Long.compare(a.dist,b.dist));
		for(int i=0; i<=N; i++) {
			dist[i] 	= MAX_DIST;
			adlist[i] 	= new ArrayList<>();
		}
		int a,b,c;
		for(int i=0; i<M; i++) { 
			a = read();
			b = read();
			c = read();
			adlist[b].add(new Node(a,c));	// 역방향으로 간선을 연결하여 다익스트라알고리즘을 실행
		} 
		for(int i=0; i<K; i++) {
			a		= read();
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