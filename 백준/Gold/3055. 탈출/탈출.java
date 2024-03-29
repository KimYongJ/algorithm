// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Main{
    
    static int R, C, startI, startJ, result;
    static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
    static char arr[][];
    static ArrayDeque<Node> q = new ArrayDeque<>();
    static boolean visit[][];
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        arr = new char[R][C];
        visit = new boolean[R][C];
        for(int i=0; i<R; i++){
            String str = br.readLine();
            for(int j=0; j<C; j++){
                arr[i][j] = str.charAt(j);
                if(arr[i][j] == '*'){ // 물을 먼저 큐에 넣어 물을 먼저 연산토록 함
                    q.add(new Node(i,j,0,true));
                    visit[i][j] = true;
                }
                else if(arr[i][j]=='S'){
                    startI = i;
                    startJ = j;
                }
            }
        }
        
        q.add(new Node(startI,startJ,0,false)); // S의 위치를 큐에 넣음
        visit[startI][startJ] = true; // 해당 위치 방문 처리
        
        while(!q.isEmpty()){
            Node now = q.poll(); // 노드를 꺼냄
            
            for(int xy[] : dxy){
                int newI = now.i + xy[0]; // 새로운 i좌표 연산
                int newJ = now.j + xy[1]; // 새로운 j좌표 연산
                int newDist = now.dist+1;
                if( newI>=0 && newJ>=0 && newI<R && newJ<C && !visit[newI][newJ]) {
                    if(arr[newI][newJ] == 'D'){ // 끝에 도달했을 때 물이 아니면 종료
                    	if(now.water)
                    		continue;
                        System.out.println(newDist);
                        return;
                    }
                    if(arr[newI][newJ] == '.'|| arr[newI][newJ] == 'S'){ // 방문하지 않은 노드인 경우 이하 실행
                    	visit[newI][newJ] = true;
                        q.add(new Node(newI, newJ, newDist, now.water));
                    }
                }
            }
            
        }
        
        System.out.print("KAKTUS");
    }
}
class Node{
    int i, j, dist;
    boolean water;
    Node(int i, int j, int dist, boolean water){
        this.i = i;
        this.j = j;
        this.dist = dist;
        this.water = water; // 해당 좌표의 값이 물인지 아닌지 체크할 변수
    }
}
