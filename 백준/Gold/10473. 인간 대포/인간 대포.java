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
	static final float INF = Integer.MAX_VALUE;
	static int N;
	static float mintime[], node[][];
	static ArrayList<Edge>[] edge;
	static PriorityQueue<Edge> pq = new PriorityQueue<Edge>((a,b)->{
		if(a.time-b.time < 0) {
			return -1;
		}else return 1;
	});
	
	// 대포에서는 걷거나 대포를 쏘거나 빠른 것으로 해야함
	public static float getTime(float x1, float y1, float x2, float y2, boolean type) {
		float dist1 = (float) Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
		float dist2 = dist1;
		float time1 = dist1/5.0f; // 걷는 시간
		if(type)  // 무조건 걸어야 할경우(시작점과 도착점일 경우)
			return time1;
		float time2 = Math.abs(dist2 - 50)/5.0f +2; // 대포로 발사되는 시간

		return Math.min(time1, time2);
	}
	public static float Dijkstra() {
		pq.add(new Edge(0,0)); // 0번째 노드(시작노드)까지 가는 시간 0 입력
		mintime[0] = 0; // 시작점 걸리는 시간 0 
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			for(Edge next : edge[now.a]) {
				int nextNode = next.a;
				float nextTime = next.time;
				float timeSum = mintime[now.a] + nextTime;
				if(mintime[nextNode] > timeSum) {
					mintime[nextNode] = timeSum;
					pq.add(new Edge(nextNode, timeSum));
				}
			}
			
		}
		
		
		return mintime[N-1]; // 마지막이 종료 노드 위치
	}
    public static void main(String[] args)throws Exception{ 
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	float x1 = Float.parseFloat(st.nextToken());
    	float y1 = Float.parseFloat(st.nextToken());
    	
    	st = new StringTokenizer(br.readLine());
    	float x2 = Float.parseFloat(st.nextToken());
    	float y2 = Float.parseFloat(st.nextToken());
    	
    	N = Integer.parseInt(br.readLine())+2;
    	
    	node = new float[N][2]; // 시작, 종료 노드를 포함한 모든 노드의 좌표를 담을 배열
    	mintime = new float[N]; // 시작 노드로부터 모든 노드까지의 걸리는 최단시간을 담을 배열
    	edge = new ArrayList[N]; // 각 정점의 인접 리스트를 담을 리스트 배열
    	for(int i=0; i<N; i++) {
    		edge[i] = new ArrayList<>();
    		mintime[i] = INF;
    	}
    	
    	node[0][0] = x1; // 시작 x좌표 
    	node[0][1] = y1; // 시작 y좌표
    	node[N-1][0] = x2; // 종료 x좌표
    	node[N-1][1] = y2; // 종료 y좌표
    	

    	for(int i=1; i<N-1; i++) {
    		st = new StringTokenizer(br.readLine());
    		node[i][0] = Float.parseFloat(st.nextToken());
    		node[i][1] = Float.parseFloat(st.nextToken());
    	}
    	// 출발지는 대포가 아니기 때문에 달려가야 함
    	for(int i=1; i<N; i++) {
    		float time = getTime(node[0][0],node[0][1], node[i][0], node[i][1], true);
    		edge[0].add(new Edge(i,time));
    	}
    	// 각 좌표마다 서로 가는데 걸리는 시간을 인접 리스트에 담음
    	for(int i=1; i<N-1; i++) {
    		for(int j=0; j<N; j++) {
    			float time = getTime(node[i][0],node[i][1], node[j][0],node[j][1] , false);
    			edge[i].add(new Edge(j,time));
    		}
    	}
    	System.out.print(Dijkstra());
    }
    
}