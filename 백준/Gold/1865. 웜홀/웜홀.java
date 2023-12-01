import java.util.ArrayList;
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		new Solution().solution();
	}
}
class Solution{
	
	final int INF = 4_990_001;	 			// 최소로 나올 수 있는 간선의 거리 
	
	ArrayList<Node> list[]; 				// 양방향 저장이 가능해야 하기 때문에 list로 담는다.
	int T, N, M, W;
	
	StringBuilder sb = new StringBuilder(); // 출력 결과를 담을 스트링 빌더
	
	int read() throws Exception{ 			// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
	
	boolean bellman_ford() { 	// 벨만 포드 알고리즘 시작
		int dist[] = new int[N+1];
		Arrays.fill(dist, INF);
		
//		★dist[1] = 0; // 모든 것을 탐색하기 위해 주석처리
		
		for(int i=0; i<N; i++) { 			// 노드갯수 -1 까지 반복
			
			boolean notUpdate = true;
			
			for(int j=1; j<=N; j++) { 		// 리스트 의 각 연결마다 다 확인
				for(Node node : list[j]) {
					int start = j;
					int end = node.end;
					int distance = node.dist;
					
					// ★ 벨만 포드 알고리즘에서는 한번이라도 초기화된 값이 있어야 실행했지만 모든 것에 대해 음의 사이클을 확인하기 위해서는 
					// ★ dist 배열에 INF로 넣은 값을 초기화 하지 않고 초기화 탐색하는 부분을 없애야 한다. 
					// ★ if(dist[start] == INF) continue;// 벨만 포드 특징 : 한번이라도 갱신 된 값이어야 이하 연산실행
					
					int newEndDist = dist[start] + distance;
					
					if(dist[end] > newEndDist) {
						dist[end] = newEndDist;
						notUpdate = false;
						if(i==N-1) {// N-1번까지 왓는데 갱신되었다는건 음의 사이클이 있다는 말이기 때문에 true리턴
							return true;
						}
					}
					
				}
			}
			if(notUpdate) // 간선에 대해 모두 돌았는데 업데이트 할것이 없으면, 다음 반복을 해도 없기 때문에 미리 종료
				return false;
		}
		return false;
	}
	
	
	void solution() throws Exception{

		T = read();
		
		while(T-->0) {
			N 		= read(); 					// 노드 갯수(최대 100개)
			M 		= read(); 					// 양의 간선 갯수
			W 		= read(); 					// 음의 간선 갯수
			list 	= new ArrayList[N+1];		// 간선 정보를 담을 list배열
			
			for(int i=1; i<N+1; i++)			// 리스트 초기화
				list[i] = new ArrayList<Node>();
			
			for(int i=0; i<M+W; i++) {			// 간선에 대해 입력을 받는다.
				int a = read();
				int b = read();
				int d = read();
				if(i<M) {
					list[a].add(new Node(b,d)); // 양의 간선은 양방향 그래프
					list[b].add(new Node(a,d)); // 양의 간선은 양방향 그래프
				}else {
					list[a].add(new Node(b,-d));// 음의 간선은 단방향 그래프이다. 
				}
			}
			
			String result = "NO";
			if( bellman_ford() ) {				// 벨만포드알고리즘 진행시 음의 사이클이 있다면 true반환
				result = "YES";
			}
			sb.append(result)
			  .append('\n');
			
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

