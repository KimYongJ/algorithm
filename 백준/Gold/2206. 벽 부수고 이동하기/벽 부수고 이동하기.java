// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main{
    
    static int Y, X, result = -1;
    static int dx[] = {0,0,1,-1};
    static int dy[] = {-1,1,0,0};
    static char arr[][];
    static boolean visit[][], visit_break[][];
    
    public static void main(String[] args)throws Exception{
        BufferedReader br   = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st	= new StringTokenizer(br.readLine());
        Y                   = Integer.parseInt(st.nextToken());
        X                   = Integer.parseInt(st.nextToken());
        arr                 = new char[Y][X];
        visit               = new boolean[Y][X];
        visit_break			= new boolean[Y][X];
        for(int y=0; y<Y; y++)
            arr[y] = br.readLine().toCharArray();
        
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(new Node(0,0,1,false));
        
        Loop:
        while(!q.isEmpty()){
            Node now = q.poll();
            
            if(now.usebreak) { 							// 벽 부수는 것을 사용한 노드들
            	if(visit_break[now.y][now.x])continue;
            	visit_break[now.y][now.x] = true;
            }else {										// 벽부수는 것을 사용하지 않은 노드들
            	if(visit[now.y][now.x])continue; 
            	visit[now.y][now.x] = true;
            }
            
            if(now.y == Y-1 && now.x == X-1){			// 종료 지점 도달시 종료
                result = now.dist;
                break Loop;
            }
            for(int i=0; i<4; i++){
                int y = now.y + dy[i];
                int x = now.x + dx[i];
                int dist = now.dist + 1;
                if(position_validate(y,x)){				// 새로 만든 좌표의 유효성 검증
                    if(arr[y][x] == '1' && !now.usebreak && !visit_break[y][x]){ // 새로 만든 좌표가 벽이고 해당 노드가 벽을 깨부순적이 없고, 벽을 부순 노드가 지나간 적이 없으면
                        q.add(new Node(y,x,dist,true));
                    }else if(arr[y][x] == '0' && !visit[y][x]){
                        q.add(new Node(y,x,dist,now.usebreak));
                    }
                }
            }
        }
        System.out.println( result );
    }
    public static boolean position_validate(int y, int x) {
    	return y>=0 && x>=0 && y<Y && x<X;
    }
}
class Node{
    int y, x, dist;
    boolean usebreak;
    Node(int y, int x, int dist, boolean usebreak){
        this.y = y;
        this.x = x;
        this.dist = dist;
        this.usebreak = usebreak;
    }
}