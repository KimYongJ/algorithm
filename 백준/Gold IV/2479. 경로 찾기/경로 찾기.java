import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.StringTokenizer;
class  Point{
	int node, dist;
	Point(int node, int dist){
		this.node = node;
		this.dist = dist;
	}
}
class Main{
	
	static final int MAX = 100_000_000;
	static int N, K, startIdx, endIdx; // 길이가 K인 N개의 2진수가 주어짐
	static int arr[][], dist[];
	static int route[]; // 최단거리 경로를 담을 리스트
	static boolean visit[];
	static ArrayList<Integer> list[];  // 인접 노드를 담을 리스트 
	static PriorityQueue<Point> q;
	static StringBuilder sb = new StringBuilder();
	// 거리가 1이면 true 아니면 false
	public static boolean isHamming(int a, int b) 
	{
		int cnt = 0;
		for(int i=0; i<K; i++)
			if(arr[a][i] != arr[b][i]) {
				++cnt;
				if(cnt > 1) return false;
			}
				
		return cnt == 1;
	}
	public static void print(int idx) {
		sb.append(idx).append(' '); // 문자열에 추가 
		if(idx == endIdx) 		// 시작인덱스인 경우 종료 
			return; 
		print(route[idx]); 			// 바로 앞 노드를 전달.
	}
	public static boolean Dijkstra() 
	{
		dist[endIdx] = 0; 		// 시작점 길이 초기화
		visit[endIdx] = true; 	// 시작점 방문 체크 
		q.add(new Point(endIdx, 0));
		
		int nextDist = 0;
		while(!q.isEmpty())  		// 큐가 빌 때 까지 반복
		{
			Point now = q.poll(); 	// 현제 데이터 뽑아오기
			
			for(Integer adNode : list[now.node]) {
				nextDist = now.dist + 1;
				if(dist[adNode] > nextDist) 	// 지금까지 adNode까지 오는 거리를 구해놓은 dist 안의 값보다 now노드를 거쳐 오는 값이 더 빠를 때 
				{
					if(visit[adNode])continue; 	// 기존 방문한 곳이면 스킵
					visit[adNode]= true;		// 방문 처리
					dist[adNode] = nextDist;	// 최단거리 갱신
					route[adNode] = now.node; 	// 현재 도착한 노드의 바로 전노드를 저장해 연결되도록 함
					if(adNode == startIdx) 		// 종료점까지 최단거리를 구한 경우 true반환 종료  
						return true;
					q.add(new Point(adNode,nextDist));
				}
			}
		}
		return false; // 종료점을 못만난 경우 false 반환
	}
	public static void main(String[] args)throws Exception
	{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		N 					= Integer.parseInt(st.nextToken());
		K 					= Integer.parseInt(st.nextToken());
		arr 				= new int[N+1][K];
		dist 				= new int[N+1];
		route 				= new int[N+1];// 최단경로정보를 담을 리스트
		visit	 			= new boolean[N+1];
		list 				= new ArrayList[N+1]; // 노드들의 연결을 담을 list
		q					= new PriorityQueue<Point>((a,b)->a.dist-b.dist); // 다익스트라를 위해 거리기준 오름차순 정렬
	
		for(int i=1; i<=N; i++) 
		{
			list[i] = new ArrayList<>(); 		// 리스트 초기화
			dist[i] = MAX; 						// 최단거리를 구할 배열을 MAX로 초기화 한다.
			String str = br.readLine();
			for(int j=0; j<K; j++)
				arr[i][j] = str.charAt(j)-'0'; 	// 2진수를 입력 받는다.
		}
		
		// 해밍 거리 노드들을 담아 놓는다.
		for(int i=1; i<=N-1; i++)
			for(int j=i+1; j<=N; j++)
				if( isHamming(i,j) ) 
				{
					list[i].add(j); // 양쪽으로 갈 수 있기 때문에 양방향 맵핑
					list[j].add(i); // 양쪽으로 갈 수 있기 때문에 양방향 맵핑
				}
			
		
		st 			= new StringTokenizer(br.readLine());
		startIdx 	= Integer.parseInt(st.nextToken());
		endIdx 		= Integer.parseInt(st.nextToken());

		if( Dijkstra() ) {
			print(startIdx); // 경로가 존재하는 경우 출력
			System.out.println(sb);
		}			
		else System.out.println(-1);
	}
}