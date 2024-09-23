//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2206
import java.util.ArrayDeque;
class Main{
    
    static int Y, X;
    static int dx[] = {0,0,1,-1};
    static int dy[] = {-1,1,0,0};
    static int arr[][];
    static boolean visit[][][];
    
    public static void main(String[] args)throws Exception{
        Y                   = read();
        X                   = read();
        arr                 = new int[Y][X];
        visit               = new boolean[2][Y][X];
        
        for(int y=0; y<Y; y++) {
        	for(int x=0; x<X; x++) {
        		arr[y][x] = System.in.read() & 15;
        	}
        	System.in.read();
        }
        	
        
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

                if(position_validate(y,x) && !(arr[y][x]==1 && now.usebreak==1)){	// 새로 만든 좌표의 유효성 검증 및 새좌표가 벽인데 못부실때 스킵
                	int usebreak = now.usebreak;
                	
                    if(arr[y][x]==1){// 새로 만든 좌표가 벽일때,
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
    static int read() throws Exception{
		int c, n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		return n;
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