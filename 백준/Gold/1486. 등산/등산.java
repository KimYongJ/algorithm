// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

class Main{
	static final int INF = 100_000_000;
	static int N, M, T, D, arr[][];
	static int dxy[][] = {{0,1},{0,-1},{1,0},{-1,0}};		// 다익스트라 함수 내에서 사용할 좌표 
    static PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.time-b.time); // 시간 기준 오름차순 정렬
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N 				= read()+1; 						// 세로
        M 				= read()+1; 						// 가로
        T 				= read();   						// 최대 52이하, 높이차가 T보다 작아야 함
        D 				= read();   						// 최대 백만이하, 어두워 지는데 까지 시간
        arr 			= new int[N][M];					// 높이를 입력받을 배열
        
        int[][]forward 	= new int[N][M];					// 1,1에서 모든 방향으로 가는 최단 거리를 저장할 배열
        int[][]backward = new int[N][M];					// 모든 방향에서 1,1로 가는 최단 거리를 담을 배열
        
        // 입력되는 높이 값을 int로 변환 후 arr배열에 담음
        for(int i=1; i<N; i++){
            String str         = br.readLine();
            for(int j=1; j<M; j++){
            	char c         = str.charAt(j-1);
            	arr[i][j]      = c<='Z' ? c-65 : c-71;
                forward[i][j]  = INF;
                backward[i][j] = INF;
            }
        }
        
        Dijkstra(true, forward);                            // 1,1에서 모든 정점으로 가는 최단거리를 구해 놓습니다. 
        Dijkstra(false, backward);                          // 모든 정점에서 1,1까지 오는 거리를 구합니다. 
        
        int max = 0;                                        // 가장 높은 위치
        
        for(int i=1; i<N; i++) 
        	for(int j=1; j<M; j++) 
        		if(forward[i][j] + backward[i][j] <= D)     // 특정 점으로 갔다 오는 시간의 합이 D보다 작을 때 
        			max = Math.max(max, arr[i][j]);         // 큰 높이를 max에 저장
        		
        System.out.println(max);
    }
    public static void Dijkstra(boolean type,int dist[][]) {
        pq.clear();
    	dist[1][1] = 0;                                     // 1,1까지 걸리는 시간은 0
    	pq.add(new Node(1,1,0));                            // 초기 값 삽입
    	
    	
    	while(!pq.isEmpty()) {
    		Node now 			= pq.poll();
    		int nowY 			= now.y;                       // 현재 y좌표
    		int nowX 			= now.x;                       // 현재 x좌표
    		int until_now_time 	= now.time;                    // 현재 y,x좌표까지 오는데 걸린 시간
    		
    		for(int xy[] : dxy) {
    			int newY 		= nowY + xy[0];
    			int newX 		= nowX + xy[1];
    			int newTime 	= until_now_time;
    			
    			if(newY<1 || newX<1 || newY>=N || newX>=M) continue; // 새로 만든 좌표가 범위를 벗어날 경우 연산 스킵
    			if(Math.abs(arr[nowY][nowX] - arr[newY][newX]) > T) continue;// 새로만든 좌표와 현재 좌표의 높이 차가 T보다 크면 연산 스킵
    			
    			if(type) {   // 정방향일 경우 newTime 연산( 1,1에서 모든 방향으로 가는 최단거를 담을 때 )
	    			if(arr[nowY][nowX] >= arr[newY][newX]) { // 이동하려는 곳이 낮거나 같은 곳이면
	    				newTime += 1;
	    			}else {  // 이동하려는 곳이 높은 곳일 경우 
	    				newTime += (int)Math.pow(arr[newY][newX]-arr[nowY][nowX],2);
	    			}
    			}else {      // 역방향일 경우 newTime 연산( 모든방향에서 1,1로 가는 최단거리를 담을 때 )
	    			if(arr[nowY][nowX] <= arr[newY][newX]) { // 이동하려는 곳이 높은 곳이면
	    				newTime += 1;
	    			}else {  // 이동하려는 곳이 낮은 곳일 경우
	    				newTime += (int)Math.pow(arr[newY][newX]-arr[nowY][nowX],2);
	    			}
    			}
    			
    			if(newTime <= D && dist[newY][newX] > newTime) {// 더 작은 거리(시간)을 찾은 경우
    				dist[newY][newX] = newTime;
    				pq.add(new Node(newY, newX, newTime));
    			}
    			
    		}
    	}
    }
    static int read() throws Exception { 						// 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}

class Node{
	int y, x, time;
	Node(int y, int x, int time){
		this.y = y;
		this.x = x;
		this.time = time;
	}
}
