// https://github.com/KimYongJ
import java.util.ArrayDeque;
class Solution {
    static int r,maxX,maxY,Lx,Ly;
    static ArrayDeque<int[]> q = new ArrayDeque<>();
    static int[][] dxy = {{0,1},{0,-1},{1,0},{-1,0}};
    static boolean[][] visit;
    public int solution(String[] maps) {
        maxX = maps.length;
        maxY = maps[0].length();
        visit = new boolean[maxX][maxY];
        
        for(int i=0; i<maxX; i++)
            for(int j=0; j<maxY; j++)
                if(maps[i].charAt(j)=='S')
                    q.add(new int[]{i,j,0});
        
        BFS(maps,'L');
        if(r==0) {
            return -1;
        }
        
        int compare = r;
        
        q = new ArrayDeque<>();
        visit = new boolean[maxX][maxY];
        q.add(new int[]{Lx,Ly,0});
        
        BFS(maps,'E');
        return r==compare ? -1 : r;
    }
    public void BFS(String[] maps,char endCondition){
        while(!q.isEmpty()){
            int[] qData = q.poll();
            for(int[] xy : dxy){
                int x = xy[0]+qData[0];
                int y = xy[1]+qData[1];
                int dist = qData[2]+1;
                
                if(x<0 || y<0 || x>=maxX || y>=maxY 
                   || maps[x].charAt(y)=='X' || visit[x][y]){
                    continue;
                }
                if(maps[x].charAt(y)==endCondition){
                    r += dist;
                    Lx = x;
                    Ly = y;
                    return;
                }
                visit[x][y] = true;
                q.add(new int[]{x,y,dist});
                
            }
        }
        
    }
}