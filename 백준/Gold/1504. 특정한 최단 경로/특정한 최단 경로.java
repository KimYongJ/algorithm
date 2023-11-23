// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Main{
	
	final static int MAX = Integer.MAX_VALUE;
	static int n, arr[][], dist[];
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
				
		n = Integer.parseInt(st.nextToken()); // 노드 갯수
		int e = Integer.parseInt(st.nextToken()); // 간선의 갯수

		arr = new int[n+1][n+1]; // 노드 갯수를 2차원 배열로 선언
		dist = new int[n+1];
		
		for(int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());// 정점과 간선을 입력 받는다.
			int aNode = Integer.parseInt(st.nextToken());
			int bNode = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			arr[aNode][bNode] = d;
			arr[bNode][aNode] = d;
		}
		st = new StringTokenizer(br.readLine());// 반드시 거쳐야 하는 노드를 받는다.
		int mNode1 = Integer.parseInt(st.nextToken()); // 반드시 방문 해야 하는 노드 1
		int mNode2 = Integer.parseInt(st.nextToken()); // 반드시 방문 해야 하는 노드 2
		
		int AtoM1 = Dijkstra(1,mNode1);
		int AtoM2 = Dijkstra(1,mNode2);
		int M1toM2 = Dijkstra(mNode1,mNode2);
		int M1toN = Dijkstra(mNode1,n);
		int M2toN = Dijkstra(mNode2,n);
		
		if(M1toM2 == -1) { // 반드시 지나야 하는 노드가 서로 방문 불가일 경우 
			System.out.println(-1);
			return;
		}
		
		// 1번 방법 -> 반드시 거칠노드 1-> 반드시 거칠노드 2-> N
		long case1 = -1;
		if(AtoM1 !=-1 && M1toM2 !=-1 && M2toN!=-1) {
			case1 = AtoM1 + M1toM2 + M2toN;
		}
		// 2번 방법 -> 반드시 거칠노드 2-> 반드시 거칠노드 1-> N
		long case2 = -1;
		if(AtoM2 !=-1 && M1toM2 !=-1 && M1toN!=-1) {
			case2 = AtoM2 + M1toM2 + M1toN;
		}
		
		long result = Math.max(case1, case2);
		
		// 둘다 -1이 아닐 때 min
		if( case2!=-1 && case1 !=-1) {
			result = Math.min(case1, case2);
		}

		System.out.println(result);
	}	
	public static int Dijkstra(int start, int end) {
		PriorityQueue<Node> q = new PriorityQueue<Node>((a,b)->a.dist-b.dist);
		boolean[] visit = new boolean[n+1];
		Arrays.fill(dist,MAX);
		
		dist[start] = 0; // 자기 자신은 0으로 초기화
		
		q.add(new Node(start,0));
		
		while(!q.isEmpty()) {
			Node curNode = q.poll();
			int cur = curNode.end;
			
			if(!visit[cur]) {
				visit[cur] = true;
				for(int i=1; i<=n; i++) {
					if(arr[cur][i] != 0) { // 노드가 연결되어 있다면 
						int nextDistance = arr[cur][i] + curNode.dist;
						if(dist[i] > nextDistance) {
							dist[i] = nextDistance;
							q.add(new Node(i,nextDistance));
						}

					}
				}
			}

		}
		return dist[end] == MAX ? -1 : dist[end];
	}
}
class Node{
	int end, dist;
	public Node(int end, int dist) {
		this.end = end;
		this.dist = dist;
	}
}