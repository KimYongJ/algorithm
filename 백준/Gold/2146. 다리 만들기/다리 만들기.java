// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
    static final int INF = Integer.MAX_VALUE;
    static int N, cityNum = 1;
    static int result = Integer.MAX_VALUE;
    static int arr[][];
    static int dy[] = {0,0,1,-1};
    static int dx[] = {-1,1,0,0};
    static int visit[][][];
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        
        for(int i=0; i<N; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }
        
        
        for(int y=0; y<N; y++) // 도시 마킹하기 ( 대륙별로 1,2,3 등.. 마킹 )
        	for(int x=0; x<N; x++)
        		if(arr[y][x]==1)
        			DFS(y,x,++cityNum);
        
        visit = new int[cityNum+1][N][N];
        for(int k=0; k<cityNum+1; k++)  // visit 초기화
        	for(int y=0; y<N; y++) 
        		Arrays.fill(visit[k][y], INF);
        	
       
        
        for(int y=0; y<N; y++) // 가장 짧은 도시 BFS 탐색
        	for(int x=0; x<N; x++)
                if(arr[y][x] != 0)
                    BFS(y,x, arr[y][x]); // BFS함수 안에서 가장 짧은 다른 대륙까지의 연결을 찾는다.
        
        System.out.println(result);
    }
    public static void BFS(int startY, int startX, int baseNumber){
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(new Node(startY, startX, -1));
        
        while(!q.isEmpty()){
            Node now = q.poll();
            if(result < now.dist) 
            	continue; 
            if(visit[baseNumber][now.y][now.x] > now.dist) {
            	visit[baseNumber][now.y][now.x] = now.dist;
	            for(int i=0; i<4; i++){
	                int y = now.y + dy[i];
	                int x = now.x + dx[i];
	                int dist = now.dist+1;
	                if(position_validate(y,x)) {
		                if(result < dist) continue; // 구해진 결과보다 현재 거리가 길다면 연산 스킵
		                if(arr[y][x] != 0 && arr[y][x] != baseNumber){ // 결과를 찾으면 종료
		                    if(result > dist)
		                        result = dist;
		                    return;
		                }
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
}
class Node{
    int y, x, dist;
    Node(int y, int x, int dist){
        this.dist = dist;
        this.y = y;
        this.x = x;
    }
}