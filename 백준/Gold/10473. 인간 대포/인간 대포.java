// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge{
	int a;
	float time;
	Edge(int a, float time){
		this.a = a;
		this.time = time;
	}
}
class Main{
	static final float INF = 160;
	static int N;
	static float mintime[], node[][];
	static boolean visit[];
	static ArrayList<Edge>[] edge;
	static PriorityQueue<Edge> pq = new PriorityQueue<Edge>((a,b)->{
		if(a.time-b.time < 0) {
			return -1;
		}else return 1;
	});
	
	// 시간을 구하는 함수
	public static float getTime(float x1, float y1, float x2, float y2, boolean type) {
		float dist1 = (float) Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
		float time1 = dist1/5; 						    // 걷는 시간
		float time2 = Math.abs(dist1 - 50)/5 +2; 	    // 대포로쏘는 50미터보다 작은 경우 음수가 되는 것을 방지해야 해서 abs함수사용
		return type ? time1 : Math.min(time1, time2); 	// type이 true이면 무조건 걸어야 하는 경우
	}
	
	public static float Dijkstra() {
		pq.add(new Edge(0,0)); 			// 0번째 노드(시작노드)까지 가는 시간 0 입력
		mintime[0] 				= 0; 	// 시작점 걸리는 시간 0 
		while(!pq.isEmpty()) {
			Edge now 			= pq.poll();
			if(visit[now.a])
				continue;
			visit[now.a] 		= true;
			
			for(Edge next : edge[now.a]) {
				int nextNode 	= next.a;
				float nextTime 	= next.time;
				float timeSum 	= mintime[now.a] + nextTime;
				if(mintime[nextNode] > timeSum) {
					mintime[nextNode] = timeSum;
					pq.add(new Edge(nextNode, timeSum));
				}
			}
			
		}
		
		
		return mintime[N-1]; // 마지막이 종료 노드 위치
	}
    public static void main(String[] args)throws Exception{ 
    	BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st 	= new StringTokenizer(br.readLine());
    	
    	float x1 			= Float.parseFloat(st.nextToken());
    	float y1 			= Float.parseFloat(st.nextToken());
    	
    	st 					= new StringTokenizer(br.readLine());
    	float x2 			= Float.parseFloat(st.nextToken());
    	float y2 			= Float.parseFloat(st.nextToken());
    	
    	N 					= Integer.parseInt(br.readLine())+2;
    	
    	node 				= new float[N][2]; 	// 시작, 종료 노드를 포함한 모든 노드의 좌표를 담을 배열
    	mintime 			= new float[N]; 	// 시작 노드로부터 모든 노드까지의 걸리는 최단시간을 담을 배열
    	visit 				= new boolean[N]; 	// 방문 유무를 체크할 변수
    	edge 				= new ArrayList[N]; // 각 정점의 인접 리스트를 담을 리스트 배열
    	for(int i=0; i<N; i++) { 				// 필요 값들 초기화
    		edge[i] 		= new ArrayList<>();
    		mintime[i] 		= INF;
    	}
    	
    	node[0][0] 			= x1; // 시작 x좌표 
    	node[0][1] 			= y1; // 시작 y좌표
    	node[N-1][0] 		= x2; // 종료 x좌표
    	node[N-1][1] 		= y2; // 종료 y좌표
    	

    	for(int i=1; i<N-1; i++) {
    		st 				= new StringTokenizer(br.readLine());
    		node[i][0] 		= Float.parseFloat(st.nextToken());
    		node[i][1] 		= Float.parseFloat(st.nextToken());
    	}
    	// 출발지는 대포가 아니기 때문에 달려가야 함, 도착 좌표는 생각할 필요가 없음, 도착 좌표가 어디로 가지 않고 받기하는 좌표기때문.
    	for(int i=1; i<N; i++) {
    		float time = getTime(node[0][0],node[0][1], node[i][0], node[i][1], true);
    		edge[0].add(new Edge(i,time));
    	}
    	// 각 좌표마다 서로 가는데 걸리는 시간을 인접 리스트에 담음
    	for(int i=1; i<N-1; i++) // 시작좌표와 도착 좌표 제외해서 담음.
    		for(int j=0; j<N; j++) {
    			float time = getTime(node[i][0],node[i][1], node[j][0],node[j][1] , false);
    			edge[i].add(new Edge(j,time));
    		}
    	
    	System.out.print(Dijkstra());
    }
    
}