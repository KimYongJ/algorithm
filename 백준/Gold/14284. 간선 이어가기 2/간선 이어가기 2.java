// https://github.com/kimyongj/algorithm
import java.util.ArrayList;
import java.util.PriorityQueue;
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
    static int read() throws Exception {// 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static void Dijkstra() {
		dist[s] = 0;
		pq.add(new Node(s,0));
		int nextDist;
		while(!pq.isEmpty())
		{
			Node now = pq.poll();
			
			if(now.node == e)break;
			if(visit[now.node])continue;
			visit[now.node] = true;
			
			for(Node next : adlist[now.node]) {
				if(!visit[next.node]) {
					nextDist = next.dist + now.dist;
					if(dist[next.node] > nextDist) {
						dist[next.node] = nextDist;
						pq.add(new Node(next.node, nextDist));
					}
				}
			}
		}
		System.out.println(dist[e]);
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		M 		= read();
		dist 	= new int[N+1];
		visit 	= new boolean[N+1];
		adlist 	= new ArrayList[N+1];
		pq 		= new PriorityQueue<Node>((a,b)->a.dist-b.dist);
		
		for(int i=1; i<=N; i++) 
		{
			dist[i] 	= MAX;
			adlist[i] 	= new ArrayList<>();
		}
		
		int a,b,c;
		for(int i=0; i<M; i++) 
		{
			a = read();
			b = read();
			c = read();
			adlist[a].add(new Node(b,c));
			adlist[b].add(new Node(a,c));
		}
		
		s = read();
		e = read();
		Dijkstra();
	}
}