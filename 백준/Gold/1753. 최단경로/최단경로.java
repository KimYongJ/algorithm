// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Main{
	
	final static int INF = 200_001; // 최대 나올 수 있는 거리가 20만이기에 INF는 200,001로 설정
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());

		ArrayList<Node>[] list = new ArrayList[N+1];
		for(int i=1; i<=N; i++) 
			list[i] = new ArrayList<Node>();
		
		while(E-->0) { // 값을 입력받는다.
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			list[a].add(new Node(b,e));
		}
		// 이하 다익스트라 알고리즘 
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.dist-b.dist);
		int[] dist = new int[N+1]; // start노드부터 뻗어나가는 거리를 담을 배열
		Arrays.fill(dist, INF); // 거리배열을 최댓값을 초기화 
		
		dist[start] = 0; // 시작 노드 방문거리 0으로 세팅 
		pq.add(new Node(start,0));// 시작노드를 담는다.
		
		while(!pq.isEmpty()) {// 큐가 빌 때까지 반복
			Node nowNode = pq.poll(); // 현재 노드를 꺼낸다.
			int nodeNum = nowNode.node;// 연산 편의를 위해 현재 노드번호 변수 선언
 
			for(int i=0; i<list[nodeNum].size(); i++) {
				Node nextNode = list[nodeNum].get(i);
				int nextDist = nowNode.dist + nextNode.dist;
				if(dist[nextNode.node]> nextDist) {
					dist[nextNode.node] = nextDist;
					pq.add(new Node(nextNode.node,nextDist));
				}
			}
				
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=N; i++) {
			if(dist[i] == INF)
				sb.append("INF").append('\n');
			else 
				sb.append(dist[i]).append('\n');
		}
		System.out.println(sb);
		
	}
}
class Node{
	int node, dist;
	public Node(int node, int dist) {
		this.node = node;
		this.dist = dist; // dist는 start노드에서 dist까지 걸리는 거리를 담는다. 
	}
}