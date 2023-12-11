// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main{
    
    static int Y, X;
    static int dx[] = {0,0,1,-1};
    static int dy[] = {-1,1,0,0};
    static char arr[][];
    static boolean visit[][][];
    
    public static void main(String[] args)throws Exception{
    	BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st 	= new StringTokenizer(br.readLine());
        Y                   = Integer.parseInt(st.nextToken());
        X                   = Integer.parseInt(st.nextToken());
        arr                 = new char[Y][X];
        visit               = new boolean[2][Y][X];
        
        for(int y=0; y<Y; y++) 
        	arr[y] 			= br.readLine().toCharArray();
        
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(new Node(0,0,1,0));
        visit[0][0][0] = true;

        while(!q.isEmpty()){
            Node now = q.poll();
                        
            if(now.y == Y-1 && now.x == X-1){			// 종료 지점 도달시 종료
                System.out.println(now.dist); return;
            }
            
            for(int i=0; i<4; i++){
                int y = now.y + dy[i];
                int x = now.x + dx[i];

                if(position_validate(y,x) && !(arr[y][x]=='1' && now.usebreak==1)){	// 새로 만든 좌표의 유효성 검증 및 새좌표가 벽인데 못부실때 스킵
                	int usebreak = now.usebreak;
                	
                    if(arr[y][x]=='1'){// 새로 만든 좌표가 벽일때,
                    	usebreak = 1;
                    }
                    
                    if(visit[usebreak][y][x]) continue;
                    
                    q.add(new Node(y,x,now.dist+1,usebreak));
                    visit[usebreak][y][x] = true;
                }
            }
        }
        System.out.println( -1 );
    }
    public static boolean position_validate(int y, int x) {
    	return y>=0 && x>=0 && y<Y && x<X;
    }
}
class Node{
    int y, x, dist, usebreak; // 벽을 부심 1, 부신적 없으면 0
    Node(int y, int x, int dist, int usebreak){
        this.y = y;
        this.x = x;
        this.dist = dist;
        this.usebreak = usebreak;
    }
}