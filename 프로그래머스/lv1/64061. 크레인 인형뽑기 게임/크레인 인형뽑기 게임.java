import java.util.*;
class Solution {
    public int solution(int[][] board, int[] moves) {
        Stack<Integer> stack = new Stack<>();
        int doll = 0;
        for(int m : moves){
            for(int i=0; i<board.length; i++){
                int x = board[i][m-1];
                if(x!=0){
                    if(!stack.empty() && x==stack.peek()){
                        doll += 2;
                        stack.pop();
                   }else 
                        stack.push(x);
                    board[i][m-1] = 0;
                    break;
                }
            }
        }
        return doll;
    }
}