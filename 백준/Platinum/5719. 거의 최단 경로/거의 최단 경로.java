// https://github.com/KimYongJ/algorithm

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Main{
	static final int INF = 51_500; 				// 노드 500 * 거리 최댓값 103
	static int N, M, START, END;
	static int a, b, c;
	static int dist[];
	static boolean exceptNode[][]; 				// 첫번째 최단거리 까지 사용된 간선들 사용못하도록 체크하는 배열
	
	
	static ArrayList<Node>[] list; 				// 인접 노드와 거리를 담을 리스트
	static ArrayList<Integer>[] useNodeList; 	// 다익스타라 알고리즘을 실행하며 사용한 노드들의 연결을 담을 리스트
	static StringBuilder sb = new StringBuilder();
	
	// 빠른 입력위해 만든 함수 
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

	// 최단거리에서 사용된 노드연결들 삭제 처리(exceptNode배열에 true처리)
	public static void deleteNode(int node) { 
		if(node == START) return;
		
		for(int front : useNodeList[node]) {
			if(!exceptNode[front][node]) {		// 빠른 연산을 위해 이미 연산한 것은 방문하지 않아야 한다. 아니면 시간초과
				exceptNode[front][node] = true; // 간선을 앞으로 사용하지 못하도록 true처리
				deleteNode(front);  			// start노드까지 역으로 DFS방식으로 찾는다.
			}
		}
	}
	// 최단거리 구하는 알고리즘
	public static void Dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.dist-b.dist); // 거리 기준 오름 차순자동정렬	
		dist 		= new int[N];

		Arrays.fill(dist, INF);
		
		dist[START] = 0; 						// 시작노드까지 최종 거리를 0으로 셋팅
		pq.add(new Node(START,0)); 				// START 노드까지 가는 거리 0 초기 셋팅
		Loop:
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			int nowNode = now.adNode;
			
			for(int i=0; i<list[nowNode].size(); i++) {
				Node next = list[nowNode].get(i);
				int nextNode = next.adNode; 	// 인접 노드 번호
				int nextDist = next.dist; 		// nowNode에서 nextNode까지 가는 거리
				int distSum = dist[nowNode] + nextDist; // 시작노드에서 nowNode까지 가는거리와 nowNode에서 nextNode까지 가는 거리를 구해서 시작노드에서 nowNode를 거쳐 nextNode까지 거리를 계산함
				
				if(exceptNode[nowNode][nextNode]) continue; // 이미 기존 최단경로에서 사용한 간선일 경우 연산 제외
				
				// 다익스트라 연산시 사용된 노드들을 어떻게 찾을 수 있을까? 
				// 특징1. 이미 최단거리가 구해진 후에는 같은 노드는 두번 방문하지 않는다.(우선순위 큐로 짧은순으로 연결했기 때문)
				// 특징2. 최단거리가 작아야만 갱신되고, 같을 때는 갱신되지 않는다 : 같을 때도 최단거리에 사용된 노드로 보고 포함되어야 한다.
				// 결론 : 최단거리가 갱신될 때 nextNode는 이전 노드의 값이 달라졌다는 의미이므로 초기화 하여 nowNode를 담는다.
				//      만약 최단거리가 같다면 그 때 사용한 nextNode의 바로 전 노드인 nowNode를 넣는다.
				if(dist[nextNode] > distSum) { 	// 최단거리 갱신시 
					dist[nextNode] = distSum;
					useNodeList[nextNode].clear(); 		// 최단거리 갱신시 nextNode의 이전 노드가 바뀐것이기 때문에 초기화 
					useNodeList[nextNode].add(nowNode); // nextNode의 이전 노드인 nowNode 삽입
					
					pq.add(new Node(nextNode, distSum));
				}else if(dist[nextNode] == distSum) { 	// 최단거리가 연산된 것과 같을시
					useNodeList[nextNode].add(nowNode);
				}
			}
		}
	}
	
	public static void main(String[] args)throws Exception{
		N 						= read();// 노드 갯수와
		M 						= read();// 도로숫자 
		
		while(N!=0) {
			list 				= new ArrayList[N]; 	// 인접 노드를 담을 리스트
			useNodeList 		= new ArrayList[N];		// 최단 거리를 구할 때 사용한 노드들의 연결을 담을 리스트
			exceptNode 			= new boolean[N][N]; 	// 최단 거리의 노드들에 true값을 넣어 거의 최단경로를 구할 때 연산하지 못하도록 한다.
			
			for(int i=0; i<N; i++) {
				list[i] 		= new ArrayList<>(); 	// 리스트 초기화
				useNodeList[i] 	= new ArrayList<>();	// 리스트 초기화
			}
			
			START 				= read();
			END 				= read();
			
			for(int i=0; i<M; i++) {
				a 				= read();
				b 				= read();
				c 				= read();
				list[a].add(new Node(b,c)); 	// 단방향 셋팅 
			}
			Dijkstra(); 						// 첫번 째 최단거리
			deleteNode(END); 					// 종료 노드를 전달하여 종료노드로부터 시작노드까지 간선을 모두 탐색
			Dijkstra(); 						// 두번 째 거의 최단거리연산
			
			int result 			= -1;
			if(dist[END] != INF)
				result 			= dist[END];
			
			sb.append(result).append('\n'); 	// 결과 셋팅

			//노드 갯수와 도로 숫자를 받는다.
			N 					= read();
			M 					= read();
		}
		System.out.println(sb);
	}
}
class Node{
	int adNode, dist;
	Node(int adNode, int dist){
		this.adNode = adNode;
		this.dist 	= dist;
	}
}