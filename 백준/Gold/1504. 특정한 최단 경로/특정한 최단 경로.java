// https://github.com/KimYongJ/algorithm
import java.util.Arrays;
import java.util.PriorityQueue;


class Main{
	
	final static int MAX = Integer.MAX_VALUE;
	static int n, arr[][], dist[];

	public static void main(String[] args)throws Exception{
		n = read(); // 노드 갯수
		int e = read(); // 간선의 갯수

		arr = new int[n+1][n+1]; // 노드 갯수를 2차원 배열로 선언
		
		dist = new int[n+1]; // 다익스트라 알고리즘 실행시 사용할 거리를 담을 배열
		
		
		for(int i=0; i<e; i++) {
			// 정점과 간선을 입력 받는다.
			int aNode = read();
			int bNode = read();
			int d = read();
			arr[aNode][bNode] = d;
			arr[bNode][aNode] = d;
		}
		// 반드시 거쳐야 하는 노드를 받는다.
		int mNode1 = read(); // 반드시 방문 해야 하는 노드 1
		int mNode2 = read(); // 반드시 방문 해야 하는 노드 2
		
		long AtoM1 = Dijkstra(1,mNode1); // 1번 노드부터 반드시 방문해야할 노드1 까지 거리
		long AtoM2 = Dijkstra(1,mNode2); // 1번 노드부터 반드시 방문해야할 노드2 까지 거리
		long M1toM2 = Dijkstra(mNode1,mNode2); // 반드시 방문해야할 두노드의 거리 
		long M1toN = Dijkstra(mNode1,n); // 반드시 방문해야할 노드1부터 N까지 거리 
		long M2toN = Dijkstra(mNode2,n); // 반드시 방문해야할 노드2부터 N까지 거리 
		
		if(M1toM2 == -1) { // 반드시 지나야 하는 노드가 서로 방문 불가일 경우 
			System.out.println(-1);
			return;
		}
		
		// 1번 방법 -> 반드시 거칠노드 1-> 반드시 거칠노드 2-> N
		long case1 = AtoM1 + M1toM2 + M2toN;

		// 2번 방법 -> 반드시 거칠노드 2-> 반드시 거칠노드 1-> N
		long case2 = AtoM2 + M1toM2 + M1toN;

		long result = Math.min(case1, case2);
		
		if( result>=MAX) {
			result = -1; 
		}

		System.out.println(result);
	}	
	public static int Dijkstra(int start, int end) {
		PriorityQueue<Node> q = new PriorityQueue<Node>((a,b)->a.dist-b.dist);
		boolean[] visit = new boolean[n+1]; // 다익스트라 알고리즘 실행시 사용할 방문을 담을 배열
		Arrays.fill(dist,MAX);
		
		dist[start] = 0; // 자기 자신은 0으로 초기화
		
		q.add(new Node(start,0)); // 자기 자신과 거리를 넣어준다.
		
		while(!q.isEmpty()) { //큐가 빌 때까지 반복
			Node curNode = q.poll(); // 큐 를 하나 꺼내준다. 
			int cur = curNode.end; // 꺼낸 데이터의 현재 노드
			
			if(!visit[cur]) { // 꺼낸 데이터를 방문하지 않은 경우만 연산 실행 
				visit[cur] = true; // 방문 처리 
				for(int i=1; i<=n; i++) { // 노드를 하나씩 순회한다. 
					if(arr[cur][i] != 0) { // 노드가 연결되어 있다면 
						int nextDistance = arr[cur][i] + curNode.dist; // 현재노드에서 다음노드까지 거리 + 현재까지 오기위한 거리 
						if(dist[i] > nextDistance) { // start 노드에서 i까지 거리가(dist[i]) , 현재 노드에서 다음 노드까지 거리보다 길다면 이하 실행
							dist[i] = nextDistance;
							q.add(new Node(i,nextDistance));
						}

					}
				}
			}

		}
		return dist[end];
	}
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
class Node{
	int end, dist;
	public Node(int end, int dist) {
		this.end = end;
		this.dist = dist;
	}
}