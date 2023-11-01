//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
class Main{
    public static void main(String[] args)throws Exception{
        ArrayDeque<Position> q = new ArrayDeque<>();  //BFS탐색을 위한 셋팅
        int[] dy = {1,-1,0,0};                        //BFS탐색을 위한 셋팅
        int[] dx = {0,0,1,-1};                        //BFS탐색을 위한 셋팅
        int result = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        char[][] arr = new char[y][x];
        
        for(int i=0; i<y; i++){
            String str = br.readLine();
            for(int j=0; j<x; j++){
                arr[i][j] = str.charAt(j);           // 문자열로 바꾸어 arr배열에 넣는다.
                if(arr[i][j]=='I'){
                    q.add(new Position(i,j));        // 도연이의 위치를 넣는다.
                }
            }
        }
        
        while(!q.isEmpty()){
            
            Position p = q.poll();                  // 큐에서 데이터를 하나 꺼낸다.
            
            for(int i=0; i<4; i++){                 // 꺼낸 데이터로 상하좌우 탐색 후 큐에 다시 넣어줌
                int newY = p.y + dy[i];             // 새로운 y좌표
                int newX = p.x + dx[i];             // 새로운 x좌표
                
                // 좌표의 유효성 검사
                if(newY<0 || newY>=y || newX<0 || newX>=x || arr[newY][newX]=='X'){
                    continue;
                }else if(arr[newY][newX]=='P'){     // 사람이라면 결과에 +1
                    result++;
                }
                
                q.add(new Position(newY,newX));    // 큐에 새로운 좌표를 넣어 준다.
                arr[newY][newX] = 'X';             // 왓던 곳을 방문할 필요 없기 때문에 방문한 곳을 벽으로 치환
            }
        }
        System.out.print(result == 0 ? "TT" : result);
    }
    static class Position{
        int y,x;
        Position(int y,int x){
            this.y = y;
            this.x = x;
        }
    }
}