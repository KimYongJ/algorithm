// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

class Main{
	public static void  main(String[] args)throws Exception{
		new Solution().solution();
	}
}
class Solution{
	final int INF 		= 99_001;
	final int MIN_INF 	= -99_001;
	int N, M, dist[];
	int beforeAndAfter[][]; // 인덱스를 기반으로 [i][0]은 전노드, [i][1]은 후노드, i는 자기자신
	boolean visit[]; 		// 양의 사이클 판별시 사용, [i] 노드에서 N까지가는 경로가 가능한지 체크
	
	ArrayList<Node>[] node;	// 인접노드를 담을 리스트
	ArrayList<Integer> result = new ArrayList<>();// 1번부터 N까지 경로를 담을 리스트
	
	void DFS(int idx) {		// idx를 전달하면 해당 idx부터 N노드까지의 경로를 result 리스트에 담음
		result.add(idx);
		
		if(idx == 1)
			return;

		DFS(beforeAndAfter[idx][0]); 	// idx노드의 의 이전노드 전달
	}
	boolean BFS_END_TO_N(int start) { 	// 사이클이 있는 경우, 전달된 노드가 N까지 갈 수 있다면 양의 사이클이 있으므로 -1출력해야함
		boolean goToEnd = false; 		// start 노드가 N까지 갈 수 있는지 체크 
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(start);
		LOOP : 
		while(!q.isEmpty()) {
			int nowNode = q.poll();
			if(!visit[nowNode]) {
				visit[nowNode] = true;
				for(int i=0; i<node[nowNode].size(); i++) {
					int nextNode = node[nowNode].get(i).end;
					if(nextNode == N) {// start노드부터 시작하여 N까지 도착시 true리턴
						goToEnd = true;
						break LOOP;
					}
					q.add(nextNode);
				}
			}
		}
		return goToEnd;
	}
	void bellman_ford() { 								// 최대거리를 구하는 벨만포드 알고리즘 
		
		Arrays.fill(dist, MIN_INF); 					// 최장거리를 구하기 위해 셋팅
		dist[1] = 0;
		boolean isUpdate;
		for(int i=0; i<N-1; i++) {						// n-1번까지 반복하여 양의 사이클이 없다면 최단거리를 만들어 놓음 
			isUpdate = false;
			for(int j=1; j<N+1; j++) {
				for(Node now : node[j]) {
					int start = j; 						// 시작점
					int end = now.end;					// 도착점
					int distance = now.dist;			// 시작에서 도착까지의 거리
	
					if(dist[start] == MIN_INF) continue;// 벨만 포드 특징 한번이라도 dist에 연산된 것이 있어야 이하 연산시작(특정 노드에 대한 거리를 구하는 것이기 때문) 
					
					int distSum = dist[start] + distance;
					
					if(dist[end] < distSum) { 			// 최장거리를 구하기 위한 셋팅
						isUpdate = true;
						dist[end] = distSum;
						beforeAndAfter[start][1] = end; // start노드의 후 노드에 end를 저장
						beforeAndAfter[end][0] = start; // end노드의 전 노드에 start를 저장
					}
				}
			}
			if(!isUpdate) 								// 업데이트 된 것이 없다면 다음 반복도 업데이트되는것이 없으므로 스킵
				break;
		}
		
		if(dist[N] == MIN_INF) 							// N에 도달할 수 없을 경우 이하 연산 스킵 
			return;
		
		for(int j=1; j<N+1; j++) {
			for(Node now : node[j]) {
				int start = j; 							// 시작점
				int end = now.end;						// 도착점
				int distance = now.dist;				// 시작에서 도착까지의 거리

				if(dist[start] == MIN_INF) continue; 	// 벨만 포드 특징 한번이라도 dist에 연산된 것이 있어야 이하 연산시작(특정 노드에 대한 거리를 구하는 것이기 때문) 
				
				int distSum = dist[start] + distance;
				
				if(dist[end] < distSum) { 				// 양의 사이클이 있을 경우 
					if(!visit[end]) {						
						if( BFS_END_TO_N(end) ) { 		// end노드까지 사이클이있는데 end노드에서 N까지 도달 가능하면 양의 사이클이 있는 것이다. 
							dist[1] = INF;
							return;
						}
					}
				}
			}
		}
	}
	void solution()throws Exception {
		StringBuilder sb = new StringBuilder();
		N 				= read(); // 2이상 100 이하 
		M 				= read(); // 1이상 2만 이하
		node 			= new ArrayList[N+1];
		dist 			= new int[N+1];
		visit 			= new boolean[N+1];
		beforeAndAfter 	= new int[N+1][2]; //[i][0]은 전노드 [i][1]은 후노드, i는 본인
		
		for(int i=1; i<N+1; i++) 
			node[i] 	= new ArrayList<Node>();
		
		for(int i=0; i<M; i++) {
			int start 	= read();
			int end 	= read();
			int dist 	= read(); 					// 0이상 1000이하
			node[start].add(new Node(end,dist)); 	// 일방 통행
		} 
		
		bellman_ford(); 							// 최단거리가 아닌, 최대거리를 구하는 벨만포드 알고리즘 
		
		if(dist[1]>0 || dist[N] == MIN_INF) { 		// 양의 사이클이 있다면 dist[1]에 양수가 들어감 + N에 도달할 수 없을 경우 dist[N]에 min_inf
			sb.append(-1);
		}else {
			DFS(N); 								// DFS 함수로 result 리스트에 노드의 순서를 담는다. 
			for(int i=result.size()-1; i>=0; i--) 	// 결과를 스트링빌더에 차례로 담음
				sb.append(result.get(i)).append(' ');
		}
		System.out.println(sb);
	}

	int read() throws Exception{ 			// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
}

class Node{
	int  end, dist;
	Node(int end, int dist){
		this.end = end;
		this.dist = dist;
	}
}