class Solution {
    public int solution(int[] numbers, int k) {
        int idx = 0;
        int maxIdx = numbers.length-1;
        for(int i=1;i<k;i++){
            idx+=2;
            if(idx-maxIdx==1){
                idx=0;
                continue;
            }
            else if(idx-maxIdx==2){
                idx=1;
                continue;
            }
        }
        

        return numbers[idx];
    }
}