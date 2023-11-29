// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Main{
    public static void main(String[] args)throws Exception{
    	new Solution().solution(); // 솔루션 실행 
    }
}
class Solution {
	
	PriorityQueue<Node> pq;
	ArrayList<Node>[] list;
	StringBuilder sb = new StringBuilder();
	
	boolean visit[];
	final int INF = 10_000_001;
	int computer, line, base, cnt, max, dist[];
	
	public void MaxSetting() {// 감염되기까지 가장 오래 걸린 컴퓨터 시간 확인 함수
		for(int i=1; i<=computer; i++)
			if(dist[i] != INF && max < dist[i])
				max = dist[i];
	}
	
	public void Dijkstra() {// 다익스트라 함수로, 최초 감염된 컴퓨터 부터 앞으로 감염될 컴퓨터까지 탐색
		pq 		= new PriorityQueue<Node>((a,b)->(a.dist-b.dist));
		dist 	= new int[computer+1]; 		// 거리 담을 배열( 이 문제에서는 시간 )
		visit 	= new boolean[computer+1];	// 노드 방문 유무 체크할 배열
		cnt 	= 0; 						// 감염된 노드 갯수
		max 	= 0; 						// 최장 걸린 시간
		
		Arrays.fill(dist, INF); 			// 초기값 INF로 셋팅
		
		dist[base] = 0; 					// 첫노드의 거리( 이 문제에서는 시간 ) 초기화
		pq.add(new Node(base,0)); 			// 큐에 첫 감염노드를 넣는다.
		
		while(!pq.isEmpty()) {
			Node nNode = pq.poll(); 		// 노드를 큐에서 꺼낸다.
			int nowNode = nNode.node;		// 꺼낸노드의 노드번호 
			int until_nowDist = nNode.dist;	// 꺼낸 노드까지 오는데 걸린 거리
			
			if(visit[nowNode]) 
				continue; 					// 방문한 노드면 다음 조건으로.
			
			cnt++; 							// 방문하는 노드마다 감염된 것이므로 cnt+1 
			visit[nowNode] = true; 			// 노드 방문처리
			
			for(int i=0; i<list[nowNode].size(); i++) { // 꺼낸 노드의 인접노드 탐색
				Node ntNode = list[nowNode].get(i); 	// 인접노드를 하나 꺼낸다.
				int nextNode = ntNode.node; // 인접노드의 노드 번호
				int nextDist = ntNode.dist; // 인접노드의 거리
				
				int distSum = until_nowDist + nextDist; // 큐에서 방금꺼낸 노드까지 오는 총거리(nowDist) + 현재노드에서 인접노드까지 방문하는데 드는 거리(nextDist)
			    if(dist[nextNode] > distSum) {// dist에 저장된 nextNode로 가는 방법 보다 nowNode를 통해 nextNode로 가는게 더 작을 때 
			    	dist[nextNode] = distSum; // 최단거리를 발견했다면 dist를 갱신합니다.
			    	pq.add(new Node(nextNode, distSum));// 갱신 후 갱신한 노드정보와, 갱신 노드까지 가는데 걸린 거리(distSum)을 넣습니다. 
			    }
			}
		}
		
		MaxSetting(); // 걸리는 시간 중 가장 오래 걸린 시간을 셋팅
		
		sb.append(cnt).append(' ')
		  .append(max).append('\n');
	}

	public void solution() throws Exception {
    	Reader r 		= new Reader();
    	int T 			= r.read();	// 테스트 케이스 최대 100개

    	while(T-->0) { 
    		computer 	= r.read(); // 총 컴퓨터 갯수
    		line 		= r.read(); // 의존성 갯수
    		base 		= r.read(); // 감염된 컴퓨터
    		list 		= new ArrayList[computer+1]; // 인접 노드를 담을 list
    		for(int i=1; i<=computer; i++) 
    			list[i] = new ArrayList<Node>();
    		
    		for(int i=0; i<line; i++) {
    			int to 	= r.read();
    			int from= r.read();
    			int distance = r.read();
    			list[from].add(new Node(to, distance));
    		}
    		Dijkstra();
    	}
    	System.out.println(sb);
	}
}
class Node{
	int node, dist;
	Node(int node, int dist){
		this.node = node; // 현재노드
		this.dist = dist; // 시작점부터 현재 노드까지 오는데 걸린 총 거리
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