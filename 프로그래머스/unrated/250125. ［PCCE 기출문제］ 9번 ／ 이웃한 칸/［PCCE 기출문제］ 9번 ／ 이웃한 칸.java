// https://github.com/KimYongJ/algorithm
class Solution {
    
    int xy[][] = {{0,1},{0,-1},{1,0},{-1,0}},
        cnt = 0, maxH, maxW;
    String base;
    
    public int solution(String[][] board, int h, int w) {
        base = board[h][w];
        maxH = board.length;
        maxW = board[0].length;
        
        for(int i=0; i<4; i++){
            int newH = h+xy[i][0];
            int newW = w+xy[i][1];
            if(newH>=0 && newW>=0 && newH < maxH && newW < maxW
               && board[newH][newW].equals(base) ){
                cnt++;
            }
        }
        
        
        
        return cnt;
    }
}