class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        int x = 0;
        int y = 0;
        for(String k : keyinput){
            if(k.contains("u") && board[1]/2>y) ++y;
            else if(k.contains("d") && board[1]/2*-1<y) --y;
            else if(k.contains("r") && board[0]/2>x) ++x;
            else if(k.contains("l") && board[0]/2*-1<x) --x;
        }
        board[0]=x;
        board[1]=y;
        return board;
    }
}