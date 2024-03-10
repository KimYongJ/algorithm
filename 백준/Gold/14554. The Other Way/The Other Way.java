// https://github.com/KimYongJ/algorithm

import java.util.ArrayList;
import java.util.PriorityQueue;

class Main{
	static final long MAX_VALUE = Long.MAX_VALUE;
	static int N, M, Start, End;
	static int dirCnt_dp[];
	static long dist[];
	static ArrayList<Node>[] adlist;
	static PriorityQueue<Node> q;
    static int read() throws Exception {	// 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static void Dijkstra() {
		q = new PriorityQueue<Node>((a,b)-> (int)(a.dist - b.dist));
		dist[Start] 		= 0;			// Start노드 까지 가는데 걸린 길이 0
		dirCnt_dp[Start] 	= 1;			// Start노드 까지 가는 방법 1
		q.add(new Node(Start, 0)); 			// Start까지 오는데 걸린 거리 0 초기화
		
		long nextDist;
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			if(now.node == End) return;
			
			for(Node next : adlist[now.node]) 
			{
				nextDist = now.dist + next.dist;	// 다음 노드까지가는데, now노드를 거쳐 가는 길이 
				if(dist[next.node] > nextDist) 		// 다음 노드까지가는 거리를 구해놓은 dist안의 값보다 now노드를 거쳐 nextNode로 가는거리가 더 짧을 때
				{
					dist[next.node] 		= nextDist;					// 최단거리 갱신
					dirCnt_dp[next.node] 	= dirCnt_dp[now.node];		// 해당 노드까지 가는데 걸린 갯수 바인딩
					q.add(new Node(next.node , nextDist));
				}
				else if(dist[next.node] == nextDist)// 노드까지 도달하는 거리가 같을 때는 경로가 하나 더 있다는 뜻이므로 해당 노드까지 오는데 드는 경로갯수 dp에 더해줌
					dirCnt_dp[next.node] =  (dirCnt_dp[next.node] + dirCnt_dp[now.node]) % 1_000_000_009;
			}
		}
		
	}
	public static void main(String[] args)throws Exception{
		N 			= read();
		M 			= read();
		Start 		= read();
		End 		= read();
		dirCnt_dp	= new int[N+1];
		dist		= new long[N+1];
		adlist 		= new ArrayList[N+1];

		for(int i=0; i<=N; i++) 
		{
			adlist[i] 	= new ArrayList<>();					// 인접 리스트 초기화
			dist[i] 	= MAX_VALUE;
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
		
		Dijkstra();
		
		System.out.println(dirCnt_dp[End]);
	}
}

class Node{
	int node; long dist;
	Node(int node, long dist){
		this.node = node; this.dist = dist;
	}
}