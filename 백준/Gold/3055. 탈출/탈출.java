// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
class Main{
    
    static int R, C, startI, startJ, endI, endJ, result;
    static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
    static boolean visit[][];
    static ArrayDeque<Node> q = new ArrayDeque<>();
    
    public static void main(String[] args)throws Exception{
        BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
        R 					= read();
        C 					= read();
        
        visit = new boolean[R+2][C+2];
        for(int i=1; i<=R; i++){
            String str 		= br.readLine();
            for(int j=1; j<=C; j++){
                char c		= str.charAt(j-1);
                if(c == '*'){ // 물을 먼저 큐에 넣어 물을 먼저 연산토록 함
                    q.add(new Node(i,j,0,true));
                    visit[i][j] = true; // 방문 체크
                }else if(c == 'S'){
                    startI 	= i;
                    startJ 	= j;
                }else if(c == 'D') {
                	endI 	= i;
                	endJ 	= j;
                }else if(c == 'X') {
                	visit[i][j] = true;
                }
            }
        }
        // 테두리에 true로 패딩을 넣어 불필요한 좌표 유효성 검증을 막음
        for(int i=0; i<C+2; i++){
        	visit[0][i] 	= true;
        	visit[R+1][i] 	= true;
        }
        for(int i=0; i<R+2; i++){
        	visit[i][0] 	= true;
        	visit[i][C+1] 	= true;
        }
        
        q.add(new Node(startI,startJ,0,false)); // S의 위치를 큐에 넣음
        visit[startI][startJ] = true; 			// S의 위치 방문 처리
        
        Loop : 
        while(!q.isEmpty()){
            Node now = q.poll(); 				// 노드를 꺼냄
            
            for(int xy[] : dxy){
                int newI 	= now.i + xy[0]; 	// 새로운 i좌표 연산
                int newJ 	= now.j + xy[1]; 	// 새로운 j좌표 연산
                int newDist = now.dist+1;
                
                if(!visit[newI][newJ]) { 
	                if(endI==newI && endJ==newJ){ 	// 끝에 도달했을 때 물이 아니면 종료
	                	if(now.water) 				// 끝에 도달한것이 물인 경우 연산 지속
	                		continue;
	                	else { 						// 끝에 도달한것물이 아닌 경우 결과 대입후 BFS 종료
		                    result 	= newDist;
		                    break Loop;
	                	}
	                }
	                visit[newI][newJ] = true;		// 방문 처리
	                q.add(new Node(newI, newJ, newDist, now.water));
                }
            }
            
        }
        System.out.print(result != 0 ? result : "KAKTUS");
    }
    // 빠른 입력을 위해 만든 함수
    public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
}
class Node{
    int i, j, dist;
    boolean water;
    Node(int i, int j, int dist, boolean water){
        this.i 		= i;
        this.j 		= j;
        this.dist 	= dist;
        this.water 	= water; // 해당 좌표의 값이 물인지 아닌지 체크할 변수
    }
}
