import java.util.ArrayDeque;
class Solution {
    static int[][] dxy = {{0,1},{0,-1},{1,0},{-1,0}};
    public int solution(int[][] maps) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int n = maps.length;
        int m = maps[0].length;
        q.add(new int[]{0,0,1});
        maps[0][0]=0;
        while(!q.isEmpty()){
            int[] qData = q.poll();
            for(int[] xy : dxy){
                int x = qData[0]+xy[0];
                int y = qData[1]+xy[1];
                int dist = qData[2] + 1;
                if(x<0 || y<0 || x>=n || y>=m || maps[x][y]!=1){
                    continue;
                }
                if(x+1==n && y+1==m)
                    return dist;
                q.add(new int[]{x,y,dist});
                maps[x][y] = 0;
            }
        }
        return -1;
    }
}