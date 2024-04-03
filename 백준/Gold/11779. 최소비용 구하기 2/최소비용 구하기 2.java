// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
class Node{
	int node, dist;
	Node(int node, int dist){
		this.node = node; this.dist = dist;
	}
}
public class Main {
	static int MAX_DIST = 200_000_000;
	static int N, B, start, end, dist[], prevPath[];
	static ArrayList<Integer> path;
	static ArrayList<Node>[] adlist;
	static PriorityQueue<Node> pq;
	public static void DFS(int start) 
	{
		path.add(start);				// 경로에 전달된 노드 삽입
		if(prevPath[start] != 0)		// 이전 경로가 0이 아닌 경우 DFS 재탐색
			DFS(prevPath[start]);
	}
	public static void Dijkstra() {
		dist[start] = 0;
		pq.add(new Node(start, 0));
		Node now;
		while(!pq.isEmpty()) 
		{
			now = pq.poll();
			if(now.node == end)
				return;
			for(Node next : adlist[now.node]) 
			{
				int nextDist = now.dist + next.dist;
				if(dist[next.node] > nextDist)				// 다음 노드까지 가는데, 지금까지 구한 거리보다 now노드를 거쳐 next노드까지 가는게 짧다면 연산 재실행 
				{
					dist[next.node] 	= nextDist;
					prevPath[next.node] = now.node;			// 이전 노드 저장
					pq.add(new Node(next.node, nextDist));	// 최단 거리 갱신시 큐에 데이터 삽입
				}
			}
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb	= new StringBuilder();
		StringTokenizer st;
		N 			= Integer.parseInt(br.readLine());
		B 			= Integer.parseInt(br.readLine());
		dist 		= new int[N+1];									// 최단거리를 담을 배열
		prevPath 	= new int[N+1];									// 이전 방문 노드를 담을 배열( 추후 경로 탐색에 사용 )
		adlist 		= new ArrayList[N+1];							// 인접리스트를 담을 배열
		path		= new ArrayList<>();							// end노드에서 start노드로의 경로를 역으로 담을 배열
		pq 			= new PriorityQueue<>((a,b)->a.dist-b.dist);	// 다익스트라 알고리즘에 사용할 우선순위 큐
		for(int i=0; i<=N; i++) 
		{
			adlist[i] 	= new ArrayList<>();						// 리스트 초기화
			dist[i] 	= MAX_DIST;									// 최단거리 배열 max값으로 초기화
		}
		
		int a,b,c;
		for(int i=0; i<B; i++) 
		{
			st	= new StringTokenizer(br.readLine());
			a 	= Integer.parseInt(st.nextToken());
			b 	= Integer.parseInt(st.nextToken());
			c 	= Integer.parseInt(st.nextToken());
			adlist[a].add(new Node(b,c));								// a->b로 c만큼 단방향 연결
		}
		
		st 		= new StringTokenizer(br.readLine());
		start 	= Integer.parseInt(st.nextToken());
		end 	= Integer.parseInt(st.nextToken());
		
		Dijkstra();
		
		DFS(end);					// end에서 start까지 경로 역탐색
		
		sb.append(dist[end])		// end까지 가는 거리
			.append('\n')		
			.append(path.size())	// end까지 가기 위해 거쳐간 노드들
			.append('\n');	 
		
		for(int i=path.size()-1; i>=0; i--)
			sb.append(path.get(i)).append(' ');	// end까지 가는 경로
		
		System.out.println(sb);
	}
}