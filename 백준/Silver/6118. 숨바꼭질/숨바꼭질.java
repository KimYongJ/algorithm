// https://github.com/KimYongJ/algorithm
// 문제 요약 : 1번 노드 부터 가장 먼거리에 있는 노드의 번호와 거기까지 가는 거리, 같은 거리를 갖는 노드들의 갯수 출력
// 주의 : 가장 먼 거리 노드의 번호가 여러개이면 노드 index가 낮은 것을 우선출력

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Main{
    public static void main(String[] args)throws Exception{
    	new Solution().solution(); // 솔루션 실행 
    }
}
class Solution {
	
	public void solution() throws Exception {
		Reader r = new Reader();
		final int INF 			= 20_000; // 최대 노드 갯수가 2만개이고, 간선 가중치가 1이기 때문에 1번 노드부터 마지막 노드까지 가는 최대 가중치는 19,999입니다.
		PriorityQueue<Node> pq 	= new PriorityQueue<Node>((a,b)->a.dist-b.dist);
		StringBuilder sb 		= new StringBuilder();
		int Node				= 0; 					// 1번에서 가장 거리가 먼 노드 중 번호가 가장 작은 것
		int Max_dist			= 0; 					// 1번 노드부터 가장 먼노드 까지 거리
		int Node_cnt			= 0; 					// 1번에서 가장 먼노드의 거리가 여러개일 때 노드의 갯수를 담을 변수   
		int N 					= r.read()+1; 			// 노드 갯수
		int M 					= r.read();   			// 간선 갯수
		int dist[] 				= new int[N]; 			// 1번노드로부터 최단거리를 담을 배열
		boolean visit[] 		= new boolean[N];		// 방문한 노드를 담을 배열
		ArrayList<Node>[] list 	= new ArrayList[N+1];	// 인접리스트를 담을 배열
		
		for(int i=1; i<N; i++)
			list[i] = new ArrayList<Node>(); // 인접리스트배열 초기화

		for(int i=0; i<M; i++) {
			int a = r.read();
			int b = r.read();
			list[a].add(new Node(b,1));
			list[b].add(new Node(a,1));
		}
		
		Arrays.fill(dist, INF); // 최초 거리를 INF로 초기화
		dist[1] = 0; 			// 1번부터 시작이기 때문에 자기 자신 0으로 초기화 
		pq.add(new Node(1,0)); 	// 1번노드를 넣고 거리 0 세팅 
		
		while(!pq.isEmpty()) {
			Node n 				= pq.poll();
			int nowNode 		= n.node;
			int until_now_dist 	= n.dist; // 1번 노드부터 nowNode까지 거리
			
			if(visit[nowNode]) continue; // 방문했다면 다음 조건으로 넘어감
			
			visit[nowNode] = true;
			
			for(int i=0; i<list[nowNode].size(); i++) {
				Node nn 		= list[nowNode].get(i); // nowNode의 인접 노드
				int nextNode 	= nn.node; 				// nowNode의 인접 노드 번호
				int nextDist 	= nn.dist; 				// nowNode부터 인접노드까지 연결된 거리
				int distSum 	= until_now_dist + nextDist; // 1번노드부터 nowNode까지 거리 + nowNode부터 nextNode까지 거리 즉, 1번노드부터 nowNode를 거쳐서 nextNode에가는 거리 
				if(dist[nextNode] 	> distSum) { 		// dist에 기 저장된 nextNode까지 최단거리가 distSum보다 크면 새로운 최단거리를 발견한 것이므로 이하 실행
					dist[nextNode] 	= distSum;
					pq.add(new Node(nextNode, distSum));
					if(Max_dist < distSum)				// Max_dist를 바로 구하기 위한 코드
						Max_dist = distSum;
				}
			}
		}
		
		for(int i=1; i<N; i++)  // Max_dist와 같은 값을 가지는 노드의 갯수와 Max_dist값을 갖는 가장 작은 인덱스 찾기 
			if(dist[i] == Max_dist) {
				Node_cnt++;
				if(Node == 0)
					Node = i;
			}
		
		sb.append(Node).append(' ')
			.append(Max_dist).append(' ')
			.append(Node_cnt);
		
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