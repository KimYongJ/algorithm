import java.util.Queue;
import java.util.ArrayDeque;
class Solution {
    static int result=Integer.MAX_VALUE,len;
    static int[][] dxy = {{-1,0},{0,1},{1,0},{0,-1}};
    public int solution(int[][] board) {
        len = board.length;
        boolean[][] bool = new boolean[len][len];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0,0,0,-1}); // x , y , cost(비용) ,  dir(방향)
        bool[0][0] = true;
        board[0][0] = 1;
        while(!q.isEmpty()){
            int[] qData = q.poll();
            for(int i=0; i<4; i++){
                int x1      = qData[0] + dxy[i][0];
                int y1      = qData[1] + dxy[i][1];
                int cost    = qData[2];
                int dir     = qData[3];
                if(dir==-1 || dir==i){ // 방향이 처음(-1)이거나 , 현재 방향(i)와 같으면 +100
                    cost += 100;
                }else{
                    cost += 600; // 방향이 다르면 +600
                }
                dir = i;
                if(x1==len-1 && y1==len-1){ // 종료조건
                    result = Math.min(cost,result);
                    continue;
                }
                if(x1< 0 || y1<0 || len<=x1 || len<= y1 || board[x1][y1]==1) continue;
                
                if(!bool[x1][y1] || board[x1][y1]>=cost){
                    board[x1][y1] = cost;
                    bool[x1][y1] = true;
                    q.add(new int[]{x1,y1,cost,dir});
                }else if(board[x1][y1]+500>= cost){
                    bool[x1][y1] = true;
                    q.add(new int[]{x1,y1,cost,dir});
                }
            }
        }
        return result;
    }
}