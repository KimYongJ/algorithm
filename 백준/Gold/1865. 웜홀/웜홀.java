// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		new Solution().solution();
	}
}
class Solution{
	
	final int INF = 4_990_001;
	final int MIN_INF = -4_990_001;
	
	ArrayList<Node> list[]; // 양방향 저장이 가능해야 하기 때문에 list로 담는다.
	int T, N, M, W;
	boolean visit[];
	
	StringTokenizer st;
	StringBuilder sb = new StringBuilder(); // 출력 결과를 담을 스트링 빌더
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	boolean bellman_ford(int startNode) { // 벨만 포드 알고리즘 시작
		int dist[] = new int[N+1];
		Arrays.fill(dist, INF);
		dist[startNode] = 0;
		
		for(int i=1; i<N; i++) { // 노드갯수 -1 까지 반복
			
			boolean notUpdate = true;
			
			for(int j=1; j<=N; j++) { // 리스트 의 각 연결마다 다 확인
				for(Node node : list[j]) {
					int start = j;
					int end = node.end;
					int distance = node.dist;
					
					if(dist[start] == INF) continue;// 벨만 포드 특징 : 한번이라도 갱신 된 값이어야 이하 연산실
					
					int newEndDist = dist[start] + distance;
					
					if(dist[end] > newEndDist) {
						dist[end] = newEndDist < MIN_INF ? MIN_INF : newEndDist;
						visit[start] = visit[end] = true; // 노드에대해서 방문 처리
						notUpdate = false;
					}
					
				}
			}
			if(notUpdate) // 간선에 대해 모두 돌았는데 업데이트 할것이 없으면, 다음 반복을해도 없기 때문에 미리 종료
				return false;
		}
		
		// 한번 더 확인하여 갱신되는 부분이 있다면 음의 사이클이 있는 것임 
		for(int j=1; j<=N; j++) { // 리스트 의 각 연결마다 다 확인
			for(Node node : list[j]) {
				int start = j;
				int end = node.end;
				int distance = node.dist;
				
				if(dist[start] == INF) continue;// 벨만 포드 특징 : 한번이라도 갱신 된 값이어야 이하 연산실
				
				int newEndDist = dist[start] + distance;
				
				if(dist[end] > newEndDist) {
					return true;
				}
				
			}
		}
		return false;
	}
	
	
	
	void inputData(int MAX)throws Exception { // 양,음의 간선 넣는 코드
		for(int i=0; i<MAX; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			if(i<M) {
				list[a].add(new Node(b,d));
				list[b].add(new Node(a,d));
			}else {
				list[a].add(new Node(b,-d)); // 음의 간선은 단방향 그래프이다. 
			}
		}
	}
	void solution() throws Exception{

		T = Integer.parseInt(br.readLine());
		
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); 	// 노드 갯수(최대 100개)
			M = Integer.parseInt(st.nextToken()); 	// 양의 간선 갯수
			W = Integer.parseInt(st.nextToken()); 	// 음의 간선 갯수
			visit = new boolean[N+1];				// 벨만 포드 알고리즘 진행시 방문 체크할 배열
			
			list = new ArrayList[N+1];				// 간선 정보를 담을 list배열
			for(int i=1; i<N+1; i++)
				list[i] = new ArrayList<Node>();
			
			inputData(M+W);							// 음, 양의 간선을 list에 담는다.
			
			String result = "NO";
			
			for(int i=1; i<N+1; i++) {
				if(!visit[i]) { // 한번도 방문하지 않은 노드들에 대해서 벨만 포드 알고리즘을 실행한다.
					if( bellman_ford(i) ) {// 벨만포드알고리즘 진행시 음의 사이클이 있다면 true반환
						result = "YES";
						break;
					}
				}
			}
			sb.append(result).append('\n');
			
		}
		System.out.println(sb);
	}
}
class Node{
	int end, dist;
	public Node(int end, int dist){
		this.end = end;
		this.dist = dist;
	}
}


//int read() throws Exception{ 			// 빠른 입력을 위한 함수
//	int c, n = System.in.read() & 15;
//	boolean negative = n == 13;
//	if(negative) n = System.in.read() & 15;
//	while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
//	if(c == 13) System.in.read();
//	return negative?~n+1:n;
//}