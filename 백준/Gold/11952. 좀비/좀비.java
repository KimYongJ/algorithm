// https://github.com/KimYongJ/algorithm

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;


class Node{
	int node; long cost;
	Node(int node, long cost){
		this.node = node;
		this.cost = cost;
	}
}
class Main{
	public static void main(String[] args)throws Exception{new Main().solution();}
	
	int N, M, K, S, p, q;
	long cost[], cost_base[];
	boolean visit[];
	HashSet<Integer> jombie;
	ArrayList<Integer>[] list;
	PriorityQueue<Node> pq;
	
    // 빠른 입력을 위해 만든 함수
    public static int read() throws Exception 
    {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
	// 최단 비용 구하는 알고리즘
	public void Dijkstra() 
	{
		pq.add(new Node(1,0));								// 큐에 1번 노드를 넣고, 1번노드까지 가는데 걸린 비용 0을 셋팅
		cost[0] 	= cost[1] 	= 0; 						// 초기 값 세팅
		visit[0] 	= visit[1] 	= true;						// 방문 체크 
		long nextCost;
		while(!pq.isEmpty()) 
		{
			Node now = pq.poll();
			
			
			
			for(int nextNode : list[now.node]) 
			{
				if(!visit[nextNode]) 
				{
					visit[nextNode] = true;
					nextCost = now.cost + cost_base[nextNode];
					
					if(cost[nextNode] > nextCost)				// nextNode노드 까지 가는 최단 비용 발견시
					{
						if(nextNode == N) 						// 종료 조건
						{
							System.out.println(now.cost);
							return;
						}
						cost[nextNode] = nextCost;				// 최단 비용 다시 세팅
						pq.add(new Node(nextNode, nextCost));	// 해당 노드 다시 넣기
					}
				}
			}
		}
	}
	public void Danger_BFS(int node) {
		boolean check[] = new boolean[N+1];
		ArrayDeque<Node> aq = new ArrayDeque<>();
		aq.add(new Node(node, 0));
		check[node] = true;
		long nextDist;
		while(!aq.isEmpty()) {
			Node now = aq.poll();
			
			nextDist = now.cost + 1;
			for(int nextNode : list[now.node]) {
				if(!check[nextNode] && nextDist <= S) {
					cost_base[nextNode] = q;
					check[nextNode] = true;
					aq.add(new Node(nextNode, nextDist));
				}
			}
		}
	}
// 위험 구역 DFS 방식체크
//	public void Danger_DFS(int depth, int node) 
//	{
//		if(depth == S) return;			// S값이면 종료
//		for(int nextNode : list[node])	// 인접 노드들 체크
//		{
//			if(!visit[nextNode])		// 좀비 노드가 아닐 때
//			{
//				cost_base[nextNode] = q;	// 위험한 도시 숙박료로 변경
//				Danger_DFS(depth+1, nextNode);
//			}
//		}
//	}
	public void solution() throws Exception{
		N 					= read();	// 도시의 수 
		M 					= read();	// 길의 수
		K 					= read();	// 좀비에게 점령당한 도시의 수
		S 					= read();	// 위험한 도시의 범위(좀비로 부터 S만큼 떨어진 곳은 비용이 q이다.)
		p 					= read();	// 안전한 도시의 숙박료
		q 					= read();	// 위험한 도시의 숙박료
		cost 				= new long[N+1];					// 최단비용을 담을 dist배열
		cost_base			= new long[N+1];					// 노드당 비용
		list 				= new ArrayList[N+1];				// 노드의 인접 리스트를 담을 배열
		jombie 				= new HashSet<>();					// 좀비 도시를 담을 배열
		visit				= new boolean[N+1];					// 다익스트라 알고리즘에서 방문한 노드 체크할 배열
		pq 					= new PriorityQueue<Node>((a,b)->(int)(a.cost-b.cost)); // 최단거리 구할 때 사용할 우선순위 큐 거리가 짧은 순으로 오름차순 정렬
		
		// 리스트 배열 초기화 
		for(int i=0; i<=N; i++) 
		{
			list[i] = new ArrayList<>();		// 인접 노드를 담을 리스트 초기화
			cost[i] = Long.MAX_VALUE;			// 최단 비용을 담을 배열 초기화
			cost_base[i] = p;					// 노드당 숙바비용을 안전한 도시로 기본 세팅
		}
		
		// 좀비 도시를 입력 받는다. 
		for(int i=0; i<K; i++) {
			int jombieNode = read();
			jombie.add(jombieNode);
			visit[jombieNode] = true;
		}
		
		
		// 도시의 정보를 입력 받는다.
		int a,b;			// 입력 받는 도시
		for(int i=0; i<M; i++) 
		{
			a = read();
			b = read();
			list[a].add(b);				// 양방향 연결
			list[b].add(a);				// 양방향 연결				
		}
		for(int node : jombie)			// 좀비로부터 S칸 떨어진 곳들 마킹
		{
			Danger_BFS(node);
		}
		Dijkstra();						// 최단비용 구하기 
	}
}