import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	
	static final int INF = 100_000_000;
	static int N, M, CNT;
	static int a, b, d;
	static int dp[]; // 결과를 dp로 담을 것
	static int dist[]; // T로부터 모든 정점에 대한 거리 
	static ArrayList<Node>[] adList; // 간선을 담을 인접 리스트
	
	public static int recursive(int now) {
		if(dp[now]!=-1) return dp[now];
		if(now == 2) return dp[now] = 1;
		dp[now] = 0;
		
		for(Node next : adList[now]) {
			if(dist[now] > dist[next.node]) {
				dp[now] += recursive(next.node);
			}
		}
		return dp[now];
	}
	
	public static void Dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.dist-b.dist);
		Arrays.fill(dist, INF);
		
		dist[2] = 0;
		pq.add(new Node(2,0));
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			int nowNode = now.node;
			
			for(Node next : adList[nowNode]) {
				int nextNode = next.node;
				int nextDist = next.dist;
				
				int distSum = dist[nowNode] + nextDist;
				
				if(dist[nextNode] > distSum) {
					dist[nextNode] = distSum;
					pq.add(new Node(nextNode, distSum));
				}
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken())+1;
		int M = Integer.parseInt(st.nextToken());
		
		adList = new ArrayList[N];
		dist = new int[N];
		dp = new int[N];
		for(int i=1; i<N; i++)
			adList[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			adList[a].add(new Node(b,d));
			adList[b].add(new Node(a,d));
		}
		
		Dijkstra();
		
		Arrays.fill(dp, -1);

		System.out.println( recursive(1) );
	}

}

class Node{
	int node, dist;
	Node(int node, int dist){
		this.node = node;
		this.dist = dist;
	}
}