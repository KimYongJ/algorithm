class Solution {
    public int[] solution(int brown, int yellow) {
        int[] result = new int[2];
        for(int i=yellow; i>0; i--){
            int row = yellow/i;
            if(yellow%i!=0) 
                continue;
            else if(brown/2 == i+2+row){
                result = new int[]{i+2, row+2};
                break;
            }
            
        } 
        return result;
    }
}