// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point{
	int adNode, dist;
	Point(int adNode, int dist){
		this.adNode = adNode;
		this.dist = dist;
	}
}
class Main{
	
	static final int MAX = 100_000_000;
	static int total, start, end, node1, node2, dst;
	static int result, dist[];
	
	static boolean[] visit;
	static PriorityQueue<Point> pq;
	static ArrayList<Point>[] adList;
	static int[] adNode;
	
	
	public static void Dijkstra() {
		// 우선순위 큐 정렬 방식을 거리가 짧은 순으로함.
		pq = new PriorityQueue<Point>((a,b)->a.dist-b.dist); 
		pq.add(new Point(start, 0)); 			// 첫 방문 체크 
		dist[start] = 0; 						// 자기자신은 거리 0으로 초기화 
		while(!pq.isEmpty()) {
			Point now = pq.poll(); 				// 큐에서 데이터를 하나 꺼내온다.
			
			if(visit[now.adNode])continue; 		//방문한 노드면 스킵
			if(now.adNode == end) break; 		// 도착 점까지 방문했을 경우 함수종료.
			visit[now.adNode]= true; 			// 방문 처리
			
			for(Point point : adList[now.adNode]) { // 인접 노드를 꺼내온다. 
				int nextNode = point.adNode;
				int nextDist = point.dist;
				
				int distSum = now.dist + nextDist;
				if(dist[nextNode] > distSum) {
					dist[nextNode] = distSum;
					pq.add(new Point(nextNode, distSum)); // start노드부터 nextNode까지 오는데 걸린 총거리
					adNode[nextNode] = now.adNode;
				}
			}
		}
	}
   
	public static void DFS(int now, int max, int sum) { // end->start까지 탐색하는 중 가장 큰 값은 max에 저장, end부터start까지 가는 총거리를 저장
		if(now == start) { 				// 현재 노드가 시작노드면 종료 
			result = sum - max; 		// 지금까지 거리를 모두 더한 것에서 가장 긴노드를 뺀다.
			return;
		}
		int nextNode = adNode[now]; 	// 다익스트라로 최단거리 구하면서 now노드까지 가기 바로 전에 거쳤던노드
		int adDist = 0;  				// now부터 nextNode까지의 거리
		for(Point p : adList[now]) { 	// now노드의 인접 노드를 돌면서 nextNode까지 걸린 거리를 찾는다.
			if(p.adNode == nextNode) {
				adDist = p.dist;
				break; 					// 거리를 찾으면 반복문 탈출
			}
		}
		// 다음 DFS실행
		DFS(nextNode, Math.max(max, adDist), sum +adDist);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		total 	= Integer.parseInt(st.nextToken());
		start 	= Integer.parseInt(st.nextToken());
		end 	= Integer.parseInt(st.nextToken());
		dist 	= new int[total+1]; 				// start 노드로부터 최단거리를 담을 리스트
		visit	= new boolean[total+1]; 			// 다익스트라 사용시 방문체크
		adList 	= new ArrayList[total+1]; 			// 인접 리스트를 담는다.
		adNode	= new int[total+1]; 				// start 부터 end까지 가는데 거친 노드들을 담을 배열
		
		for(int i=0; i<total+1; i++) {
			adList[i] = new ArrayList<>(); 			// 인접 노드와 인접노드까지 거리를 담을 리스트 초기화
 
			dist[i] = MAX; 							// 최단거리를 구하기 위해 값은 MAX로 셋팅 
		}
		
		for(int i=0; i<total-1; i++) {
			st 		= new StringTokenizer(br.readLine());
			node1 	= Integer.parseInt(st.nextToken());
			node2 	= Integer.parseInt(st.nextToken());
			dst 	= Integer.parseInt(st.nextToken());
			adList[node1].add(new Point(node2, dst)); // 양방향 이기 때문에 해준다. 
			adList[node2].add(new Point(node1, dst)); // 양방향 이기 때문에 해준다.
		}
		
		Dijkstra();
		
		DFS(end,0,0); // end노드부터 start노드 까지 거꾸로 가면서 가장 긴 노드의 값을 빼서 result에 저장한다.
		
		
		System.out.println(result);
	}
}