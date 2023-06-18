class Solution {
    public int[] solution(int brown, int yellow) {
        int row = 0;
        for(int i=yellow; i>0; i--){
            row = yellow/i;
            if(yellow%i!=0) continue;
            if(brown/2 == i+2+row){
                return new int[]{i+2, row+2};
            }
            
        } 
        return new int[]{0,0};
    }
}