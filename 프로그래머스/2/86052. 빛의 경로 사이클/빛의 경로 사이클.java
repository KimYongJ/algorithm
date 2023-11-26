// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Collections;

class Solution {
    
    boolean[][][] visit; // 좌표에 따른 방문 위치 저장 : 상하좌우
    ArrayList<Integer> list = new ArrayList<>(); // 결과를 담을 리스트
    int ylen, xlen, 
        dxy[][] = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우 좌표
    
    public ArrayList<Integer> solution(String[] grid) {
        ylen = grid.length;
        xlen = grid[0].length();
        visit = new boolean[ylen][xlen][4];
        
        for(int y=0; y<ylen; y++)
            for(int x=0; x<xlen; x++)
                for(int p=0; p<4; p++)
                    if(!visit[y][x][p]){
                        visit[y][x][p] = true;
                        BFS(y, x, p, grid);
                    }
        
        Collections.sort(list);
        
        return list;
    }
    public void BFS(int y,int x, int p, String[] grid){
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(new Node(y,x,p,0));
        
        while(!q.isEmpty()){
            Node node = q.poll();
            int nextP = getNextPosition(grid[node.y].charAt(node.x), node.p); // 도착한 좌표의 문자와 진입 경로를 통해 다음 좌표와 진입경로를 입력한다.
            int nextY = node.y + dxy[nextP][0];
            int nextX = node.x + dxy[nextP][1];
            int nextDist = node.dist + 1;
            
            if(nextY < 0) nextY = ylen-1;
            if(nextY >= ylen) nextY = 0;
            if(nextX < 0) nextX = xlen-1;
            if(nextX >= xlen) nextX = 0;
            
            if(visit[nextY][nextX][nextP]){ // 종료조건, 방문했다면 지금까지 거리 추가 후 종료
                list.add(nextDist);
                break;
            }
            
            visit[nextY][nextX][nextP] = true;
            q.add(new Node(nextY, nextX, nextP, nextDist));
        }
    }
    public int getNextPosition(char c, int p){
        if(c=='S'){
            return p;
        }else if(c=='L'){
            if(p==0) return 2;
            if(p==1) return 3;
            if(p==3) return 0;
        }else{
            if(p==0) return 3;
            if(p==1) return 2;
            if(p==2) return 0;
        }
        return 1;
    }
}
class Node{
    int y, x, p, dist;
    Node(int y, int x, int p, int dist){
        this.y = y;
        this.x = x;
        this.p = p;
        this.dist = dist;
    }
}