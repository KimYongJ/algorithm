import java.util.*;
class Solution {
    public int solution(int[][] board) {
        int l = board.length;
        for(int i=0; i<board.length; i++)
            for(int j=0; j<board[i].length; j++)
                if(board[i][j]==1) board[i][j]=2;
        
        for(int i=0; i<board.length; i++)
            for(int j=0; j<board[i].length; j++)
                if(board[i][j]==2) 
                    for(int x=-1;x<2; x++)
                        for(int y=-1; y<2; y++)
                            if(j+y>=0 && j+y<l && i+x>=0 && i+x<l && board[i+x][j+y]!=2)
                                board[i+x][j+y] = 1;
        int r = 0;
        for(int[] n : board) for(int m : n)
                if(m<1) r++;
        
        return r;
    }
}