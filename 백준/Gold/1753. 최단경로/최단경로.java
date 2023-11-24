// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Main{
	public static void main(String[] args)throws Exception{
		final int INF = 200_001;

		
		int N = read();
		int M = read();
		int start = read();
		
		ArrayList<Node>[] list = new ArrayList[N+1];
		for(int i=1; i<=N; i++)
			list[i] = new ArrayList<Node>();
		
		for(int i=0; i<M; i++) {
			int a = read();
			int b = read();
			int d = read();
			list[a].add(new Node(b,d));	
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.dist-b.dist);
		boolean[] visit = new boolean[N+1];
		int[] dist = new int[N+1];
		Arrays.fill(dist, INF);
		
		dist[start] = 0;
		pq.add(new Node(start,0));
		while(!pq.isEmpty()) {
			Node nowNodeObj = pq.poll();
			int nowNode = nowNodeObj.node;
			int nowDist = nowNodeObj.dist;
			
			if(visit[nowNode])  // 방문한노드라면 스킵
				continue;
			
			visit[nowNode] = true; // 방문처리
			
			for(int i=0; i<list[nowNode].size(); i++) {
				Node nextNodeObj = list[nowNode].get(i);
				int nextNode = nextNodeObj.node; // 방금 큐에서 꺼낸 nowNodeObj의 인접노드
				int nextDist = nextNodeObj.dist; // 방금 큐에서 꺼낸 nowNodeObj의 인접노드의 거리
				
				int distSum = nowDist + nextDist;// 방금 큐에서 꺼낸 노드까지의 거리와 꺼낸 노드의 인접리스트 까지 거리
				if(dist[nextNode] > distSum) {// 최단거리 저장배열 dist값보다 작다면 값 갱신 및 큐에 데이터 삽입 
					dist[nextNode] = distSum;
					pq.add(new Node(nextNode, distSum));
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			if(dist[i]==INF) sb.append("INF").append('\n');
			else sb.append(dist[i]).append('\n');
		}
		System.out.print(sb);
	}
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }
}
class Node{
	int node, dist;
	Node(int node, int dist){
		this.node = node;
		this.dist = dist;
	}
}