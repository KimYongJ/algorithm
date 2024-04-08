// https://github.com/kimyongj/algorithm
import java.util.ArrayList;
import java.util.PriorityQueue;
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
    static int read() throws Exception {// 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static void Dijkstra() {
		dist[1] = 0;
		pq.add(new Node(1,0)); // 1번째 노드까지 0만큼 든다.
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(!visit[now.node]) 
			{
				visit[now.node] = true;		//큐에서 꺼낸 노드는 최단거리임이 보장된다. 
				for(Node next : adlist[now.node]) 
				{
					if(!visit[next.node]) 
					{
						nextDist = now.dist + next.dist;
						if(dist[next.node] > nextDist) 
						{
							dist[next.node]= nextDist;
							pq.add(new Node(next.node, nextDist));
						}
					}
				}
			}
		}
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		M 		= read();
		dist 	= new int[N+1];
		visit 	= new boolean[N+1];
		adlist	= new ArrayList[N+1];
		pq 		= new PriorityQueue<Node>((a,b)->a.dist-b.dist);
		for(int i=0; i<=N; i++) 
		{
			dist[i] 	= MAX;
			adlist[i] 	= new ArrayList<>();
		}
		int a,b,c;
		for(int i=0; i<M; i++) 
		{
			a 	= read();
			b 	= read();
			c 	= read();
			adlist[a].add(new Node(b,c));
			adlist[b].add(new Node(a,c));
		}
		Dijkstra();
		System.out.println(dist[N]);
	}
}