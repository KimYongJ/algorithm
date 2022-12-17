class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        int g = board[0]/2;
        int s = board[1]/2;
        int gm = g*-1;
        int sm = s*-1;
        int x = 0;
        int y = 0;
        for(String k : keyinput){
            if(k.contains("u") && s>y){
                ++y;
            }else if(k.contains("d") && sm<y){
                --y;
            }else if(k.contains("r") && g>x){
                ++x;
            }else if(k.contains("l") && gm<x){
                --x;
            }
        }
        int[] ans = {x,y};
        return ans;
        
    }
}