// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;

class Main{
    static final int INF	= Integer.MAX_VALUE;
    static int result 		= Integer.MAX_VALUE;
    static int N, cityNum 	= 1;
    static int dy[] 		= {0,0,1,-1};
    static int dx[] 		= {-1,1,0,0};
    static int arr[][], dist[][];
    
    public static void main(String[] args)throws Exception{

        N 					= read();
        arr 				= new int[N][N];
        dist 				= new int[N][N];
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++) {
                arr[i][j] 	= read();
                dist[i][j] 	= INF; 					// 최단거리 담을 배열  INF로 초기화
            }
        
        
        
        for(int y=0; y<N; y++) 
        	for(int x=0; x<N; x++) {
        		if(arr[y][x]==1)
        			DFS(y,x,++cityNum);  			// 도시 마킹하기 ( 대륙별로 2,3,4 등.. 마킹 )
        		
        		if(arr[y][x] != 0)
                    BFS(y,x, arr[y][x]); 			// 도시 마킹 후 BFS함수 안에서 가장 짧은 다른 대륙까지의 연결을 찾는다.
        	}
        
        System.out.println(result);
    }
    public static void BFS(int startY, int startX, int baseNumber){
        ArrayDeque<Node> q = new ArrayDeque<>(); 	// 큐선언
        q.add(new Node(startY, startX, -1)); 		// 초기값 삽입 거리는 최초 -1로 삽입
        
        while(!q.isEmpty()){
            Node now = q.poll();
            if(result < now.dist)  					// 큐에서 꺼낸 거리가 결과로 구해둔 result보다 크면 연산하지 않음
            	continue; 
            if(dist[now.y][now.x] > now.dist) {		// dp형식으로 해당 좌표의 최단거리를 2차원 배열에 담아 놓는다.
            	dist[now.y][now.x] = now.dist;		// 최단거리가 아니면 연산하지 않음, 최단거리일 때 대입 후 연산
	            for(int i=0; i<4; i++){
	                int y = now.y + dy[i];
	                int x = now.x + dx[i];
	                int dist = now.dist+1;
	                if(position_validate(y,x)) {	// 새로 생성한 좌표 유효성 검증
		                if(arr[y][x] != 0 && arr[y][x] != baseNumber){ // 결과를 찾으면 종료
		                    if(result > dist)
		                        result = dist;
		                    return;
		                }
		                if(arr[y][x]==0)			//	바다면 큐에 데이터를 넣음
		                	q.add(new Node(y,x, dist));
	                }
	            }
            }
        }
    }
    // 대륙 별 숫자로 마킹하는 DFS함수
    public static void DFS(int y, int x, int markNum){
        if(arr[y][x] == 0) return;					// 바다면 마킹 종료
        arr[y][x] = markNum;						// 바다가 아니면 마킹넘버로 셋팅 
        for(int i=0; i<4; i++){
            int newY = y + dy[i];
            int newX = x + dx[i];
            if(position_validate(newY,newX) && arr[newY][newX] == 1){// 유효성 검증 후 1이면 DFS시작
                DFS(newY, newX, markNum);
            }
        }
    }
    public static boolean position_validate(int newY, int newX){// 좌표 유효성 검증 함수
        return newY>=0 && newX>=0 && newY<N && newX<N;
    }
    static int read() throws Exception {			// 빠른 숫자 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }
}
class Node{
    int y, x, dist;
    Node(int y, int x, int dist){
        this.dist = dist;
        this.y = y;
        this.x = x;
    }
}