import java.util.*;
class Solution {
    public int solution(int[][] board, int[] moves) {
        Stack<Integer> stack = new Stack<>();
        int doll = 0;
        for(int m : moves){
            int x = 0;  m-=1;
            for(int i=0; i<board.length; i++)
                if(board[i][m]!=0){
                    x = board[i][m];
                    board[i][m] = 0;
                    break;                
                }
            if(!stack.empty() && x==stack.peek()){
                    doll += 2;
                    stack.pop();
                    continue;
            }
            if(x!=0) stack.push(x);
        }
        return doll;
    }
}