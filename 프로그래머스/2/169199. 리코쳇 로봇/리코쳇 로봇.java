// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;
class Solution {
    
    int ylen,xlen;
    
    public int solution(String[] board) {
        ylen = board.length;
        xlen = board[0].length();
        
        int[][] dxy = {{1,0},{0,1},{-1,0},{0,-1}};
        ArrayDeque<int[]> q = new ArrayDeque<>(); // BFS실행할 큐 선언
        boolean[][] visit = new boolean[ylen][xlen];// 방문 체크할 배열 선언
        
        for(int y=0; y<ylen; y++)
            for(int x=0; x<xlen; x++){
                if(board[y].charAt(x)=='R'){// 시작점 찾기
                    q.add(new int[]{y,x,0});
                    visit[y][x] = true;
                }
            }
        
        while(!q.isEmpty()){
            
            int[] p = q.poll(); // 현재 위치를 꺼내온다.
            
            for(int i=0; i<4; i++){
                int y = p[0];
                int x = p[1];
                int dist = p[2]+1;
                
                while(true){ // 미끄러지는 코드
                    y += dxy[i][0];
                    x += dxy[i][1];
                    if(!Range(y,x) ||board[y].charAt(x)=='D'){
                        y -= dxy[i][0];
                        x -= dxy[i][1];
                        break;
                    }
                }
                
                if(visit[y][x]) continue; // 방문한적 있다면 pass
                
                if(board[y].charAt(x)=='G'){ 
                    return dist; // 최초에 찾은 목적지가 최단거리다.
                }
                // 목적지가 아닐 경우 방문 체크 후 큐에 값을 넣고 BFS진행
                visit[y][x] = true; 
                q.add(new int[]{y,x,dist});
                
            }
        }
        return -1;
    }
    public boolean Range(int y, int x){// 범위의 안이면 true 밖이면 false
        return y>=0 && y<ylen && x>=0 && x<xlen;
    }
}