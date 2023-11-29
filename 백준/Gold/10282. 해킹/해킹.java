// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	
    public static void main(String[] args)throws Exception{
		final int INF = 10_000_001;
		
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	StringTokenizer st;
    	int T = Integer.parseInt(br.readLine());
    	
    	
    	while(T-->0) { // 테스트 케이스 최대 100개 
    		st = new StringTokenizer(br.readLine());
    		int computer = Integer.parseInt(st.nextToken()); // 총 컴퓨터 갯수
    		int line = Integer.parseInt(st.nextToken()); // 의존성 갯수
    		int base = Integer.parseInt(st.nextToken()); // 감염된 컴퓨터
    		
    		ArrayList<Node>[] list = new ArrayList[computer+1];
    		for(int i=1; i<=computer; i++) 
    			list[i] = new ArrayList<Node>();
    		
    		for(int i=0; i<line; i++) {
    			st = new StringTokenizer(br.readLine());
    			int to = Integer.parseInt(st.nextToken());
    			int from = Integer.parseInt(st.nextToken());
    			int distance = Integer.parseInt(st.nextToken());
    			list[from].add(new Node(to, distance));
    		}
    		int cnt = 0; // 감염된 노드 갯수
    		int max = 0; // 최장 걸린 시간
    		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->(a.dist-b.dist));
    		int[] dist = new int[computer+1]; // 거리 담을 배열( 이 문제에서는 시간 )
    		boolean[] visit = new boolean[computer+1];
    		Arrays.fill(dist, INF);
    		dist[base] = 0; // 첫노드의 거리( 이 문제에서는 시간 ) 초기화
    		pq.add(new Node(base,0)); // 큐에 첫 감염노드를 넣는다. 
    		while(!pq.isEmpty()) {
    			Node nNode = pq.poll(); // 노드를 큐에서 꺼낸다.
    			int nowNode = nNode.node;// 꺼낸노드의 노드번호 
    			int nowDist = nNode.dist;// 꺼낸 노드까지 오는데 걸린 거리
    			if(visit[nowNode]) continue; // 방문한 노드면 다음 조건으로.
    			
    			cnt++; // 방문하는 노드마다 감염된 것이므로 cnt+1 
    			visit[nowNode] = true; // 노드 방문처리
    			
    			for(int i=0; i<list[nowNode].size(); i++) { // 꺼낸 노드의 인접노드 탐색
    				Node ntNode = list[nowNode].get(i); // 인접노드를 하나 꺼낸다.
    				int nextNode = ntNode.node; // 인접노드의 노드 번호
    				int nextDist = ntNode.dist; // 인접노드의 거리
    				
    				int distSum = nowDist + nextDist; // 큐에서 방금꺼낸 노드까지 오는 총거리(nowDist) + 현재노드에서 인접노드까지 방문하는데 드는 거리(nextDist)
    			    if(dist[nextNode] > distSum) {// dist에 저장된 nextNode로 가는 방법 보다 nowNode를 통해 nextNode로 가는게 더 작을 때 
    			    	dist[nextNode] = distSum;
    			    	pq.add(new Node(nextNode, distSum));
    			    }
    			}
    		}
    		for(int i=1; i<=computer; i++) {
    			if(dist[i] != INF && max < dist[i])
    				max = dist[i];
    		}
    		sb.append(cnt).append(' ').append(max).append('\n');
    		
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