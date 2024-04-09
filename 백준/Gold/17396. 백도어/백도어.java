// https://github.com/kimyongj/algorithm
import java.util.ArrayList;
import java.util.PriorityQueue;
class Node{
	int node; long dist;
	Node(int node, long dist){this.node=node; this.dist=dist;}
}
class Main{
	static long MAX = 200_000_000_000L;
	static int N, M;
	static long dist[];
	static boolean visit[];
	static ArrayList<Node>[] adlist;
	static PriorityQueue<Node> pq;
    static int read() throws Exception {// 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static void Dijkstra() {
		long result = -1, 								// 도달불가시 -1출력을 해야함
			 nextDist;
		dist[0] = 0;
		pq.add(new Node(0,0));
		
		while(!pq.isEmpty()) 
		{
			Node now = pq.poll();
			if(now.node == N) 							// 목적지 도착시 break
			{
				result = now.dist;
				break;
			}
			if(visit[now.node])continue;				// 큐에서 나온 노드는 최단거리임이 보증된다.
			visit[now.node] = true; 					// 방문 처리 
			
			for(Node next : adlist[now.node]) 
			{
				if(next.node ==N || !visit[next.node]) 	// 다음 노드가 최종 노드가 아니며, 방문하지 않았을 때 연산 실행
				{
					nextDist = next.dist + now.dist;	// 거처서 가는게 더 빠른지 탐색
					if(dist[next.node]> nextDist) 
					{
						dist[next.node] = nextDist;
						pq.add(new Node(next.node, nextDist));
					}
				}
			}
		}
		System.out.println(result);
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		M 		= read();
		dist 	= new long[N];
		visit 	= new boolean[N];
		adlist 	= new ArrayList[N];
		pq 		= new PriorityQueue<Node>((a,b)->Long.compare(a.dist,b.dist));
		for(int i=0; i<N; i++) 
		{
			dist[i] 	= MAX;
			visit[i]	= read() == 1;			// 방문할 수 없으면 true처리
			adlist[i] 	= new ArrayList<>();
		}
		int a,b,c;
		for(int i=0; i<M; i++)
		{
			a = read();
			b = read();
			c = read();
			adlist[a].add(new Node(b,c));		// 양방향 연결
			adlist[b].add(new Node(a,c));
		}
		N--;									// 빠른 연산을 위해 N에 -1처리 
		Dijkstra();
	}
}