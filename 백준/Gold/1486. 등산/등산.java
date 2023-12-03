// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	static final int INF = 100_000_000;
	static int N, M, T, D, minTime, arr[][];
	static int dxy[][] = {{0,1},{0,-1},{1,0},{-1,0}};
	static int dist[][][][] = new int[26][26][26][26];
	static boolean visit[][];
	
	static PriorityQueue<Node> pq; // 높은 순으로 내림 차순 정렬
	static PriorityQueue<Node> q; // 다익스트라에서 사용할 우선순위 큐, dist기준 오름 차순 정렬
	
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken())+1; // 세로
        M = Integer.parseInt(st.nextToken())+1; // 가로
        T = Integer.parseInt(st.nextToken()); // 52이하, 높이차가 T보다 작아야 함
        D = Integer.parseInt(st.nextToken()); // 백만이하, 어두워 지는 시간 
        arr = new int[N][M];
        pq = new PriorityQueue<Node>((a,b)->b.dist-a.dist); // 높이 내림 차순 정렬
        
        // 최단거리를 담을 dist배열 초기화
        for(int k=1; k<26; k++) 
        	for(int i=1; i<26; i++) 
        		for(int j=1; j<26; j++) 
        			Arrays.fill(dist[k][i][j], INF);
        		
        	
        // 입력되는 높이 값을 int로 변환 후 arr배열에 담음
        for(int i=1; i<N; i++){
            String str = br.readLine();
            for(int j=1; j<M; j++){
            	char c = str.charAt(j-1);
            	arr[i][j] = c -= c<='Z' ? 65 : 71;
            	
            	pq.add(new Node(i,j,c));// 높이가 높은 순으로 정렬
            }
        }
        
        Dijkstra(1,1); // 1,1에서 모든 정점으로 가는 최단거리를 구해 놓습니다. 
        
        while(!pq.isEmpty()) {
        	Node asc = pq.poll(); // 높이가 높은것을 우선순위에서 큐에서 하나 꺼냅니다. 
        	int Y = asc.y;
        	int X = asc.x;
        	
        	Dijkstra(Y, X); // 꺼낸 Y, X를 시작점으로 하여 모든 정점에 대한 최단거리를 저장합니다. 
        	
        	if( dist[1][1][Y][X] + dist[Y][X][1][1] <= D ) {
        		System.out.println(arr[Y][X]);
        		return;
        	}
        }
        System.out.println(0);
    }
    public static void Dijkstra(int startY, int startX) {
    	q = new PriorityQueue<Node>((a,b)->a.dist-b.dist);
    	dist[startY][startX][startY][startX] = 0;
    	
    	q.add(new Node(startY, startX, 0));
    	
    	while(!q.isEmpty()) {
    		Node now = q.poll();
    		
    		if(dist[startY][startX][now.y][now.x] < now.dist)
    			continue;
    		
    		for(int xy[] : dxy) {
    			int y = now.y + xy[0];
    			int x = now.x + xy[1];
    			int time;
    			if(y<1 || x<1 || y>=N || x>=M)
    				continue;
    			
    			if(Math.abs(arr[now.y][now.x]- arr[y][x]) > T) // 높이 차이가 T보다 크다면 연산을 하지 않는다.
    				continue;
    			
    			
    			if(arr[now.y][now.x] >= arr[y][x]) { // 가려는 곳이 더 낮다면
    				time = now.dist + 1; // 지금까지 걸린 시간에+1 처리
    			}else // 가려는 곳이 더 높다면
    				time = (int)Math.pow(arr[now.y][now.x]- arr[y][x],2) + now.dist;

    			if(dist[startY][startX][y][x] > time && time <= D) { // startY,startX에서 시작해서 y,x좌표로 가는 것을 구해놓은 것보다, now까지 와서 y,x좌표로 가는 시간이 더 작을 때 갱신
    				
    				dist[startY][startX][y][x] = time; // 최단거리 갱신 후 큐에 삽입
    				q.add(new Node(y,x,time));
    			}
    			
    		}
    	}
    	
    }
}

class Node{
	int y, x, dist;
	Node(int y, int x, int dist){
		this.y = y;
		this.x = x;
		this.dist = dist;
	}
}