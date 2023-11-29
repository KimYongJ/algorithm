// https://github.com/KimYongJ/algorithm
// 문제 요약 : 시작점부터 도착 후보들의 노드로 가는 최단경로에 g,h 노드 간선을 지나는 것이 있느냐를 찾는 문제
// [풀이]
// 1. start에서 모든 경로로 가는 최단거리를 구한다. 	배열이름 StoE
// 2. h에서 모든 경로로 가는 최단 거리를 구한다. 		배열이름 HtoAll
// 3. g에서 모든 경로로 가는 최단 거리를 구한다. 		배열이름 GtoAll
// start에서 g,h 간선을 거쳐 end까지 가는 거리가 최단거리인 것인지 구하는 공식
// HtoAll[start] + g-h거리 + GtoAll[end] = StoE[end]
// GtoAll[start] + g-h거리 + HtoAll[end] = StoE[end]
// 위 2개의 식 중 하나라도 참이면 최단거리다이다.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;


class Main{
    public static void main(String[] args)throws Exception{
    	new Solution().solution(); // 솔루션 실행 
    }
}
class Solution {
	
	StringBuilder sb = new StringBuilder();
	ArrayList<Node>[] list;
	PriorityQueue<Node> pq;
	PriorityQueue<Integer> result;
	final int INF = 1_999_001;
	
	int N, g_h_dist, nowNode, nextNode, 
	    nextDist, until_now_dist, distSum,
	    dist[], StoE[], HtoAll[], GtoAll[];
	
	boolean visit[]; // 다익스트라 함수 실행시 노드 방문 체크를 담을 배열
	
	
	// start부터 end까지 갈 때 g,h를 거치는지 체크하는 함수
	public boolean isPossible(int start, int end) {
		boolean pos = false;
		
		if(HtoAll[start] != INF && GtoAll[end] != INF) {
			if(HtoAll[start] + g_h_dist + GtoAll[end] == StoE[end]) {
				pos = true;
			}
		}
		if(GtoAll[start] != INF && HtoAll[end] !=INF) {
			if(GtoAll[start] + g_h_dist + HtoAll[end] == StoE[end]) {
				pos = true;
			}
		}
		
		return pos;
	}
	public int[] Dijkstra(int start) {
		pq 		= new PriorityQueue<Node>((a,b)->a.dist-b.dist);// 우선순위 큐, dist기준 오름차순 정렬
		visit	= new boolean[N+1]; // 노드 방문을 체크할 배열 
		dist 	= new int[N+1];// start노드 부터의 모든 정점에 대한 최단거리를 담을 배열 
		Arrays.fill(dist, INF); // 도달할 수 없는 곳은 INF로 초기화
		
		pq.add(new Node(start,0));// 큐에 담아준다.
		dist[start] = 0; // 자기자신은 거리가 0으로 초기화 
		
		while(!pq.isEmpty()) {
			Node n = pq.poll(); 
			nowNode = n.node; // 현재 큐에서 꺼낸 노드
			until_now_dist = n.dist; // start에서부터 현재꺼낸 노드까지 걸린 총 거리 
			
			if(visit[nowNode])
				continue; // 방문한 노드면 이미 최단거리를 구한 것이기 때문에 이하 연산을 하지 않습니다. 
			
			visit[nowNode] = true;
			
			for(int i=0; i<list[nowNode].size(); i++) {
				Node nn 		= list[nowNode].get(i); // 큐에서 꺼낸 노드의 인접노드를 가져옵니다. 
				nextNode 	= nn.node; // 큐에서 꺼낸 노드의 인접노드 번호
				nextDist 	= nn.dist; // 큐에서 꺼낸 노드로부터 인접노드 까지 거리
				distSum 	= until_now_dist + nextDist;// start에서 nowNode까지 거리 + nowNode부터 nextNode까지 거리 
				if(dist[nextNode] > distSum) {// start에서 nextNode까지 거리보다 start에서 nowNode까지 거리 + nowNode에서 nextNode까지 거리가 더 작으면 갱신   
					dist[nextNode] = distSum;
					pq.add(new Node(nextNode, distSum));
				}
			}
		}
		return dist;
	}
	
	public void solution() throws Exception {
		Reader r = new Reader();
		
		int T = r.read();
		while(T-->0) {
			N	= r.read(); // 노드갯수(최대 2000)
			int M	= r.read(); // 간선갯수(최대 5만)
			int H 	= r.read(); // 도착지후보(최대 100)
			  
			result = new PriorityQueue<>(); // 도착지 후보 중 시작점에서 g,h를 지나 도착지 후보까지 갈 수 있는 노드를 담을 큐
			list  	= new ArrayList[N+1]; 	// 간선정보를 담을 리스트 선언
			
			for(int i=1; i<=N; i++)
				list[i] = new ArrayList<Node>();
			
			int start 	= r.read(); // 시작 노드
			int g 		= r.read(); // g 노드
			int h 		= r.read(); // h 노드
			
			
			for(int i=0; i<M; i++) { // 간선 셋팅
				int a = r.read(); // 노드 a
				int b = r.read(); // 노드 b
				int d = r.read(); // 사이 거리(최대 1000)
				list[a].add(new Node(b,d));
				list[b].add(new Node(a,d));
				
				if( (a==g && b==h) || (a==h&& b==g) ) {
					g_h_dist = d; // g와 h사이 거리 저장 
				}
			}
			
			StoE 	= Dijkstra(start);
			HtoAll 	= Dijkstra(g);
			GtoAll 	= Dijkstra(h);
			
			
			while(H-->0) {
				int end = r.read();
				if(isPossible(start, end)) { // start에서 시작하여 end로 가는 최단거리에 g,h를 지나는 간선이 있는지 체크
					result.add(end);
				}
			}
			
			while(!result.isEmpty()) 
				sb.append(result.poll()).append(' ');
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
class Node{
	int node, dist;
	Node(int node, int dist){
		this.node = node;
		this.dist = dist;
	}
}
// 빠른 입력을 위해 만듦
class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;
    int read() throws Exception {
        int n = 0;
        byte c;
        while ((c = get()) <= 32);
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = get()));
        return n;
    }
    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }
    byte get() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}