// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;

class Main{
    static final int INF = Integer.MAX_VALUE;
    static int N, cityNum = 1;
    static int result = Integer.MAX_VALUE;
    static int arr[][], dist[][];
    static int dy[] = {0,0,1,-1};
    static int dx[] = {-1,1,0,0};
    public static void main(String[] args)throws Exception{

        N = read();
        arr = new int[N][N];
        dist = new int[N][N];
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++) {
                arr[i][j] = read();
                dist[i][j] = INF; // 최단거리 담을 배열  INF로 초기화
            }
        
        
        
        for(int y=0; y<N; y++) 
        	for(int x=0; x<N; x++) {
        		if(arr[y][x]==1)
        			DFS(y,x,++cityNum);  // 도시 마킹하기 ( 대륙별로 2,3,4 등.. 마킹 )
        		if(arr[y][x] != 0)
                    BFS(y,x, arr[y][x]); // BFS함수 안에서 가장 짧은 다른 대륙까지의 연결을 찾는다.
        	}
        
        System.out.println(result);
    }
    public static void BFS(int startY, int startX, int baseNumber){
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(new Node(startY, startX, -1));
        
        while(!q.isEmpty()){
            Node now = q.poll();
            if(result < now.dist) 
            	continue; 
            if(dist[now.y][now.x] > now.dist) {
            	dist[now.y][now.x] = now.dist;
	            for(int i=0; i<4; i++){
	                int y = now.y + dy[i];
	                int x = now.x + dx[i];
	                int dist = now.dist+1;
	                if(position_validate(y,x)) {
		                if(arr[y][x] != 0 && arr[y][x] != baseNumber){ // 결과를 찾으면 종료
		                    if(result > dist)
		                        result = dist;
		                    return;
		                }
		                if(arr[y][x]==0)
		                	q.add(new Node(y,x, dist));
	                }
	            }
            }
        }
    }
    
    // 대륙 별 숫자로 마킹하는 DFS함수
    public static void DFS(int y, int x, int markNum){
        if(arr[y][x] == 0) return;
        arr[y][x] = markNum;
        for(int i=0; i<4; i++){
            int newY = y + dy[i];
            int newX = x + dx[i];
            if(position_validate(newY,newX) && arr[newY][newX] == 1){
                DFS(newY, newX, markNum);
            }
        }
    }
    public static boolean position_validate(int newY, int newX){
        return newY>=0 && newX>=0 && newY<N && newX<N;
    }
    static int read() throws Exception {
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